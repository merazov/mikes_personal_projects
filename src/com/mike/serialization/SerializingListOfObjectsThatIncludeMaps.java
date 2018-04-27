package com.mike.serialization;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import lombok.Data;
import lombok.NoArgsConstructor;

public class SerializingListOfObjectsThatIncludeMaps {

    /*
     * IMPORTANT: enableComplexMapKeySerialization is needed to be able to serialize key
     */
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().enableComplexMapKeySerialization().setPrettyPrinting()
            .create();

    private static final Gson CUSTOM_GSON = new GsonBuilder().disableHtmlEscaping()
            .registerTypeAdapter(Spec.class, new JsonSerializer<Spec>() {
                @Override
                public JsonElement serialize(Spec src, Type typeOfSrc, JsonSerializationContext context) {
                    return new JsonPrimitive(src.toString());
                }                
            }).create();
    
    @Data
    @NoArgsConstructor
    public static class Spec {
        private int countryId;
        //private int channelId;
        //private int deviceTypeId;
        //private int programId;
        //private boolean dtcp;
    }
    
    @Data
    public static class MixEvent {

        private final LocalDate startDate;
        private final Map<Spec, BigDecimal> mixGroups;

        private LocalDate endDate;

        public MixEvent(LocalDate startDate, Map<Spec, BigDecimal> mixGroups) {
            this.startDate = startDate;
            this.mixGroups = mixGroups;
            this.endDate = LocalDate.MAX;
        }
    }
    
    public static void main(String[] args) {
        List<MixEvent> mixEvents = new ArrayList<>();
        Spec spec1 = new Spec();//(1,1,1,1,true);
        //spec1.setChannelId(1);
        spec1.setCountryId(1);
        Spec spec2 = new Spec();//2,2,2,2,true);
        //spec2.setChannelId(2);
        spec2.setCountryId(2);
        Map<Spec, BigDecimal> mixGroup1 = ImmutableMap.of(spec1, new BigDecimal("0.6"),
                                                          spec2, new BigDecimal("0.4"));
        MixEvent mixEvent1 = new MixEvent(LocalDate.of(2018, 1, 1), mixGroup1);
        
        mixEvents.add(mixEvent1);
        mixEvents.add(mixEvent1);
        
        //System.out.println(GSON.toJson(spec1));
        System.out.println(GSON.toJson(mixGroup1));
        
        Map<Spec, BigDecimal> output = GSON.fromJson(GSON.toJson(mixGroup1), new TypeToken<Map<Spec, BigDecimal>>() {}.getType());
        for(Map.Entry<Spec, BigDecimal> entry : output.entrySet()) {
            System.out.println("***key=" + entry.getKey().getCountryId() + " value=" + entry.getValue());
        }
        
        MixEvent outputMixEvent = GSON.fromJson(GSON.toJson(mixEvent1), new TypeToken<MixEvent>() {}.getType());
        System.out.println("+++outputMixEvent.getStartDate()=" + outputMixEvent.getStartDate());
        for(Map.Entry<Spec, BigDecimal> entry : outputMixEvent.getMixGroups().entrySet()) {
            System.out.println("+++key=" + entry.getKey().getCountryId() + " value=" + entry.getValue());
        }
        
        List<MixEvent> outputMixEvents = GSON.fromJson(GSON.toJson(mixEvents), new TypeToken<List<MixEvent>>() {}.getType());
        System.out.println("^^^outputMixEvent.getStartDate()=" + outputMixEvents.get(0).getStartDate());
        for(MixEvent entry : outputMixEvents) {
            for(Map.Entry<Spec, BigDecimal> entry2 : entry.getMixGroups().entrySet()) {
                System.out.println("^^^key=" + entry2.getKey().getCountryId() + " value=" + entry2.getValue());
            }
        }
        
        // The following is serialized correctly as: {"1":1,"2":2}
        /*Map<String, BigDecimal> ex = ImmutableMap.of("1", new BigDecimal("1"),
                                                     "2", new BigDecimal("2"));
        System.out.println(GSON.toJson(ex));*/
    }
}
