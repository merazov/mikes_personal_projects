package com.mike.optimizations;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;

import lombok.Data;
import lombok.NonNull;

public class BenchmarkingEmbeddedVsGuavaEtc {

    private static final int DAYS = 2000;
    private static final int ASINS_PER_DAY = 1000;
    private static final int RETAILERS_PER_ASIN = 2;
    private static final String[] ASINS;
    private static final String[] RETAILERS;

    private static final String DEFAULT_MODEL = "US_ONLINE_TABLET_KET";

    static {
        ASINS = new String[ASINS_PER_DAY];

        for (int i = 0; i < ASINS_PER_DAY; i++) {
            ASINS[i] = UUID.randomUUID().toString();
        }

        RETAILERS = new String[RETAILERS_PER_ASIN];
        RETAILERS[0] = "Best Buy";
        RETAILERS[1] = "Amazon";
    }

    public static Map<String, List<Record>> getV2Records(String model, List<Record> records) {
        return ImmutableMap.of(model, records);
    }

    public static List<Record> getLegacyRecords() {
        ImmutableList.Builder<Record> legacyBld = new ImmutableList.Builder();
        final LocalDate initialDate = LocalDate.of(2018, 1, 1);

        for (int day = 0; day < DAYS; day++) {
            LocalDate today = initialDate.plusDays(day);

            for (int k = 0; k < ASINS_PER_DAY; k++) {
                legacyBld.add(new Record(today, DEFAULT_MODEL, ASINS[k], RETAILERS[0]));
                legacyBld.add(new Record(today, DEFAULT_MODEL, ASINS[k], RETAILERS[1]));
            }
        }
        return legacyBld.build();
    }

    public static void main(String[] args) {
        // Run legacy code
        long startEpoch = System.currentTimeMillis();
        List<Record> legacyRecords = getLegacyRecords();
        Map<String, List<Record>> v2 = getV2Records(DEFAULT_MODEL, legacyRecords);
 //       int matches = inspectAllLegacyRecordsMultipleTimes(legacyRecords, v2);
 //       assertEquals(DAYS * ASINS_PER_DAY * RETAILERS_PER_ASIN, matches);
        long endEpoch = System.currentTimeMillis();
 //       System.out.println("duration for crappy code=" + (endEpoch - startEpoch) + " matches=" + matches);

        int matches;
        // Concatenation of Strings as key using HashMap
        Map<String, List<Record>> secondContainer = new HashMap<>();
        for (Record record : legacyRecords) {
            String key = record.getModelName() + record.getDate() + record.getAsin() + record.getRetailer();
            List<Record> accm = secondContainer.computeIfAbsent(key, model -> new ArrayList<>());
            accm.add(record);
        }
        startEpoch = System.currentTimeMillis();
        matches = alg2(secondContainer, v2);
        endEpoch = System.currentTimeMillis();
        System.out.println("duration for alg2 code=" + (endEpoch - startEpoch) + " matches=" + matches);
        
        // Record as key using HashMap
        Map<Record, List<Record>> thirdContainer = new HashMap<>();
        for (Record record : legacyRecords) {
            List<Record> accm = thirdContainer.computeIfAbsent(record, model -> new ArrayList<>());
            accm.add(record);
        }
        startEpoch = System.currentTimeMillis();
        matches = alg3(thirdContainer, v2);
        endEpoch = System.currentTimeMillis();
        System.out.println("duration for alg3 code=" + (endEpoch - startEpoch) + " matches=" + matches);
        
        // Using Guava multimap with Record as key
        Multimap<Record, Record> fourthContainer = ArrayListMultimap.create();
        for (Record record : legacyRecords) {
            fourthContainer.put(record, record);
        }
        startEpoch = System.currentTimeMillis();
        matches = alg4(fourthContainer, v2);
        endEpoch = System.currentTimeMillis();
        System.out.println("duration for alg4 code=" + (endEpoch - startEpoch) + " matches=" + matches);
        
        // Using Immutablemap with Record as key
        ImmutableMap.Builder<Record, Record> fifthContainer = ImmutableMap.builder();
        for (Record record : legacyRecords) {
            fifthContainer.put(record, record);
        }
        startEpoch = System.currentTimeMillis();
        matches = alg4(fourthContainer, v2);
        endEpoch = System.currentTimeMillis();
        System.out.println("duration for alg5 code=" + (endEpoch - startEpoch) + " matches=" + matches);
    }

    public static int alg4(@NonNull Multimap<Record, Record> legacy,
                           @NonNull Map<String, List<Record>> v2) {
        
        int numberOfMatches = 0;

        for (Map.Entry<String, List<Record>> v2ModelEntries : v2.entrySet()) {
            // for each model
            List<Record> v2Records = v2ModelEntries.getValue();

            for (Record v2Record : v2Records) {
                Collection<Record> legacyRecordsForCorrespondingV2Record = legacy.get(v2Record);
                numberOfMatches = numberOfMatches + legacyRecordsForCorrespondingV2Record.size();

                // System.out.println("Found " + legacyRecordsForCorrespondingV2Record.size());

                // Do nothing
            }
        }

        return numberOfMatches;
    }
    
    public static int alg3(@NonNull Map<Record, List<Record>> legacy,
                           @NonNull Map<String, List<Record>> v2) {
        
        int numberOfMatches = 0;

        for (Map.Entry<String, List<Record>> v2ModelEntries : v2.entrySet()) {
            // for each model
            List<Record> v2Records = v2ModelEntries.getValue();

            for (Record v2Record : v2Records) {
                List<Record> legacyRecordsForCorrespondingV2Record = legacy.get(v2Record);
                numberOfMatches = numberOfMatches + legacyRecordsForCorrespondingV2Record.size();

                // System.out.println("Found " + legacyRecordsForCorrespondingV2Record.size());

                // Do nothing
            }
        }

        return numberOfMatches;
    }
    
    public static int alg2(@NonNull Map<String, List<Record>> legacy,
                           @NonNull Map<String, List<Record>> v2) {
        
        int numberOfMatches = 0;

        for (Map.Entry<String, List<Record>> v2ModelEntries : v2.entrySet()) {
            // for each model
            String modelName = v2ModelEntries.getKey();
            List<Record> v2Records = v2ModelEntries.getValue();

            for (Record v2Record : v2Records) {

                String key = v2Record.getModelName() + v2Record.getDate() + v2Record.getAsin() + v2Record.getRetailer();
                
                List<Record> legacyRecordsForCorrespondingV2Record = legacy.get(key);
                
                numberOfMatches = numberOfMatches + legacyRecordsForCorrespondingV2Record.size();

                // System.out.println("Found " + legacyRecordsForCorrespondingV2Record.size());

                // Do nothing
            }
        }

        return numberOfMatches;
    }
    
    public static int inspectAllLegacyRecordsMultipleTimes(@NonNull List<Record> legacy,
                                                           @NonNull Map<String, List<Record>> v2) {

        int numberOfMatches = 0;

        for (Map.Entry<String, List<Record>> v2ModelEntries : v2.entrySet()) {
            // for each model
            String modelName = v2ModelEntries.getKey();
            List<Record> v2Records = v2ModelEntries.getValue();

            List<Record> legacyRecordsForThisModel = legacy
                    .stream()
                    .filter(entry -> entry.getModelName().startsWith(modelName))
                    .collect(Collectors.toList());

            for (Record v2Record : v2Records) {

                List<Record> legacyRecordsForCorrespondingV2Record = legacyRecordsForThisModel
                        .stream()
                        .filter(legacyRecord -> legacyRecord.getDate().isEqual(v2Record.getDate()) && legacyRecord.getAsin()
                                .equals(v2Record.getAsin()) && legacyRecord.getRetailer().equals(v2Record.getRetailer()))
                        .collect(Collectors.toList());

                numberOfMatches = numberOfMatches + legacyRecordsForCorrespondingV2Record.size();

                // System.out.println("Found " + legacyRecordsForCorrespondingV2Record.size());

                // Do nothing
            }
        }

        return numberOfMatches;
    }

    @Data
    public static class Record {
        private final LocalDate date;
        private final String modelName;
        private final String asin;
        private final String retailer;
    }
}
