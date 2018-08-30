package com.mike.ngf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import com.amazon.device.planning.adept.mysql.jooq.kindledistplan.tables.pojos.DeviceDemandModelingGroupsPojo;
import com.amazon.device.planning.vdp.service.cache.MixesCache;
import com.amazon.device.planning.vdp.service.pojo.EventPojo;
import com.amazon.device.planning.vdp.service.pojo.MixEvent;
import com.amazon.device.planning.vdp.service.pojo.mixes.Spec;
import com.amazon.device.planning.vdp.service.visitors.VdpEventsVisitor;
import com.google.common.base.CaseFormat;
import com.google.common.base.Charsets;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class VdpEventsVisitorTest {
    
private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");
    
    private static final int COLUMN_FOR_DDMG_ID = 0;
    private static final int COLUMN_FOR_DATE = 1;
    private static final String DELIMITER = "---";
    private static final int SCALE = 3;

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                @Override
                public LocalDate deserialize(JsonElement json, Type arg1,
                                             JsonDeserializationContext arg2) throws JsonParseException {
                    try {
                        return LocalDate.parse(json.getAsString());
                    } catch (DateTimeParseException e) {
                        throw new JsonParseException(e);
                    }
                }
            }).registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                @Override
                public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
                    return new JsonPrimitive(src.toString());
                }
            }).create();

    private List<String> traces;
    private List<String> outputAfterTraces;
    // Container for pre-gathered output
    private Table<Integer, LocalDate, List<MixGroupForTesting>> expectedOutput; //<This is actually a list of 'mixGroups', i.e., Spec to BigDecimal map
    
    private enum MixesCacheTypes {
        PROGRAM("program_actuals_mixes"),
        CONFIG("config_actuals_mixes");

        MixesCacheTypes(String name) {
            this.name = name;
        }

        @Getter
        private final String name;
    }

    private Table<Integer, String, MixesCache> ddmgTypeMixesCache = HashBasedTable.create();

    private VdpEventsVisitor vdpEventsVisitor;
    
    @Setter
    @Getter
    @NoArgsConstructor
    public static class MixGroupForTesting {
        private LocalDate startDate;
        private Integer countryId;
        private Integer channelId;
        private Integer deviceTypeId;
        private Integer programId;
        private Map<Spec, BigDecimal> mixGroups;
        private BigDecimal mixPercentage;
        private Integer retailerId;
        private Integer storageId;
        private Integer carrierId;
        private Integer bundleId;
        private Integer colorId;
    }

    @Data
    @NoArgsConstructor
    public static class Trace {
        private static final int COLUMN_FOR_CLASS_NAME = 2;
        private static final int COLUMN_FOR_TRACE_JSON = 3;
        
        private int ddmg;
        private LocalDate applicationDate;
        private Class<EventPojo> clazz;
        private String json;
        private EventPojo pojo;

        @SuppressWarnings("unchecked")
        public Trace(String line) throws ClassNotFoundException {
            String[] elements = line.split(DELIMITER);
            ddmg = Integer.valueOf(elements[COLUMN_FOR_DDMG_ID]);
            applicationDate = LocalDate.parse(elements[COLUMN_FOR_DATE]);
            clazz = (Class<EventPojo>) Class.forName(elements[COLUMN_FOR_CLASS_NAME]);
            json = elements[COLUMN_FOR_TRACE_JSON];
            pojo = GSON.fromJson(json, clazz);
        }
    }
    
    @Before
    public void init() throws IOException {
        traces = FileUtils.readLines(new File("tst/com/amazon/device/planning/vdp/service/visitors/events_visitor_data.txt"),
                                         Charsets.UTF_8);

        // Artificially appending a line to trigger assertion for all DDMGs without making the code fancier (please forgive this sin)
        traces.add("123456789---3000-01-01---com.amazon.device.planning.adept.mysql.jooq.tables.pojos.DeviceDemandModelingGroupsPojo---"
                + "{\"id\":672,\"name\":\"usOnlineAlphaLgQ6\",\"countryId\":17,\"channelId\":1,\"deviceTypeId\":25,\"programId\":201}");
        
        outputAfterTraces = FileUtils.readLines(new File("tst/com/amazon/device/planning/vdp/service/visitors/mix_events.txt"),
                                          Charsets.UTF_8);

        expectedOutput = HashBasedTable.create();

        // Initialize caches from traces
        for (String line : traces) {
            String[] elements = line.split(DELIMITER);
            if (elements[1].equals(MixesCacheTypes.PROGRAM.getName())) {
                MixesCache mixEvent = GSON.fromJson(elements[2], MixesCache.class);
                ddmgTypeMixesCache.put(Integer.parseInt(elements[0]), MixesCacheTypes.PROGRAM.getName(), mixEvent);
                continue;
            } else if (elements[1].equals(MixesCacheTypes.CONFIG.getName())) {
                MixesCache mixEvent = GSON.fromJson(elements[2], MixesCache.class);
                ddmgTypeMixesCache.put(Integer.parseInt(elements[0]), MixesCacheTypes.CONFIG.getName(), mixEvent);
                continue;
            }
        }

        final int columnForJason = 3;
        final int columnForMixPercentage = 2;
        for (String line : outputAfterTraces) {
            String[] elements = line.split(DELIMITER);

            int ddmg = Integer.valueOf(elements[COLUMN_FOR_DDMG_ID]);
            LocalDate date = LocalDate.parse(elements[COLUMN_FOR_DATE]);
            String json = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, elements[columnForJason]);
            MixGroupForTesting mixEvent = GSON.fromJson(json, MixGroupForTesting.class);

            List<MixGroupForTesting> mixEvents = expectedOutput.get(ddmg, date);
            if (mixEvents == null) {
                mixEvents = new ArrayList<>();
            }
            mixEvents.add(mixEvent);
            mixEvent.setMixPercentage(new BigDecimal(elements[columnForMixPercentage]));

            expectedOutput.put(ddmg, date, mixEvents);
        }
    }
    
    @Test
    public void runKPATraces() throws ClassNotFoundException {
        DeviceDemandModelingGroupsPojo currentDdmgBeingProcessed = null;
        for (String trace : traces) {

            if (trace.contains(MixesCacheTypes.PROGRAM.getName()) || trace.contains(MixesCacheTypes.CONFIG.getName())) {
                // Ignore caches data
                continue;
            }

            Trace traceAsObject;
            if (trace.contains(DeviceDemandModelingGroupsPojo.class.getSimpleName())) {
                if (currentDdmgBeingProcessed != null) {
                    // Compare what we got and what we should get according to KPA traces
                    assertMixEvents(currentDdmgBeingProcessed);
                }

                // Re-load new data for next DDMG
                final int columnForJson = 3;
                currentDdmgBeingProcessed = GSON.fromJson(trace.split(DELIMITER)[columnForJson], DeviceDemandModelingGroupsPojo.class);
                
                vdpEventsVisitor = new VdpEventsVisitor(
                    currentDdmgBeingProcessed,
                    this.ddmgTypeMixesCache.get(currentDdmgBeingProcessed.getId(), MixesCacheTypes.PROGRAM.getName()) != null ?
                            this.ddmgTypeMixesCache.get(currentDdmgBeingProcessed.getId(), MixesCacheTypes.PROGRAM.getName()) : new MixesCache(),
                    this.ddmgTypeMixesCache.get(currentDdmgBeingProcessed.getId(), MixesCacheTypes.CONFIG.getName()) !=null ?
                            this.ddmgTypeMixesCache.get(currentDdmgBeingProcessed.getId(), MixesCacheTypes.CONFIG.getName()) : new MixesCache());
            } else {
                traceAsObject = new Trace(trace);
                traceAsObject.getPojo().accept(vdpEventsVisitor, traceAsObject.getApplicationDate());
            }
        }
    }
    
    private void assertMixGroups(List<MixGroupForTesting> expectedMixEventsForThisDdmgAndDate,
                                 Map.Entry<Spec, BigDecimal> ouputtedSpecToPercentEntry) {
        final int expectedNumberOfRoundsOfMatches = 4;

        int maximumNumberOfMatches = 0;
        for (MixGroupForTesting expectedMixEventForThisDdmgAndDate : expectedMixEventsForThisDdmgAndDate) { // loop through each expected
                                                                                                            // mix group for each date
            int matches = 0;
            Spec spec = ouputtedSpecToPercentEntry.getKey();
            BigDecimal mixPercentage = ouputtedSpecToPercentEntry.getValue().setScale(SCALE, RoundingMode.HALF_UP);

            if (expectedMixEventForThisDdmgAndDate.getCountryId().compareTo(spec.getCountryId()) == 0 &&
                expectedMixEventForThisDdmgAndDate.getChannelId().compareTo(spec.getChannelId()) == 0 &&
                expectedMixEventForThisDdmgAndDate.getDeviceTypeId().compareTo(spec.getDeviceTypeId()) == 0
               ) {
                matches++;
            }

            matches += countMatchingConfigurations(expectedMixEventForThisDdmgAndDate, spec, mixPercentage);
            if (matches > maximumNumberOfMatches) {
                maximumNumberOfMatches = matches;
            }
        }

        if (maximumNumberOfMatches < expectedNumberOfRoundsOfMatches) {
            fail();
        }
    }
    
    private int countMatchingConfigurations(MixGroupForTesting expectedMixEventForThisDdmgAndDate, Spec spec, BigDecimal mixPercentage) {
        int matches = 0;
        BigDecimal expectedPercentage = expectedMixEventForThisDdmgAndDate.getMixPercentage().setScale(SCALE, RoundingMode.HALF_UP);
        if (expectedMixEventForThisDdmgAndDate.getProgramId().compareTo(spec.getProgramId()) == 0 &&
            expectedPercentage.compareTo(mixPercentage) == 0
        ) {
            matches++;
        }

        if (expectedMixEventForThisDdmgAndDate.getStorageId().compareTo(spec.getStorageId()) == 0 &&
            expectedMixEventForThisDdmgAndDate.getCarrierId().equals(spec.getCarrierId()) &&
            (expectedMixEventForThisDdmgAndDate.getBundleId() == null || expectedMixEventForThisDdmgAndDate.getBundleId().equals(spec.getBundleId()))) {
            matches++;
        }

        if (expectedMixEventForThisDdmgAndDate.getColorId() == null || spec.getColorId() == null ||
                expectedMixEventForThisDdmgAndDate.getColorId().compareTo(spec.getColorId()) == 0) {
            matches++;
        }

        return matches;
    }
    
    private void assertMixEvents(DeviceDemandModelingGroupsPojo currentDdmgBeingProcessed) {
        Map<LocalDate, List<MixGroupForTesting>> expectedMixEvents = expectedOutput.rowMap().get(currentDdmgBeingProcessed.getId());
        List<MixEvent> outputtedMixEvents = vdpEventsVisitor.getMixEvents();

        // Assert we have mix groups, i.e., MixEvent, for the same number of dates
        assertEquals(expectedMixEvents.size(), outputtedMixEvents.size());

        for (MixEvent outputtedMixEvent : outputtedMixEvents) { // < For each MixEvent associated to a date

            BigDecimal ouputtedPercentageAccumulator = BigDecimal.ZERO;
            BigDecimal expectedPercentageAccumulator = BigDecimal.ZERO;
            for (Map.Entry<Spec, BigDecimal> ouputtedSpecToPercentEntry : outputtedMixEvent.getMixGroups().entrySet()) { //< For each mix group associated to a MixEvent

                // We are asserting the start date indirectly in this line
                List<MixGroupForTesting> expectedMixEventsForThisDdmgAndDate = expectedMixEvents.get(outputtedMixEvent.getStartDate());

                // Assert the number of mix groups
                //assertEquals(expectedMixEventsForThisDdmgAndDate.size(), outputtedMixEvent.getMixGroups().size());

                // Assert mix groups contents
                assertMixGroups(expectedMixEventsForThisDdmgAndDate, ouputtedSpecToPercentEntry);

                BigDecimal mixPercentage = ouputtedSpecToPercentEntry.getValue().setScale(SCALE, RoundingMode.HALF_UP);
                ouputtedPercentageAccumulator = ouputtedPercentageAccumulator.add(mixPercentage);
            }

            for (MixGroupForTesting mixGroup : expectedMixEvents.get(outputtedMixEvent.getStartDate())) {
                BigDecimal expectedPercentageForThisMixGroup = mixGroup.getMixPercentage().setScale(SCALE, RoundingMode.HALF_UP);
                expectedPercentageAccumulator = expectedPercentageAccumulator.add(expectedPercentageForThisMixGroup);
            }

            /*
             * Among other things, this asserts the usage of ProgramMixEvent's 'demandFactor'
             */
            assertEquals(expectedPercentageAccumulator, ouputtedPercentageAccumulator);
        }
    }
}
