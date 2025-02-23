/*
 * This file is part of FragPipe.
 *
 * FragPipe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FragPipe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FragPipe. If not, see <https://www.gnu.org/licenses/>.
 */

package com.dmtavt.fragpipe.util;

import com.dmtavt.fragpipe.Fragpipe;
import com.dmtavt.fragpipe.dialogs.MassOffsetLoaderPanel;
import com.dmtavt.fragpipe.tabs.TabMsfragger;
import com.github.chhh.utils.SwingUtils;
import com.github.chhh.utils.swing.FileChooserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umich.ms.fileio.filetypes.agilent.cef.jaxb.P;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

public class GlycoMassLoader {
    public String glycoFilePath;
    public List<Double> glycoMasses;
    public MassOffsetLoaderPanel optionsPanel;

    private static final Logger log = LoggerFactory.getLogger(TabMsfragger.class);
    private static final String MASSES_FILE = "glycan_masses.txt";
    private static final Pattern pGlycoPattern = Pattern.compile("[AGFHNXP]");
    private static final Pattern MMGlycoPattern = Pattern.compile("[AGFHNPSYCXUM][0-9]+");
    private static final HashMap<String, String> pGlycoTokenMap;    // map pGlyco tokens to our internal Glycan strings
    private static final HashMap<String, String> MMGlycoTokenMap;    // map MetaMorpheus kind tokens to our internal Glycan strings

    public static final String PROP_FILECHOOSER_LAST_PATH = "glycoloader.filechooser.path";

    static
    {
        pGlycoTokenMap = new HashMap<>();
        pGlycoTokenMap.put("A", "NeuAc");
        pGlycoTokenMap.put("G", "NeuGc");
        pGlycoTokenMap.put("F", "Fuc");
        pGlycoTokenMap.put("H", "Hex");
        pGlycoTokenMap.put("N", "HexNAc");
        pGlycoTokenMap.put("X", "Xyl");
        pGlycoTokenMap.put("P", "HexPh");

        MMGlycoTokenMap = new HashMap<>();
        MMGlycoTokenMap.put("A", "NeuAc");
        MMGlycoTokenMap.put("G", "NeuGc");
        MMGlycoTokenMap.put("F", "Fuc");
        MMGlycoTokenMap.put("H", "Hex");
        MMGlycoTokenMap.put("N", "HexNAc");
        MMGlycoTokenMap.put("P", "Phospho");
        MMGlycoTokenMap.put("S", "Sulfo");
        MMGlycoTokenMap.put("Y", "Na");
        MMGlycoTokenMap.put("C", "Acetyl");
        MMGlycoTokenMap.put("X", "Xyl");
        MMGlycoTokenMap.put("U", "SuccinylHex");
        MMGlycoTokenMap.put("M", "Formyl");
    }

    private static final HashMap<String, Double> glycanMasses;
    static
    {
        glycanMasses = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(GlycoMassLoader.class.getResourceAsStream(MASSES_FILE))));
            String readline;
            while ((readline = reader.readLine()) != null) {
                String[] splits = readline.split("=");
                glycanMasses.put(splits[0].trim().toLowerCase(), Double.parseDouble(splits[1].trim()));
            }
        } catch (IOException | NullPointerException e) {
            throw new IllegalStateException("Error reading internal glycan masses file");
        }
    }

    public GlycoMassLoader(boolean msfraggerOnly) {
        optionsPanel = new MassOffsetLoaderPanel(msfraggerOnly);
        glycoMasses = new ArrayList<>();
    }

    public List<Double> loadMassesFile(Component parent) {
        List<javax.swing.filechooser.FileFilter> glycFilters = new ArrayList<>();
        FileFilter filter = new FileNameExtensionFilter("Glycan or Mod Database (.txt, .csv, .tsv, .glyc, .gdb)", "txt", "csv", "tsv", "glyc", "gdb");
        glycFilters.add(filter);
        String loc = Fragpipe.propsVarGet(PROP_FILECHOOSER_LAST_PATH);
        JFileChooser fc = FileChooserUtils.builder("Select the Glycan or other Mod List file")
                .approveButton("Select").mode(FileChooserUtils.FcMode.FILES_ONLY)
                .acceptAll(false).multi(false).filters(glycFilters)
                .paths(Stream.of(loc)).create();

        String selectedPath;
        int userSelection = fc.showSaveDialog(SwingUtils.findParentFrameForDialog(parent));
        if (JFileChooser.APPROVE_OPTION == userSelection) {
            selectedPath = fc.getSelectedFile().toString();
            Fragpipe.propsVarSet(PROP_FILECHOOSER_LAST_PATH, selectedPath);
            glycoFilePath = selectedPath;
            return loadMassesFromFile(selectedPath);
        } else {
            return new ArrayList<>();
        }
    }

    private List<Double> loadMassesFromFile(String selectedPath) {
        List<Double> masses;
        if (selectedPath.endsWith(".csv") || selectedPath.endsWith(".txt") || selectedPath.endsWith(".tsv") || selectedPath.endsWith(".glyc")) {
            // Byonic format
            masses = GlycoMassLoader.loadTextOffsets(selectedPath);
        } else if (selectedPath.endsWith(".gdb")) {
            // pGlyco format
            masses = GlycoMassLoader.loadPGlycoFile(selectedPath);
        } else {
            // invalid file type
            log.error("Invalid file type for mass offset file %s. Must be .csv, .txt, or .glyc");
            return new ArrayList<>();
        }
        glycoMasses = masses;
        return masses;
    }

    public List<String> loadOffsets(Component parent) {
        List<Double> masses = loadMassesFile(parent);
        return loadOffsetsHelper(parent, masses);
    }

    public List<String> loadOffsetsFromFile(Component parent, File glycoFile) {
        List<Double> masses = loadMassesFromFile(glycoFile.toString());
        return loadOffsetsHelper(parent, masses);
    }

    public List<String> loadOffsetsHelper(Component parent, List<Double> masses) {
        if (masses.size() > 0) {
            // combos and filtering
            final int confirmation = SwingUtils.showConfirmDialog2(parent, optionsPanel, "Offset loading options", OK_CANCEL_OPTION);
            if (JOptionPane.OK_OPTION == confirmation) {
                // combine glycan masses if requested (e.g. O-glycans)
                if (optionsPanel.getMaxCombos() > 1) {
                    masses = generateMassCombos(masses, optionsPanel.getMaxCombos(), optionsPanel.useMassFilter(), optionsPanel.getMaxMass(), optionsPanel.getMinMass());
                } else if (optionsPanel.useMassFilter()) {
                    masses = filterMasses(masses, optionsPanel.getMinMass(), optionsPanel.getMaxMass());
                }

                // make sure 0 is included in the mass offsets list
                if (!masses.contains(0.0)) {
                    masses.add(0, 0.0);
                }

                // clean up masses before returning final strings (round off floating point errors at 12 decimal places)
                List<String> massStrings = new ArrayList<>();
                for (double mass : masses) {
                    BigDecimal decimal = new BigDecimal(mass).setScale(12, RoundingMode.HALF_EVEN).stripTrailingZeros();
                    massStrings.add(decimal.toPlainString());
                }
                return massStrings;
            } else {
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
    }

    // Method for filtering if NOT using mass combinations
    private static List<Double> filterMasses(List<Double> inputMasses, double minMass, double maxMass) {
        List<Double> filteredMasses = new ArrayList<>();
        for (double mass : inputMasses) {
            if (mass <= maxMass && mass >= minMass) {
                filteredMasses.add(mass);
            }
        }
        return filteredMasses;
    }

    /**
     * Generate all combinations of provided masses up to the specified max number. Remove duplicates (at 4 decimal places)
     * Uses combinations with repetition since same glycan can occur multiple times on a peptide.
     * @return
     */
    private static List<Double> generateMassCombos(List<Double> masses, int maxCombos, boolean massFilter, double maxMass, double minMass) {
        Set<Long> existingMasses = new HashSet<>();
        List<Double> allMasses = new ArrayList<>();
        for (int count = 1; count <= maxCombos; count++) {
            // iterate combinations
            List<int[]> combos = GlycoMassLoader.combinationsWithRepetition(masses.size(), count);
            for (int[] combo : combos) {
                // calculate mass as the sum of the glycans at selected indices
                double comboMass = 0;
                for (int i : combo) {
                    comboMass += masses.get(i);
                }
                // check for duplicates and add if unique
                long massKey = Math.round(comboMass * 10000);
                if (!existingMasses.contains(massKey)) {
                    if (!massFilter) {
                        allMasses.add(comboMass);
                        existingMasses.add(massKey);
                    } else {
                        // filtering requested
                        if (comboMass <= maxMass && comboMass >= minMass) {
                            allMasses.add(comboMass);
                            existingMasses.add(massKey);
                        }
                    }
                }
            }
        }
        return allMasses;
    }

    // read masses only from a text file. Can have , or \t delimiter, and one or multiple entries per line.
    public static ArrayList<Double> loadTextOffsetsOld(String filePath) {
        ArrayList<Double> massOffsets = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String readline;
            while ((readline = reader.readLine()) != null) {
                readline = readline.trim();
                int t = readline.indexOf('#');
                if (t >= 0) {
                    readline = readline.substring(0, t);
                }

                if (readline.isEmpty()) {
                    continue;
                }

                String[] lineSplits;
                // handle csv and tsv inputs that may contain multiple entries per line
                if (readline.contains(",")) {
                    lineSplits = readline.split(",");
                } else if (readline.contains("\t")) {
                    lineSplits = readline.split("\t");
                } else {
                    lineSplits = new String[]{readline};
                }

                // parse masses
                for (String mass : lineSplits) {
                    try {
                        massOffsets.add(Double.parseDouble(mass.trim()));
                    } catch (NumberFormatException ex) {
                        log.warn(String.format("Invalid entry %s could not be parsed and will be ignored.", mass));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return massOffsets;
    }

    /**
     * Load pGlyco glycan database file of format (N)(H)(H) or similar. Reads only the composition, ignoring structure.
     * Removes duplicate masses to 4 decimal places
     * @param filePath
     * @return
     */
    public static ArrayList<Double> loadPGlycoFile(String filePath) {
        ArrayList<Double> massOffsets = new ArrayList<>();
        HashMap<Long, Boolean> existingMasses = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String readline;
            while ((readline = reader.readLine()) != null) {
                readline = readline.trim();
                int t = readline.indexOf('#');
                if (t >= 0) {
                    readline = readline.substring(0, t);
                }
                // skip empty lines and lines without a glycan structure
                if (!readline.contains("(")) {
                    continue;
                }

                // parse glycans from tokens
                String line = readline.replace("Hp", "P");      // remove the only double character, if present
                Matcher matcher = pGlycoPattern.matcher(line);
                boolean valid = true;
                double glycanMass = 0;
                while(matcher.find()) {
                    String glycanToken = matcher.group();
                    if (pGlycoTokenMap.containsKey(glycanToken)) {
                        glycanMass += glycanMasses.get(pGlycoTokenMap.get(glycanToken).toLowerCase());
                    } else {
                        log.warn(String.format("Invalid token %s in line %s. This line will be skipped", glycanToken, line));
                        valid = false;
                        break;
                    }
                }
                // check for duplicates and add if unique
                if (valid) {
                    long massKey = Math.round(glycanMass * 10000);
                    if (!existingMasses.containsKey(massKey)) {
                        massOffsets.add(glycanMass);
                        existingMasses.put(massKey, true);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return massOffsets;
    }

    /**
     * Load glycans from a file containing Byonic format glycans (e.g., HexNAc(2)Hex(5) % 1216.4228)
     * @param filePath
     * @return
     */
    public static ArrayList<Double> loadTextOffsets(String filePath) {
        ArrayList<Double> massOffsets = new ArrayList<>();
        List<String> charsNotInKindGlycans = Arrays.asList("(", ")", "%", "_", "-", "\t");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String readline;
            while ((readline = reader.readLine()) != null) {
                readline = readline.trim();
                int t = readline.indexOf('#');
                if (t >= 0) {
                    readline = readline.substring(0, t);
                }

                if (readline.isEmpty()) {
                    continue;
                }

                String[] lineSplits;
                // handle csv and tsv inputs that may contain multiple entries per line
                if (readline.contains(",")) {
                    lineSplits = readline.split(",");
                } else if (readline.contains("\t")) {
                    lineSplits = readline.split("\t");
                } else {
                    lineSplits = new String[]{readline};
                }

                // parse glycans
                String glycanName;
                for (String line : lineSplits) {
                    double glycanMass;
                    // handle new and old formats
                    if (line.contains("%") || line.contains("(")) {
                        if (line.contains("%")) {
                            // default database includes mass in addition to glycan name - ignore mass and only take name
                            glycanName = line.split("%")[0];
                        } else {
                            glycanName = line;
                        }
                        glycanMass = parseByonicGlycanString(glycanName);
                    } else {
                        if (line.contains("\t")) {
                            // various databases may include other info in addition to glycan name - ignore and only take name (first column)
                            glycanName = line.split("\t")[0];
                            glycanMass = parseOldPTMSGlycanString(glycanName);
                        } else if (line.contains("_")) {
                            glycanName = line;
                            glycanMass = parseOldPTMSGlycanString(glycanName);
                        } else if (charsNotInKindGlycans.stream().noneMatch(line::contains)) {
                            // line contains no distinguishing characters - see if it is a MetaMorpheus "Kind" glycan
                            glycanMass = parseMMKindGlycanString(line);
                        } else {
                            // not a recognized format - often other info in the database
                            continue;
                        }

                    }
                    if (glycanMass == 0) {
                        continue;
                    }
                    massOffsets.add(glycanMass);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return massOffsets;
    }

    /**
     * Parse old PTM-Shepherd format glycans (HexNAc-2_Hex-5)
     * @param glycanString string to parse
     * @return mass of glycan
     */
    public static double parseOldPTMSGlycanString(String glycanString) {
        double mass = 0;
        String[] splits = glycanString.split("_");
        // Read all residue counts into the composition container
        for (String split: splits) {
            String[] glycanSplits = split.split("-");
            mass = getResidueMass(mass, glycanSplits);
        }
        return mass;
    }

    /**
     * Parse Byonic base format (Glyc1(#)Glyc2(#) ... % Mass)
     * @param glycanString Byonic format glycan string
     * @return mass of glycan
     */
    public static double parseByonicGlycanString(String glycanString) {
        String[] massSplits = glycanString.split(" % ");
        String[] compositionSplits = massSplits[0].trim().split("\\)");
        // Read all residue counts into the composition container
        double mass = 0;
        for (String split: compositionSplits) {
            String[] glycanSplits = split.split("\\(");
            mass = getResidueMass(mass, glycanSplits);
        }
        return mass;
    }

    /**
     * Parse MetaMorpheus "Kind" glycan strings, which are in the format N2H2A1 (for example)
     * @param glycanString MM format glycan kind string
     * @return mass of glycan
     */
    public static double parseMMKindGlycanString(String glycanString) {
        Matcher matcher = MMGlycoPattern.matcher(glycanString);
        double glycanMass = 0;
        while(matcher.find()) {
            String glycanToken = matcher.group();
            String glycanType = glycanToken.substring(0, 1);
            int glycanCount = Integer.parseInt(glycanToken.substring(1));
            if (MMGlycoTokenMap.containsKey(glycanType)) {
                glycanMass += (glycanMasses.get(MMGlycoTokenMap.get(glycanType).toLowerCase()) * glycanCount);
            } else {
                log.warn(String.format("Invalid token %s in line %s. This line will be skipped", glycanToken, glycanString));
                glycanMass = 0;
                break;
            }
        }
        return glycanMass;
    }

    private static double getResidueMass(double mass, String[] glycanSplits) {
        String glycanResidue = glycanSplits[0].trim().toLowerCase();
        if (glycanMasses.containsKey(glycanResidue)) {
            double residueMass = glycanMasses.get(glycanResidue);
            int count = Integer.parseInt(glycanSplits[1].trim());
            mass += (residueMass * count);
        } else {
            log.error(String.format("Invalid glycan %s is not in the internal database. Please add its mass to the database file and retry.", glycanResidue));
        }
        return mass;
    }

    // recursive combination generator (with repetition)
    public static List<int[]> combinationsWithRepetition(int numItems, int numChoices) {
        List<int[]> combinations = new ArrayList<>();
        combinationsHelper(combinations, new int[numChoices], 0, numItems-1, 0);
        return combinations;
    }

    // helper method for recursive combination generator
    private static void combinationsHelper(List<int[]> combinations, int[] data, int start, int end, int index) {
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);
        } else if (start <= end) {
            data[index] = start;
            combinationsHelper(combinations, data, start, end, index + 1);  // include start to allow repetition
            combinationsHelper(combinations, data, start + 1, end, index);
        }
    }

}
