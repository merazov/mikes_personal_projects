package com.mike.models_validation.legacy_records_vs_v2_s3_file;

import static java.lang.Double.parseDouble;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

public class ValidatingLegacyRecordsVsV2S3File {

    private static final String DDMG = "US_OFFLINE_AUCC_MOBILE_BBY";

    private static DemandRecordsKey getKey(V2DemandEntry pojo) {
        return new DemandRecordsKey(pojo.getToCountry(),
                                    pojo.getChannel(),
                                    pojo.getDeviceType(),
                                    pojo.getDate(),
                                    pojo.getAsin(),
                                    pojo.getRetailerId(),
                                    pojo.getPromoDescription(),
                                    pojo.getDeviceColor());
    }

    public static void main(String[] args) throws IOException {
        List<V2DemandEntry> legacyPojos = getPojos("src/com/mike/models_validation/legacy_records_vs_v2_s3_file/legacy_records_" + DDMG + ".csv");

        List<V2DemandEntry> v2Pojos = getPojos("src/com/mike/models_validation/legacy_records_vs_v2_s3_file/demandEntriesAfterRunningChildWorkflow_" + DDMG + "_v2.csv");

        System.out.println("legacyPojosSize=" + legacyPojos.size() + " v2PojosSize=" + v2Pojos.size());

        int numberOfMatches = 0;
        int numberOfSecondListMismatches = 0;
        double baselinesMape = 0;
        double vdpUnitsMape = 0;
        double vdpPlusUnitsMape = 0;
        double approvedPromoUnitsMape = 0;
        double unApprovedPromoUnitsMape = 0;

        final List<V2DemandEntry> firstListOfPojos = v2Pojos;
        final List<V2DemandEntry> secondListOfPojos = legacyPojos;
        for (V2DemandEntry firstPojo : firstListOfPojos) {
            // System.out.println("[legacy pojo] legacyPojo: " + legacyPojo);

            DemandRecordsKey firstKey = getKey(firstPojo);

            List<V2DemandEntry> containerOfAllMatchesFromSecondList = new ArrayList<>();
            for (V2DemandEntry secondPojo : secondListOfPojos) {
                // System.out.println("[v2 pojo] legacyPojo: " + v2Pojo);

                DemandRecordsKey secondKey = getKey(secondPojo);

                // System.out.println("legacy key=" + legacyKey + " v2Key=" + v2Key);

                if (firstKey.equals(secondKey)) {
                    containerOfAllMatchesFromSecondList.add(secondPojo);
                }
            } //< end of iteration for second list by a single legacy record

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
                for (V2DemandEntry match : containerOfAllMatchesFromSecondList) {
                    if (containerOfAllMatchesFromSecondList.size() > 1) {
                        //System.out.println("---match=" + match);
                    }
                    cumBaselineUnits += Double.parseDouble(match.getBaselineUnits());
                    cumVdpUnits += Double.parseDouble(match.getVdpUnits());
                    cumVdpPlusUnits += Double.parseDouble(match.getVdpPlusUnits());
                    cumApprovedPromoUnitsUnits += Double.parseDouble(match.getApprovedPromoUnits());
                    cumUnApprovedPromoUnits += Double.parseDouble(match.getUnapprovedPromoUnits());
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

        System.out.println("legacyPojosSize=" + legacyPojos.size() + " v2PojosSize=" + v2Pojos
                .size() + " numberOfMatches=" + numberOfMatches + " numberOfV2Mismatches=" + numberOfSecondListMismatches + " baselinesMape=" + baselinesMape + " vdpUnitsMape=" + vdpUnitsMape + " vdpPlusUnitsMape=" + vdpPlusUnitsMape + " approvedPromoUnitsMape=" + approvedPromoUnitsMape + " unApprovedPromoUnitsMape=" + unApprovedPromoUnitsMape);
    }

    private static double getMape(String expected, String measured) {
        if (parseDouble(expected) == parseDouble(measured)) {
            return 0;
        }

        return Math.abs((parseDouble(expected) - parseDouble(measured)) / parseDouble(expected));
    }

    private static List<V2DemandEntry> getPojos(@NonNull final String fileName) throws IOException {
        List<V2DemandEntry> allDemandRecords = new ArrayList<>();
        try (ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(fileName), CsvPreference.STANDARD_PREFERENCE)) {
            final String[] header = beanReader.getHeader(true);

            V2DemandEntry demand;
            while ((demand = beanReader.read(V2DemandEntry.class, header)) != null) {
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
        private final String retailerId;
        private final String promoDescription;
        private final String deviceColor;
    }
}
