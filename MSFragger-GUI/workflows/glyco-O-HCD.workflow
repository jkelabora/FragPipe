# Workflow: glyco-O-HCD



crystalc.run-crystalc=false
database.decoy-tag=rev_
diann.fragpipe.cmd-opts=
diann.heavy=
diann.library=
diann.light=
diann.medium=
diann.q-value=0.01
diann.quantification-strategy=3
diann.run-dia-nn=false
diann.run-dia-plex=false
diann.run-specific-protein-q-value=false
diann.unrelated-runs=false
diann.use-predicted-spectra=true
diaumpire.AdjustFragIntensity=true
diaumpire.BoostComplementaryIon=false
diaumpire.CorrThreshold=0
diaumpire.DeltaApex=0.2
diaumpire.ExportPrecursorPeak=false
diaumpire.Q1=true
diaumpire.Q2=true
diaumpire.Q3=true
diaumpire.RFmax=500
diaumpire.RPmax=25
diaumpire.RTOverlap=0.3
diaumpire.SE.EstimateBG=false
diaumpire.SE.IsoPattern=0.3
diaumpire.SE.MS1PPM=10
diaumpire.SE.MS2PPM=20
diaumpire.SE.MS2SN=1.1
diaumpire.SE.MassDefectFilter=true
diaumpire.SE.MassDefectOffset=0.1
diaumpire.SE.NoMissedScan=1
diaumpire.SE.SN=1.1
diaumpire.run-diaumpire=false
freequant.mz-tol=10
freequant.rt-tol=0.4
freequant.run-freequant=false
ionquant.excludemods=
ionquant.heavy=
ionquant.imtol=0.05
ionquant.ionfdr=0.01
ionquant.light=
ionquant.locprob=0.75
ionquant.maxlfq=1
ionquant.mbr=0
ionquant.mbrimtol=0.05
ionquant.mbrmincorr=0
ionquant.mbrrttol=1
ionquant.mbrtoprun=10
ionquant.medium=
ionquant.minfreq=0
ionquant.minions=2
ionquant.minisotopes=2
ionquant.minscans=3
ionquant.mztol=10
ionquant.normalization=1
ionquant.peptidefdr=1
ionquant.proteinfdr=1
ionquant.requantify=1
ionquant.rttol=0.4
ionquant.run-ionquant=true
ionquant.tp=0
ionquant.uniqueness=0
ionquant.use-labeling=false
ionquant.use-lfq=true
ionquant.writeindex=0
msbooster.predict-rt=true
msbooster.predict-spectra=true
msbooster.run-msbooster=false
msbooster.use-correlated-features=false
msfragger.Y_type_masses=0 203.07937 365.1322
msfragger.activation_types=all
msfragger.add_topN_complementary=0
msfragger.allowed_missed_cleavage_1=2
msfragger.allowed_missed_cleavage_2=2
msfragger.calibrate_mass=2
msfragger.check_spectral_files=true
msfragger.clip_nTerm_M=true
msfragger.deisotope=1
msfragger.delta_mass_exclude_ranges=(-1.5,3.5)
msfragger.deneutralloss=1
msfragger.diagnostic_fragments=204.086646 186.076086 168.065526 366.139466 144.0656 138.055 512.197375 292.1026925 274.0921325 657.2349 243.026426 405.079246 485.045576 308.09761
msfragger.diagnostic_intensity_filter=0.1
msfragger.digest_max_length=50
msfragger.digest_min_length=7
msfragger.fragment_ion_series=b,y
msfragger.fragment_mass_tolerance=20
msfragger.fragment_mass_units=1
msfragger.group_variable=0
msfragger.intensity_transform=0
msfragger.ion_series_definitions=
msfragger.isotope_error=0/1/2
msfragger.labile_search_mode=labile
msfragger.localize_delta_mass=true
msfragger.mass_diff_to_variable_mod=0
msfragger.mass_offsets=0 203.0794 365.1322 406.1588 494.1748 511.1901 527.185 568.2116 656.2276 673.2429 714.2695 730.2644 802.2855 818.2804 859.307 860.3274 876.3223 947.323 964.3383 1005.3649 1021.3598 1022.3802 1095.3966 1109.3758 1167.4177 1238.4184 1241.4545 1312.4552 1313.4756 1386.492 1387.5124 1441.4978 1458.5131 609.2382 697.2542 771.291 917.3489 933.3438 1062.3864 1063.4068 1079.4017 1150.4024 1208.4443 1224.4392 1225.4596 1298.476 1370.4971 1444.5339 1515.5346 1516.555 1589.5714 1590.5918 1644.5772 1661.5925 892.3172 1038.3751 1183.4126 1329.4705 1460.5288 1474.508 1532.5499 1603.5506 1606.5867 1677.5874 1678.6078 1751.6242 1752.6446 1806.63 1823.6453 812.3176 900.3336 974.3704 1120.4283 1136.4232 1265.4658 1266.4862 1282.4811 1353.4818 1411.5237 1427.5186 1428.539 1501.5554 1573.5765 1647.6133 1718.614 1719.6344 1792.6508 1793.6712 1847.6566 1864.6719 988.3496 1296.4603 1354.5022 1499.5397 1732.5932 1735.6293 1807.6504 1880.6668 1881.6872 1935.6726 1952.6879 1184.433 1371.5175 1475.5284 1533.5703 1620.5659 1749.6085 1824.6657 1897.6821 1898.7025 1969.7032 1054.37 1200.4279 1257.4494 1345.4654 1403.5073 1491.5233 1548.5448 1549.5652 1622.5816 1636.5608 1694.6027 1765.6034 1768.6395 1839.6402 1840.6606 1913.677 1914.6974 1968.6828 1985.6981 1663.6082 1809.6661 1954.7036 1955.724 2009.7094 2026.7247 1894.646 2042.7196 2043.74 2097.7254 2114.7407 1346.4858 1637.5812 1695.6231 1782.6187 1911.6613 1986.7185 2059.7349 2060.7553 2131.756 1574.5969 1736.6497 2027.7451 2100.7615 2101.7819 2155.7673 2172.7826 1825.661 1971.7189 2116.7564 2117.7768 2171.7622 2188.7775 1604.571 1662.6129 1766.6238 2040.7039 2115.7611 2189.7979 2243.7833 2260.7986 1927.6562 2056.6988 2130.7356 2204.7724 2205.7928 2259.7782 2276.7935 2245.799 2246.8194 2300.8048 2317.8201 1720.6548 1865.6923 1882.7076 2098.7458 2173.803 2247.8398 2301.8252 2318.8405 2262.8143 2263.8347 2334.8354 2185.7414 2333.815 2388.8208 2405.8361 1928.6766 2073.7141 2202.7567 2277.8139 2350.8303 2351.8507 2422.8514 2010.7298 2391.8569 2392.8773 2446.8627 2463.878 2407.8518 2408.8722 2462.8576 2479.8729 2044.7604 2335.8558 2409.8926 2480.8933 2190.7932 2336.8511 2481.8886 2482.909 2536.8944 2553.9097 2218.7516 2347.7942 2421.831 2495.8678 2496.8882 2550.8736 2567.8889 2554.9301 2608.9155 2625.9308 2476.8368 2551.894 2624.9104 2679.9162 2696.9315 2627.9465 2628.9669 2682.9523 2699.9676 2698.9472 2753.953 2770.9683 2626.9512 2700.988 2754.9734 2771.9887 2772.984 2774.0044 2827.9898 2845.0051 2775.0248 2829.0102 2846.0255 2882.9956 2900.0109 2917.0262 1015.397 1103.413 1177.4498 1323.5077 1339.5026 1468.5452 1469.5656 1485.5605 1556.5612 1614.6031 1630.598 1631.6184 1704.6348 1776.6559 1850.6927 1921.6934 1922.7138 1995.7302 1996.7506 2050.736 2067.7513 1191.429 1557.5816 1702.6191 1938.7087 2083.7462 2084.7666 2138.752 1866.6876 2012.7455 2157.783 2158.8034 2212.7888 2229.8041 1777.6763 1939.7291 2230.8245 2303.8409 2304.8613 2358.8467 2375.862 2028.7404 2174.7983 2319.8358 2320.8562 2374.8416 2448.8784 2449.8988 2503.8842 2520.8995 1923.7342 2068.7717 2085.787 2376.8824 2450.9192 2504.9046 2521.9199 2465.8937 2466.9141 2537.9148 2591.9002 2213.8092 2594.9363 2595.9567 2649.9421 2666.9574 2610.9312 2611.9516 2665.937 2538.9352 2612.972 2683.9727 2393.8726 2539.9305 2684.968 2685.9884 2739.9738 2756.9891 2758.0095 2811.9949 2831.0259 2832.0463 2886.0317 2903.047 2902.0266 2957.0324 2974.0477 2830.0306 2904.0674 2958.0528 2975.0681 2976.0634 2977.0838 2978.1042 1419.5022 1565.5601 1710.5976 1856.6555 1987.7138 2001.693 2133.7717 2278.8092 2279.8296 1711.618 2002.7134 2147.7509 2424.8671 2425.8875 2292.7884 2569.9046 2570.925 2641.9257 2293.8088 2438.8463 2642.9461 2715.9625 2716.9829 2787.9836 2555.9254 2701.9833 2847.0208 2848.0412 2919.0419 2583.8838 2712.9264 2786.9632 2861 2862.0204 2916.0058 2933.0211 2920.0623 2991.063 2841.969 2990.0426 2993.0787 2994.0991 2992.0834 1218.4764 1306.4924 1380.5292 1526.5871 1542.582 1671.6246 1672.645 1688.6399 1759.6406 1817.6825 1833.6774 1834.6978 1907.7142 1979.7353 2053.7721 2124.7728 2125.7932 2198.8096 2199.83 2253.8154 2270.8307 1394.5084 1760.661 1905.6985 2141.7881 2286.8256 2287.846 2341.8314 2069.767 2215.8249 2360.8624 2361.8828 2415.8682 2432.8835 1980.7557 2142.8085 2433.9039 2506.9203 2507.9407 2561.9261 2578.9414 2231.8198 2377.8777 2522.9152 2523.9356 2577.921 2651.9578 2652.9782 2706.9636 2723.9789 2126.8136 2271.8511 2288.8664 2579.9618 2653.9986 2707.984 2724.9993 2668.9731 2669.9935 2740.9942 2794.9796 2416.8886 2798.0157 2799.0361 2853.0215 2870.0368 2814.0106 2815.031 2869.0164 2742.0146 2816.0514 2887.0521 2596.952 2743.0099 2888.0474 2889.0678 2943.0532 2960.0685 2961.0889 1482.5244 1790.6351 1848.677 1993.7145 2226.768 2429.8474 2156.7877 2534.8787 2609.9359 2737.9581 2214.8296 2359.8671 2592.9206 2667.9778 2796 2813.0153 2941.0375 2971.0116 1857.6759 2148.7713 2206.8132 2497.9086 2571.9454 2231.8449 2393.8977 2684.9931 2759.0299 2439.8667 2584.9042 2713.9468 2789.004 2863.0408 2934.0415 2555.9505 2847.0459 2921.0827 2729.9417 2858.9843 2988.0269 1581.555 1727.6129 1784.6344 1872.6504 1930.6923 2018.7083 2075.7298 2076.7502 2149.7666 2163.7458 2221.7877 2295.8245 2366.8252 2367.8456 2440.862 2441.8824 2512.8831 1873.6708 2164.7662 2222.8081 2309.8037 2513.9035 2586.9199 2587.9403 2658.941 2352.846 2498.9039 2643.9414 2644.9618 2454.8412 2657.9206 2731.9574 2732.9778 2803.9785 2789.9993 2791.0197 2455.8616 2600.8991 2804.9989 2878.0153 2879.0357 2950.0364 2935.0368 2936.0572 2937.0776 2717.9782 2864.0361 2745.9366 2874.9792 2949.016 2759.0048 2905.0627 2019.7287 2310.8241 2368.866 2659.9614 2733.9982 2601.9195 2746.957 2875.9996 2951.0568 2718.0033 2891.9945 2434.9243 2596.9771 2888.0725 2962.1093 2921.0576 2406.8565 2464.8984 2568.9093 2842.9894 2918.0466 2522.9403 2901.0313 2976.0885 2730.9621 2580.9822 2726.0197 2743.035 2959.0732 2871.0572 2905.0878 2893.0149
msfragger.max_fragment_charge=2
msfragger.max_variable_mods_combinations=5000
msfragger.max_variable_mods_per_peptide=3
msfragger.min_fragments_modelling=2
msfragger.min_matched_fragments=4
msfragger.min_sequence_matches=2
msfragger.minimum_peaks=15
msfragger.minimum_ratio=0.00
msfragger.misc.fragger.clear-mz-hi=0
msfragger.misc.fragger.clear-mz-lo=0
msfragger.misc.fragger.digest-mass-hi=5000
msfragger.misc.fragger.digest-mass-lo=400
msfragger.misc.fragger.enzyme-dropdown-1=stricttrypsin
msfragger.misc.fragger.enzyme-dropdown-2=null
msfragger.misc.fragger.precursor-charge-hi=4
msfragger.misc.fragger.precursor-charge-lo=1
msfragger.misc.fragger.remove-precursor-range-hi=1.5
msfragger.misc.fragger.remove-precursor-range-lo=-1.5
msfragger.misc.slice-db=1
msfragger.num_enzyme_termini=2
msfragger.output_format=tsv_pepXML
msfragger.output_max_expect=50
msfragger.output_report_topN=1
msfragger.output_report_topN_dia1=5
msfragger.output_report_topN_dia2=3
msfragger.override_charge=false
msfragger.precursor_mass_lower=-20
msfragger.precursor_mass_mode=corrected
msfragger.precursor_mass_units=1
msfragger.precursor_mass_upper=20
msfragger.precursor_true_tolerance=20
msfragger.precursor_true_units=1
msfragger.remainder_fragment_masses=203.07937
msfragger.remove_precursor_peak=1
msfragger.report_alternative_proteins=true
msfragger.require_precursor=true
msfragger.restrict_deltamass_to=ST-
msfragger.reuse_dia_fragment_peaks=false
msfragger.run-msfragger=true
msfragger.search_enzyme_cut_1=KR
msfragger.search_enzyme_cut_2=
msfragger.search_enzyme_name_1=stricttrypsin
msfragger.search_enzyme_name_2=null
msfragger.search_enzyme_nocut_1=
msfragger.search_enzyme_nocut_2=
msfragger.search_enzyme_sense_1=C
msfragger.search_enzyme_sense_2=C
msfragger.table.fix-mods=0.0,C-Term Peptide,true,-1; 0.0,N-Term Peptide,true,-1; 0.0,C-Term Protein,true,-1; 0.0,N-Term Protein,true,-1; 0.0,G (glycine),true,-1; 0.0,A (alanine),true,-1; 0.0,S (serine),true,-1; 0.0,P (proline),true,-1; 0.0,V (valine),true,-1; 0.0,T (threonine),true,-1; 57.02146,C (cysteine),true,-1; 0.0,L (leucine),true,-1; 0.0,I (isoleucine),true,-1; 0.0,N (asparagine),true,-1; 0.0,D (aspartic acid),true,-1; 0.0,Q (glutamine),true,-1; 0.0,K (lysine),true,-1; 0.0,E (glutamic acid),true,-1; 0.0,M (methionine),true,-1; 0.0,H (histidine),true,-1; 0.0,F (phenylalanine),true,-1; 0.0,R (arginine),true,-1; 0.0,Y (tyrosine),true,-1; 0.0,W (tryptophan),true,-1; 0.0,B ,true,-1; 0.0,J,true,-1; 0.0,O,true,-1; 0.0,U,true,-1; 0.0,X,true,-1; 0.0,Z,true,-1
msfragger.table.var-mods=15.9949,M,true,3; 42.0106,[^,true,1; 79.96633,STY,false,3; -17.0265,nQnC,false,1; -18.0106,nE,false,1; 4.025107,K,false,3; 6.020129,R,false,3
msfragger.track_zero_topN=0
msfragger.use_all_mods_in_first_search=false
msfragger.use_topN_peaks=300
msfragger.write_calibrated_mzml=false
msfragger.write_uncalibrated_mgf=false
msfragger.zero_bin_accept_expect=0
msfragger.zero_bin_mult_expect=1
opair.activation1=HCD
opair.activation2=ETD
opair.filterOxonium=true
opair.glyco_db=
opair.max_glycans=4
opair.max_isotope_error=2
opair.min_isotope_error=0
opair.ms1_tol=20
opair.ms2_tol=20
opair.oxonium_filtering_file=
opair.oxonium_minimum_intensity=0.05
opair.reverse_scan_order=false
opair.run-opair=false
opair.single_scan_type=false
peptide-prophet.cmd-opts=--nonparam --expectscore --decoyprobs --masswidth 4000.0
peptide-prophet.combine-pepxml=true
peptide-prophet.run-peptide-prophet=true
percolator.cmd-opts=--only-psms --no-terminate --post-processing-tdc
percolator.keep-tsv-files=false
percolator.min-prob=0.5
percolator.run-percolator=false
phi-report.dont-use-prot-proph-file=false
phi-report.filter=--sequential --prot 0.01 --mapmods
phi-report.pep-level-summary=false
phi-report.philosoher-msstats=false
phi-report.print-decoys=false
phi-report.prot-level-summary=true
phi-report.remove-contaminants=false
phi-report.run-report=true
protein-prophet.cmd-opts=--maxppmdiff 2000000
protein-prophet.run-protein-prophet=true
ptmprophet.cmdline=
ptmprophet.run-ptmprophet=false
ptmshepherd.adv_params=false
ptmshepherd.annotation-common=false
ptmshepherd.annotation-custom=false
ptmshepherd.annotation-glyco=true
ptmshepherd.annotation-unimod=false
ptmshepherd.annotation_file=
ptmshepherd.annotation_tol=0.01
ptmshepherd.cap_y_ions=0,203.07937,406.15874,568.21156,730.26438,349.137279
ptmshepherd.decoy_type=1
ptmshepherd.diag_ions=204.086646,186.076086,168.065526,366.139466,144.0656,138.055,512.197375,292.1026925,274.0921325,657.2349,243.026426,405.079246,485.045576,308.09761
ptmshepherd.diagextract_mode=false
ptmshepherd.diagmine_diagMinFoldChange=3.0
ptmshepherd.diagmine_diagMinSpecDiff=25
ptmshepherd.diagmine_fragMinFoldChange=3.0
ptmshepherd.diagmine_fragMinPropensity=12.5
ptmshepherd.diagmine_fragMinSpecDiff=25
ptmshepherd.diagmine_minIonsPerSpec=2
ptmshepherd.diagmine_minPeps=25
ptmshepherd.diagmine_mode=false
ptmshepherd.diagmine_pepMinFoldChange=3.0
ptmshepherd.diagmine_pepMinSpecDiff=25
ptmshepherd.glyco_adducts=
ptmshepherd.glyco_fdr=0.01
ptmshepherd.glyco_isotope_max=3
ptmshepherd.glyco_isotope_min=-1
ptmshepherd.glyco_mode=true
ptmshepherd.glyco_ppm_tol=50
ptmshepherd.glycodatabase=
ptmshepherd.histo_smoothbins=2
ptmshepherd.iontype_a=false
ptmshepherd.iontype_b=true
ptmshepherd.iontype_c=false
ptmshepherd.iontype_x=false
ptmshepherd.iontype_y=true
ptmshepherd.iontype_z=false
ptmshepherd.localization_allowed_res=ST
ptmshepherd.localization_background=4
ptmshepherd.max_adducts=0
ptmshepherd.n_glyco=false
ptmshepherd.normalization-psms=true
ptmshepherd.normalization-scans=false
ptmshepherd.output_extended=false
ptmshepherd.peakpicking_mass_units=0
ptmshepherd.peakpicking_minPsm=10
ptmshepherd.peakpicking_promRatio=0.3
ptmshepherd.peakpicking_width=0.002
ptmshepherd.precursor_mass_units=0
ptmshepherd.precursor_tol=0.01
ptmshepherd.print_decoys=false
ptmshepherd.prob_dhexOx=2,0.5,0.1
ptmshepherd.prob_dhexY=2,0.5
ptmshepherd.prob_neuacOx=2,0.05,0.2
ptmshepherd.prob_neugcOx=2,0.05,0.2
ptmshepherd.prob_phosphoOx=2,0.05,0.2
ptmshepherd.prob_regY=5,0.5
ptmshepherd.prob_sulfoOx=2,0.05,0.2
ptmshepherd.remainder_masses=203.07937
ptmshepherd.remove_glycan_delta_mass=true
ptmshepherd.run-shepherd=true
ptmshepherd.spectra_maxfragcharge=2
ptmshepherd.spectra_ppmtol=20
ptmshepherd.varmod_masses=
quantitation.run-label-free-quant=false
run-psm-validation=true
run-validation-tab=true
saintexpress.fragpipe.cmd-opts=
saintexpress.max-replicates=10
saintexpress.run-saint-express=false
saintexpress.virtual-controls=100
speclibgen.easypqp.extras.max_delta_ppm=15
speclibgen.easypqp.extras.max_delta_unimod=0.02
speclibgen.easypqp.extras.rt_lowess_fraction=0
speclibgen.easypqp.fragment.a=false
speclibgen.easypqp.fragment.b=true
speclibgen.easypqp.fragment.c=false
speclibgen.easypqp.fragment.x=false
speclibgen.easypqp.fragment.y=true
speclibgen.easypqp.fragment.z=false
speclibgen.easypqp.im-cal=Automatic selection of a run as reference IM
speclibgen.easypqp.neutral_loss=false
speclibgen.easypqp.rt-cal=noiRT
speclibgen.easypqp.select-file.text=
speclibgen.easypqp.select-im-file.text=
speclibgen.keep-intermediate-files=false
speclibgen.run-speclibgen=false
tab-run.delete_calibrated_mzml=false
tab-run.delete_temp_files=false
tab-run.sub_mzml_prob_threshold=0.5
tab-run.write_sub_mzml=false
tmtintegrator.add_Ref=-1
tmtintegrator.aggregation_method=0
tmtintegrator.allow_overlabel=true
tmtintegrator.allow_unlabeled=true
tmtintegrator.best_psm=true
tmtintegrator.channel_num=TMT-6
tmtintegrator.dont-run-fq-lq=false
tmtintegrator.glyco_qval=0.01
tmtintegrator.groupby=0
tmtintegrator.log2transformed=true
tmtintegrator.max_pep_prob_thres=0
tmtintegrator.min_ntt=0
tmtintegrator.min_pep_prob=0.9
tmtintegrator.min_percent=0.05
tmtintegrator.min_purity=0.5
tmtintegrator.min_site_prob=-1
tmtintegrator.mod_tag=none
tmtintegrator.ms1_int=true
tmtintegrator.outlier_removal=true
tmtintegrator.print_RefInt=false
tmtintegrator.prot_exclude=none
tmtintegrator.prot_norm=0
tmtintegrator.psm_norm=false
tmtintegrator.quant_level=2
tmtintegrator.ref_tag=Bridge
tmtintegrator.run-tmtintegrator=false
tmtintegrator.tolerance=20
tmtintegrator.top3_pep=true
tmtintegrator.unique_gene=0
tmtintegrator.unique_pep=false
tmtintegrator.use_glycan_composition=true
workflow.description=<p style\="margin-top\: 0">For CID/HCD search of enriched O-glycopeptides</p>
workflow.input.data-type.im-ms=false
workflow.input.data-type.regular-ms=true
workflow.saved-with-ver=20.0-build5
