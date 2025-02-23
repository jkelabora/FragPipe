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

package com.dmtavt.fragpipe.api;

import com.dmtavt.fragpipe.params.ThisAppProps;
import com.github.chhh.utils.StringUtils;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public class InputLcmsFile implements Comparable<InputLcmsFile> {
    private final Path path;
    private final String experiment;
    private final Integer replicate;
    private final String dataType;

    public static final String REASON_NON_ASCII = "has non-ASCII chars";
    public static final String REASON_DOTS = "has dots";
    public static final String REASON_MULTIPLE_DOTS = "has multiple dots";
    public static final String REASON_SPACES = "has spaces";
    public static final String allowedChars = "[A-Za-z0-9-_+.\\[\\]()]";
    public static final String disallowedChars = "[^A-Za-z0-9-_ +.\\[\\]()]";
    public static final Pattern disallowedExperimentPattern = Pattern.compile("[^A-Za-z0-9_]");
    public static final String REASON_DISALLOWED_CHARS = "has characters other than: " + allowedChars;

    public InputLcmsFile(Path path, String experiment, Integer replicate, String dataType) {
        this.path = path;
        experiment = experiment != null ? experiment.trim() : ThisAppProps.DEFAULT_LCMS_EXP_NAME;
        this.experiment = disallowedExperimentPattern.matcher(experiment).replaceAll("_");
        this.replicate = replicate;

        if (dataType == null) {
            this.dataType = guessDataType(path);
        } else {
            switch (dataType.trim().toLowerCase()) {
                case "dia":
                    this.dataType = "DIA";
                    break;
                case "gpf-dia":
                    this.dataType = "GPF-DIA";
                    break;
                case "dia-quant":
                    this.dataType = "DIA-Quant";
                    break;
                case "dia-lib":
                    this.dataType = "DIA-Lib";
                    break;
                default:
                    this.dataType = "DDA";
            }
        }
    }

    private String guessDataType(Path filePath) {
        String fileName = filePath.toAbsolutePath().toString();
        if (fileName.endsWith(".d") || fileName.toLowerCase().contains("dda") || fileName.contains("_Q1.") || fileName.contains("_Q2.") || fileName.contains("_Q3.")) { // DDA has higher priority.
            return "DDA";
        } else if (fileName.contains("DIA")) { // DIA has to be upper case.
            return "DIA";
        } else {
            return "DDA";
        }
    }

    public Path outputDir(Path workDir) {
        return workDir.resolve(getGroup());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof InputLcmsFile) {
            return compareTo((InputLcmsFile) o) == 0;
        } else {
            return false;
        }
    }

    public int compareTo(@NotNull InputLcmsFile other) { // the sorting here must be consistent with the one in LcmsFileGroup
        Comparator<InputLcmsFile> comparator = Comparator.comparing(InputLcmsFile::getGroup).thenComparing(InputLcmsFile::getDataType).thenComparing(p -> p.path.toAbsolutePath().toString());
        return comparator.compare(this, other);
    }

    @Override
    public int hashCode() {
        return (getPath().toAbsolutePath() + "-" + getGroup() + "-" + getDataType()).hashCode();
    }

    @Override
    public String toString() {
        return String.format("InputLcmsFile{group: '%s', dataType: '%s', path: '%s'}", getGroup(), getDataType(), getPath());
    }

    public String getGroup() {
        if (getReplicate() != null) {
            if (StringUtils.isNullOrWhitespace(experiment)) {
                return "exp_" + getReplicate();
            } else {
                return experiment + "_" + getReplicate();
            }
        }
        return experiment;
    }

    public String getExperiment() {
        return experiment;
    }

    public Integer getReplicate() {
        return replicate;
    }

    public String getDataType() {
        return dataType;
    }

    public Path getPath() {
        return path;
    }

    private static<T> void addNonNull(Collection<? super T> collection, T value) {
        if (value != null) {
            collection.add(value);
        }
    }

    public static Set<String> validatePath(String dir) {
//        if (p.getFileName().toString().contains("2file space")) {
//            int a = 1;
//        }
        Set<String> reasons = new HashSet<>();
        addNonNull(reasons, testIsNotAscii(dir));
        addNonNull(reasons, testHasSpaces(dir));
        return reasons;
    }

    public static Set<String> validateFilename(String fn) {
        Set<String> reasons = new HashSet<>();
        //addNonNull(reasons, testIsNotAscii(fn));
        addNonNull(reasons, testHasSpaces(fn));
        addNonNull(reasons, testHasMoreThanOneDot(fn));
        addNonNull(reasons, testHasNonAllowedChars(fn));
        return reasons;
    }

    private static String testIsNotAscii(String s) {
        return (s != null && !com.github.chhh.utils.StringUtils.isPureAscii(s)) ? REASON_NON_ASCII : null;
    }

    private static String testHasSpaces(String s) {
        return (s != null && s.contains(" ")) ? REASON_SPACES : null;
    }

    private static String testHasDots(String s) {
        return (s != null && s.contains(".")) ? REASON_DOTS : null;
    }

    private static String testHasMoreThanOneDot(String s) {
        return (s != null && s.chars().filter(c -> c == '.').count() > 1) ? REASON_MULTIPLE_DOTS : null;
    }

    private static String testHasNonAllowedChars(String s) {
        if (s == null) {
            return null;
        }
        return Pattern.compile(disallowedChars).matcher(s).find() ? REASON_DISALLOWED_CHARS : null;
    }

    public static Path renameBadFile(Path p) {
        String oldFn = p.getFileName().toString();
        final String replacement = "_";
        String newFn = oldFn.replaceAll(" ", replacement);
        if (testHasDots(newFn) != null) {
            newFn = StringUtils.upToLastDot(newFn).replaceAll("\\.", replacement) + "." + StringUtils.afterLastDot(newFn);
        }
        newFn = newFn.replaceAll(disallowedChars, replacement);
        return p.resolveSibling(newFn);
    }
}
