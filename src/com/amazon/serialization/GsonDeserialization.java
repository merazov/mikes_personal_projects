package com.amazon.serialization;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.CaseFormat;
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
import lombok.NoArgsConstructor;

public class GsonDeserialization {
    
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
    
    public void deserializeAMap() {
        //String json = "\"mix_groups\":{\"{:country_id=>17, :channel_id=>1, :device_type_id=>25, :retailer_id=>nil, :program_id=>201, :dtcp=>true, :storage_id=>37, :carrier_id=>72, :bundle_id=>5, :color_id=>34}\":\"0.0\"}"; 
        String json = "{\"{:country_id=>17, :channel_id=>1, :device_type_id=>25, :retailer_id=>nil, :program_id=>201, :dtcp=>true, :storage_id=>37, :carrier_id=>72, :bundle_id=>5, :color_id=>34}\":\"0.0\"}"; 

        //Map<Spec, BigDecimal> mixGroups = new HashMap<>();
        Map<String, BigDecimal> mixGroups = new HashMap<>();
        mixGroups = GSON.fromJson(json, mixGroups.getClass());
        for (Map.Entry<String, BigDecimal> entry : mixGroups.entrySet()) {
            System.out.println("mixGroups key=" + entry.getKey());
            System.out.println("mixGroups value=" + entry.getValue());
            
            String formattedSpec = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, entry.getKey());
            JSONObject specJson = new JSONObject(formattedSpec);
            String countryId = specJson.getString("countryId");
            
            System.out.println("");
            
            // The excerpt below does not work
            //System.out.println("mixGroups key=" + entry.getKey().getCountryId());
            //System.out.println("mixGroups key=" + entry.getKey().getChannelId());
            //System.out.println("mixGroups key=" + entry.getKey().getDeviceTypeId());
        }
    }

    @Data
    @NoArgsConstructor
    private static class MyDataStructure {
        private Map<Spec, BigDecimal> mixGroups;
    }
    
    public void deserializeADataStructureContainingAMap() {
        String json = "\"mix_groups\":{\"{:country_id=>17, :channel_id=>1, :device_type_id=>25, :retailer_id=>nil, :program_id=>201, :dtcp=>true, :storage_id=>37, :carrier_id=>72, :bundle_id=>5, :color_id=>34}\":\"0.0\"}"; 
        
        mixGroups = GSON.fromJson(json, MyDataStructure.class);
        for(Map.Entry<Spec, BigDecimal> entry : mixGroups.entrySet()) {
            System.out.println("mixGroups key=" + entry.getKey());
            System.out.println("mixGroups value=" + entry.getValue());
        }
    }
    
    public static void main(String[] args) {
        GsonDeserialization sut = new GsonDeserialization();
        sut.deserializeAMap();
    }
}
