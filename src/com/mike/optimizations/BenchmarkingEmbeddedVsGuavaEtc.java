package com.mike.optimizations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.amazon.device.planning.adept.redshift.jooq.tables.pojos.VdpStagingRetailerVdpsPojo;
import com.amazon.device.planning.vdp.service.pojo.DemandEntry;

import lombok.Data;
import lombok.NonNull;

public class BenchmarkingEmbeddedVsGuavaEtc {

    public static void inspectAllLegacyRecordsMultipleTimes(@NonNull List<Record> legacy,
                                                            @NonNull Map<String, List<Record>> v2) {

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
                                .equals(v2Record.getAsin()))
                        .collect(Collectors.toList());

                // Do nothing
            }
        }
    }

    public static List<Record> getLegacyRecords() {
        final int recordsPerAsin = 100;
        final int asinsPerDay = 100;
        final int numberOfDays = 100;

        int retailerCount = 0;
        
        List<Record> legacy = new ArrayList<>();
        for (int day = 0; day < numberOfDays; day++) {
            for (int asin = 0; asin < asinsPerDay; asin++) {
                
                private String demandSource;
                
                for (int recordPerAsinPerDay = 0; recordPerAsinPerDay < recordsPerAsin; recordPerAsinPerDay++) {
                    final String asinToUse = UUID.randomUUID().toString();
                    final 
                    
                    Record record = new Record();
                    S
                }
            }
        }
        
        return legacy;
    }

    public static void main(String[] args) {

    }

    @Data
    public static class Record {
        private String asin;
        private String modelName;
        private LocalDate date;
        private int retailer;
    }
}
