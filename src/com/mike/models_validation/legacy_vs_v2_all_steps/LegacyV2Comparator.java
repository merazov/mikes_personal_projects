package com.mike.models_validation.legacy_vs_v2_all_steps;

import static java.lang.Double.parseDouble;
import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class LegacyV2Comparator {

    private static final String PATH_SEPARATOR = "/";
    private static final String PATH_TO_MODELS_DATA = "src/com/mike/models_validation/legacy_vs_v2_all_steps/data";

    public static void main(String[] args) throws IOException {
        LegacyV2Comparator comparator = new LegacyV2Comparator();
        InputRecords<?, ?> inputRecords = comparator.readFiles("DE_ONLINE_EINK", Steps.ASIN_MIX, PojoTypes.TRAINING);
        compare(comparator, inputRecords, PojoTypes.TRAINING);

        //comparator.compareOneToOne(inputRecords);
        //src/com/mike/models_validation/legacy_vs_v2_all_steps/data/US_ONLINE_AUCC_KYANITE/v2_US_ONLINE_AUCC_KYANITE_demandEntriesAfterAsinMixes.csv
        //src/com/mike/models_validation/legacy_vs_v2_all_steps/data/US_ONLINE_AUCC_KYANITE/v2_US_ONLINE_AUCC_KYANITE_demandEntriesAfterAsinMixes.csv
        //src/com/mike/models_validation/legacy_vs_v2_all_steps/data/US_ONLINE_AUCC_KYANITE/v2_US_ONLINE_AUCC_KYANITE_demandEntriesAfterAsinMixes.csv
        //src/com/mike/models_validation/legacy_vs_v2_all_steps/data/US_ONLINE_AUCC_KYANITE/v2_US_ONLINE_AUCC_KYANITE_demandEntriesAfterAsinMixes.csv
        
        // 1. Read both files and convert each line to an object
    }

    private void compareTrainingRecords(InputRecords<TrainingPojo, TrainingPojo> inputRecords) {
        System.out.println("legacy size=" + inputRecords.getLegacyPojos().size());
        System.out.println("v2 size=" + inputRecords.getV2Pojos().size());

        for (TrainingPojo v2Pojo : inputRecords.getV2Pojos()) {
            for (TrainingPojo legacyPojo : inputRecords.getLegacyPojos()) {
                if (v2Pojo.getKey().equals(legacyPojo.getKey())) {
        
                    System.out.println("legacy=" + legacyPojo);
                    System.out.println("v2=" + v2Pojo);
                    
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_blackfriday(), v2Pojo.getSeasonal_blackfriday());
                    System.out.println("1");
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_blackfriday_n1_n5(), v2Pojo.getSeasonal_blackfriday_n1_n5());
                    System.out.println("2");
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_blackfriday_n6_n12(), v2Pojo.getSeasonal_blackfriday_n6_n12());
                    System.out.println("3");
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_blackfriday_p1_p2(), v2Pojo.getSeasonal_blackfriday_p1_p2());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_christmasday_n1_n7(), v2Pojo.getSeasonal_christmasday_n1_n7());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_christmasday_n8_n14(), v2Pojo.getSeasonal_christmasday_n8_n14());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_christmaseve(), v2Pojo.getSeasonal_christmaseve());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_cybermonday(), v2Pojo.getSeasonal_cybermonday());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_cybermonday_0_p6(), v2Pojo.getSeasonal_cybermonday_0_p6());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_cybermonday_p14_p20(), v2Pojo.getSeasonal_cybermonday_p14_p20());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_cybermonday_p7_p13(), v2Pojo.getSeasonal_cybermonday_p7_p13());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_easterday_0_n2(), v2Pojo.getSeasonal_easterday_0_n2());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_easterday_n3_n7(), v2Pojo.getSeasonal_easterday_n3_n7());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_jan1_p1_p5(), v2Pojo.getSeasonal_jan1_p1_p5());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_momsday_n7_n13(), v2Pojo.getSeasonal_momsday_n7_n13());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_weekday(), v2Pojo.getSeasonal_weekday());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_yearly_fourier_cosine_1(), v2Pojo.getSeasonal_yearly_fourier_cosine_1());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_yearly_fourier_cosine_2(), v2Pojo.getSeasonal_yearly_fourier_cosine_2());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_yearly_fourier_cosine_3(), v2Pojo.getSeasonal_yearly_fourier_cosine_3());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_yearly_fourier_cosine_6(), v2Pojo.getSeasonal_yearly_fourier_cosine_6());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_yearly_fourier_sine_1(), v2Pojo.getSeasonal_yearly_fourier_sine_1());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_yearly_fourier_sine_3(), v2Pojo.getSeasonal_yearly_fourier_sine_3());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_yearly_fourier_sine_4(), v2Pojo.getSeasonal_yearly_fourier_sine_4());
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonal_yearly_fourier_sine_6(), v2Pojo.getSeasonal_yearly_fourier_sine_6());
                    System.out.println("1016");
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonality_prime_day_m1w_start_from_2015(), v2Pojo.getSeasonality_prime_day_m1w_start_from_2015());
                    System.out.println("1017");
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonality_prime_day_p1w_start_from_2015(), v2Pojo.getSeasonality_prime_day_p1w_start_from_2015());
                    System.out.println("1018");
                    compareTwoStringsAsDoubles(legacyPojo.getSeasonality_prime_day_start_from_2015(), v2Pojo.getSeasonality_prime_day_start_from_2015());
                    System.out.println("1019");
                    compareTwoStringsAsDoubles(legacyPojo.getTrend_linear_20121125(), v2Pojo.getTrend_linear_20121125());
                    System.out.println("1020");
                    compareTwoStringsAsDoubles(legacyPojo.getDependent(), v2Pojo.getDependent());
                    System.out.println("legacy:" + legacyPojo.getSeasonal_yearly_fourier_sine_4() + " v2=" + v2Pojo.getSeasonal_yearly_fourier_sine_4());
                    
                }
            }
        }
    }
    
    private void compareTwoStringsAsDoubles(String legacy, String v2) {
        assertEquals(Double.parseDouble(legacy), Double.parseDouble(v2), 0.1);
    }
    
    @Data
    public static class TrainingPojo {
        @Getter
        private String key;
        
        public void setkey(String key) {
            this.key = key;
        }
        
        private String seasonal_yearly_fourier_cosine_1;
        private String seasonal_yearly_fourier_sine_3;
        private String seasonal_christmasday_n1_n7;
        private String seasonal_cybermonday_0_p6;
        private String seasonal_jan1_p1_p5;
        private String seasonal_blackfriday_p1_p2;
        private String seasonal_weekday;
        private String seasonal_yearly_fourier_sine_1;
        private String Seasonal_cybermonday_p14_p20;
        private String seasonal_blackfriday;
        private String seasonal_yearly_fourier_sine_6;
        private String seasonality_prime_day_p1w_start_from_2015;
        private String seasonal_cybermonday;
        private String seasonal_christmasday_n8_n14;
        private String seasonal_yearly_fourier_cosine_3;
        private String seasonal_yearly_fourier_cosine_6;
        private String seasonal_yearly_fourier_cosine_2;
        private String seasonality_prime_day_m1w_start_from_2015;
        private String trend_linear_20121125;
        private String seasonal_cybermonday_p7_p13;
        private String seasonal_easterday_n3_n7;
        private String seasonal_blackfriday_n1_n5;
        private String seasonal_yearly_fourier_sine_4;
        private String seasonal_momsday_n7_n13;
        private String seasonality_prime_day_start_from_2015;
        private String seasonal_easterday_0_n2;
        private String seasonal_christmaseve;
        private String price_ratio_de_online_eink_kindle_v2;
        private String seasonal_blackfriday_n6_n12;
        private String dependent;
        private String actuals;
    }
    
    @SuppressWarnings("unchecked")
    private static <T, V> void compare(LegacyV2Comparator comparator, InputRecords<T, V> inputRecords, PojoTypes type) {
        if (type == PojoTypes.DEMAND) {
            comparator.compareOneToOne((InputRecords<LegacyDemandEntry, V2DemandEntry>)inputRecords);
        } else if (type == PojoTypes.TRAINING) {
            comparator.compareTrainingRecords((InputRecords<TrainingPojo, TrainingPojo>)inputRecords);
        } else {
            throw new RuntimeException("Pojo type not recognized!");
        }
    }

    @Getter
    @RequiredArgsConstructor
    private static enum Steps {
        //ASIN_MIX("demand_program_config_mixed.csv", "demandEntriesAfterAsinMixes.csv");
        ASIN_MIX("training_input.csv", "training_input_dpfs_formatted.csv");
        
        private final String legacyFileName;
        private final String v2FileName;
    }

    @SuppressWarnings("unchecked")
    private <T, V> InputRecords<T, V> readFiles(@NonNull final String modelEvaluated, @NonNull final Steps step,
                                                @NonNull final PojoTypes type) throws IOException {

        final String legacyPath = StringUtils.join(new String[] {PATH_TO_MODELS_DATA, modelEvaluated, "legacy_" + modelEvaluated + "_" + step.getLegacyFileName()}, PATH_SEPARATOR);
        final String v2Path = StringUtils.join(new String[] {PATH_TO_MODELS_DATA, modelEvaluated, "v2_" + modelEvaluated + "_" + step.getV2FileName()}, PATH_SEPARATOR);
        System.out.println("v2Path=" + v2Path);
        
        if (type == PojoTypes.DEMAND) {
            
            List<LegacyDemandEntry> legacyPojos = getPojos(legacyPath, LegacyDemandEntry.class);
            List<V2DemandEntry> v2Pojos = getPojos(v2Path, V2DemandEntry.class);
            
            return (InputRecords<T, V>) new InputRecords<LegacyDemandEntry, V2DemandEntry>(legacyPojos, v2Pojos);
        } else if (type == PojoTypes.TRAINING)  {
            List<TrainingPojo> legacyPojos = getPojos(legacyPath, TrainingPojo.class);
            List<TrainingPojo> v2Pojos = getPojos(v2Path, TrainingPojo.class);
            
            return (InputRecords<T, V>) new InputRecords<TrainingPojo, TrainingPojo>(legacyPojos, v2Pojos);
        } else {
            throw new RuntimeException("Pojo type not recognized!");
        }
    }

    @Data
    public static class InputRecords<LEGACY, V2> {
        private final List<LEGACY> legacyPojos;
        private final List<V2> v2Pojos;
    }

    private static <T> DemandRecordsKey getKey(T pojo) {
        if (pojo instanceof V2DemandEntry) {
            V2DemandEntry v2DemandEntry = (V2DemandEntry) pojo;

            return new DemandRecordsKey(v2DemandEntry.getToCountry(),
                                        v2DemandEntry.getChannel(),
                                        v2DemandEntry.getDeviceType(),
                                        v2DemandEntry.getDate(),
                                        v2DemandEntry.getAsin(),
                                        //v2DemandEntry.getRetailerId(),
                                        v2DemandEntry.getPromoDescription(),
                                        v2DemandEntry.getDeviceColor());
        } else if (pojo instanceof LegacyDemandEntry) {
            LegacyDemandEntry legacyDemandEntry = (LegacyDemandEntry) pojo;
            
            return new DemandRecordsKey(legacyDemandEntry.getTo_country(),
                                        legacyDemandEntry.getChannel(),
                                        legacyDemandEntry.getDevice_type(),
                                        legacyDemandEntry.getDate(),
                                        legacyDemandEntry.getAsin(),
                                        //legacyDemandEntry.getRetailer_id(),
                                        legacyDemandEntry.getPromoDescription(),
                                        legacyDemandEntry.getDevice_color());
        } else {
            throw new RuntimeException("cannot work with this class:" + pojo.toString());
        }
    }

    private void compareOneToOne(InputRecords<LegacyDemandEntry, V2DemandEntry> inputRecords) {
        
        int numberOfMatches = 0;
        int numberOfSecondListMismatches = 0;
        double baselinesMape = 0;
        double vdpUnitsMape = 0;
        double vdpPlusUnitsMape = 0;
        double approvedPromoUnitsMape = 0;
        double unApprovedPromoUnitsMape = 0;
        
        final List<V2DemandEntry> firstListOfPojos = inputRecords.getV2Pojos();
        final List<LegacyDemandEntry> secondListOfPojos = inputRecords.getLegacyPojos();
        for (V2DemandEntry firstPojo : firstListOfPojos) {
            // System.out.println("[legacy pojo] legacyPojo: " + legacyPojo);

            DemandRecordsKey firstKey = getKey(firstPojo);

            List<LegacyDemandEntry> containerOfAllMatchesFromSecondList = new ArrayList<>();
            for (LegacyDemandEntry secondPojo : secondListOfPojos) {
                // System.out.println("[v2 pojo] legacyPojo: " + v2Pojo);

                DemandRecordsKey secondKey = getKey(secondPojo);

                if (LocalDate.parse(secondPojo.getDate(), DateTimeFormatter.ISO_DATE).equals(LocalDate.of(2018, 10, 12))) {
                    //System.out.println("secondKey for legacyPojo:" + secondKey);
                }

                if (firstKey.equals(secondKey)) {
                    System.out.println("firstKey=" + firstKey + " secondKey=" + secondKey);
                    containerOfAllMatchesFromSecondList.add(secondPojo);
                }
            } //< end of iteration for second list by a single legacy record

            //System.out.println("number of legacy matches=" + containerOfAllMatchesFromSecondList.size());
            
            if (!containerOfAllMatchesFromSecondList.isEmpty()) {
                numberOfMatches++;

                double cumBaselineUnits = 0;
                double cumVdpUnits = 0;
                double cumVdpPlusUnits = 0;
                double cumApprovedPromoUnitsUnits = 0;
                double cumUnApprovedPromoUnits = 0;
                if (containerOfAllMatchesFromSecondList.size() > 1) {
                    //System.out.println("***firstPojo=" + firstPojo);
                }
                for (LegacyDemandEntry match : containerOfAllMatchesFromSecondList) {
                    if (containerOfAllMatchesFromSecondList.size() > 1) {
                        //System.out.println("---match=" + match);
                    }
                    cumBaselineUnits += Double.parseDouble(match.getBaseline_units());
                    cumVdpUnits += Double.parseDouble(match.getVdp_units());
                    cumVdpPlusUnits += Double.parseDouble(match.getVdp_plus_units());
                    cumApprovedPromoUnitsUnits += Double.parseDouble(match.getPromo_units_app());
                    cumUnApprovedPromoUnits += Double.parseDouble(match.getPromo_units_unapp());
                }

                baselinesMape += getMape(firstPojo.getBaselineUnits(), String.valueOf(cumBaselineUnits));
                vdpUnitsMape += getMape(firstPojo.getVdpUnits(), String.valueOf(cumVdpUnits));
                vdpPlusUnitsMape += getMape(firstPojo.getVdpPlusUnits(), String.valueOf(cumVdpPlusUnits));
                approvedPromoUnitsMape += getMape(firstPojo.getApprovedPromoUnits(), String.valueOf(cumApprovedPromoUnitsUnits));
                unApprovedPromoUnitsMape += getMape(firstPojo.getUnapprovedPromoUnits(), String.valueOf(cumUnApprovedPromoUnits));

                /*
                 * if (legacyPojo.getDemandSource().contains("promotions")) {
                 * System.out.println("[debug promos] legacyPojo=" + legacyPojo +
                 * " v2Pojo=" + v2Pojo);
                 * }
                 */

                //if (getMape(firstPojo.getUnapprovedPromoUnits(), secondPojo.getUnapprovedPromoUnits()) > 0.05) {
                    // System.out.println("[debug unappPromo] legacy:" + firstPojo.getUnapprovedPromoUnits() + " v2:" + secondPojo
                    // .getUnapprovedPromoUnits() + " mape=" + getMape(firstPojo.getUnapprovedPromoUnits(),
                    // secondPojo.getUnapprovedPromoUnits()));
                //}
            } else {
                numberOfSecondListMismatches++;
            }
        }

        baselinesMape = baselinesMape / numberOfMatches;
        vdpUnitsMape = vdpUnitsMape / numberOfMatches;
        vdpPlusUnitsMape = vdpPlusUnitsMape / numberOfMatches;
        approvedPromoUnitsMape = approvedPromoUnitsMape / numberOfMatches;
        unApprovedPromoUnitsMape = unApprovedPromoUnitsMape / numberOfMatches;

        System.out.println("legacyPojosSize=" + inputRecords.getLegacyPojos().size() + " v2PojosSize=" + inputRecords.getV2Pojos().size() +
                " numberOfMatches=" + numberOfMatches + " numberOfV2Mismatches=" + numberOfSecondListMismatches + " baselinesMape=" + baselinesMape + " vdpUnitsMape=" + vdpUnitsMape + " vdpPlusUnitsMape=" + vdpPlusUnitsMape + " approvedPromoUnitsMape=" + approvedPromoUnitsMape + " unApprovedPromoUnitsMape=" + unApprovedPromoUnitsMape);
    }

    private static double getMape(String expected, String measured) {
        if (parseDouble(expected) == parseDouble(measured)) {
            return 0;
        }

        return Math.abs((parseDouble(expected) - parseDouble(measured)) / parseDouble(expected));
    }
    
    private static <T> List<T> getPojos(@NonNull final String fileName, @NonNull final Class<T> clazz) throws IOException {
        List<T> allDemandRecords = new ArrayList<>();
        try (ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(fileName), CsvPreference.STANDARD_PREFERENCE)) {
            final String[] header = beanReader.getHeader(true);

            T demand;
            while ((demand = beanReader.read(clazz, header)) != null) {
                // System.out.println("demand=" + demand);
                allDemandRecords.add(demand);
            }
        }

        return allDemandRecords;
    }
    
    @Data
    @EqualsAndHashCode
    static class DemandRecordsKey {
        private final String country;
        private final String channel;
        private final String deviceType;
        private final String date;
        private final String asin;
    //    private final String retailerId;
        private final String promoDescription;
        private final String deviceColor;
    }
    
    private static enum PojoTypes {
        DEMAND,
        TRAINING
    }
}
