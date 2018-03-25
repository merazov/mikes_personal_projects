package com.amazon.sql;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import com.google.common.base.CaseFormat;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
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
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ContructingJoinObjectsFromJSON {

    private static final Joiner COMMA_JOINER = Joiner.on("---");

    private final String prefixBeforeClassName;

    private List<EventPojo> inOderListOfPojosToBeVisited = new ArrayList<>();

    @RequiredArgsConstructor
    private static enum ClassesPairedForJoins {
        PROGRAM_MIX("ProgramMixEvent", "ProgramMix", ProgramEventProgramMixProgramPojo.class, "program_mix_event_id"),
        CONFIG_MIX("ConfigMixEvent", "ConfigMix", ConfigEventConfigMixProgramPojo.class, "config_mix_event_id");

        private final String referencedClassName; // The one that has a PK being referenced by other records
        private final String referencingClassName; // The one references a unique record
        private final Class<? extends EventPojo> classToConvertTo;
        private final String joinColumn;

        public String getReferencedClass() {
            return referencedClassName;
        }

        public String getReferencingClass() {
            return referencingClassName;
        }

        public Class<?> getClassToConvertTo() {
            return classToConvertTo;
        }

        @SuppressWarnings("unchecked")
        public static <T extends EventPojo> Class<T> getClassForClassName(String referencedClassName) {
            for (ClassesPairedForJoins element : ClassesPairedForJoins.values()) {
                if (element.getReferencedClass().equals(referencedClassName)) {
                    return (Class<T>) element.getClassToConvertTo();
                }
            }

            throw new RuntimeException("Could not find class object for className:" + referencedClassName);
        }

        public static boolean twoClassesShouldBeJoinedTogether(String referencedClassName, String className2) {
            for (ClassesPairedForJoins element : ClassesPairedForJoins.values()) {
                if (element.getReferencedClass().equals(referencedClassName) && element.getReferencingClass().equals(className2)) {
                    return true;
                }
            }

            return false;
        }

        public static boolean classNameIsAReferedOne(String classname) {
            for (ClassesPairedForJoins element : ClassesPairedForJoins.values()) {
                if (element.getReferencedClass().equals(classname)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void run() throws IOException {
        List<String> contents = FileUtils.readLines(new File("/Users/merazo/ddmgs_of_interest.txt"), Charsets.UTF_8);

        Gson gson = new GsonBuilder()
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

        //String currentProgramReferredClassName = "";
        //String currentConfigReferredClassName = "";
        String currentReferredProgramClassRawRow = "";
        String currentReferredConfigClassRawRow = "";

        // Loop through all lines
        for (int i = 0; i < contents.size(); i++) {
            
            String currentLine = contents.get(i);
            String classNameForCurrentRow = extractClassNameFromRow(currentLine);
            DeviceDemandModelingGroupsPojo currentDDMG;
            
            if (ClassesPairedForJoins.classNameIsAReferedOne(classNameForCurrentRow)) {
                if (classNameForCurrentRow.equals("ProgramMixEvent")) {
                    currentReferredProgramClassRawRow = currentLine;
                } else {
                    currentReferredConfigClassRawRow = currentLine;
                }
                //currentReferredClassName = classNameForCurrentRow;
                //currentReferredClassRawRow = currentLine;
            } else if (classNameForCurrentRow.equals("DeviceDemandModelingGroup")) {
                String jsonForCurrentLine = currentLine.split("json:")[1];
                String formattedJsonForCurrentLine = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, jsonForCurrentLine);
                currentDDMG = gson.fromJson(formattedJsonForCurrentLine, DeviceDemandModelingGroupsPojo.class);
                
                System.out.println(COMMA_JOINER.join(currentDDMG.getId(),
                                                     "1970-01-01---com.amazon.device.planning.adept.mysql.jooq.tables.pojos.DeviceDemandModelingGroupsPojo",
                                                     gson.toJson(currentDDMG)));
                
            } else {
                // Construct a join object
                //if (ClassesPairedForJoins.twoClassesShouldBeJoinedTogether(currentReferredClassName, classNameForCurrentRow)) {
                
                    // Let's set up context before proceeding
                    String currentReferredClassName;
                    String currentReferredClassRawRow;
                    if (classNameForCurrentRow.equals("ProgramMix")) {
                        currentReferredClassName = "ProgramMixEvent";
                        currentReferredClassRawRow = currentReferredProgramClassRawRow;
                    } else {
                        currentReferredClassName = "ConfigMixEvent";
                        currentReferredClassRawRow = currentReferredConfigClassRawRow;
                    }
                        
                    String[] lineSplittedBySpace = currentLine.split("\\s+");
                    int ddmg = Integer.valueOf(lineSplittedBySpace[0]);
                    LocalDate dateForProcessing = LocalDate.of(Integer.valueOf(lineSplittedBySpace[1].split("-")[0]),
                                                               Integer.valueOf(lineSplittedBySpace[1].split("-")[1]),
                                                               Integer.valueOf(lineSplittedBySpace[1].split("-")[2]));

                    Class<? extends EventPojo> clazz = ClassesPairedForJoins.getClassForClassName(currentReferredClassName);

                    EventPojo pojo;
                    try {
                        String jsonForCurrentReferredClassRawRow = currentReferredClassRawRow.split("json:")[1];
                        String jsonForCurrentLine = currentLine.split("json:")[1];

                        // System.out.println("jsonForCurrentReferredClassRawRow=" + jsonForCurrentReferredClassRawRow);
                        // System.out.println("jsonForCurrentLine=" + jsonForCurrentLine);

                        String formattedJsonForCurrentReferredClassRawRow = CaseFormat.LOWER_UNDERSCORE
                                .to(CaseFormat.LOWER_CAMEL, jsonForCurrentReferredClassRawRow);
                        String formattedJsonForCurrentLine = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, jsonForCurrentLine);
                        //System.out.println("---formattedJsonForCurrentReferredClassRawRow=" + formattedJsonForCurrentReferredClassRawRow);
                        //System.out.println("---formattedJsonForCurrentLine=" + formattedJsonForCurrentLine);

                        // Bare bones pojo
                        pojo = gson.fromJson(formattedJsonForCurrentReferredClassRawRow, clazz);
                        JSONObject json = new JSONObject(formattedJsonForCurrentLine);

                        if (clazz == ProgramEventProgramMixProgramPojo.class) {
                            ProgramEventProgramMixProgramPojo castedPojo = (ProgramEventProgramMixProgramPojo) pojo;

                            int configMixEventId = json.getInt("programMixEventId");
                            int programId = json.getInt("programId");
                            BigDecimal mixPercent = new BigDecimal(json.getString("mixPercent"));
                            castedPojo.setProgramMixEventId(configMixEventId);
                            castedPojo.setProgramId(programId);
                            castedPojo.setMixPercent(mixPercent);
                        } else {
                            ConfigEventConfigMixProgramPojo castedPojo = (ConfigEventConfigMixProgramPojo) pojo;

                            int storageId = json.getInt("storageId");
                            int colorId = json.getInt("colorId");
                            int carrierId = json.getInt("carrierId");
                            int bundleId = json.getInt("bundleId");
                            boolean dtcp = json.getBoolean("dtcp");
                            BigDecimal mixPercent = new BigDecimal(json.getString("mixPercent"));

                            castedPojo.setStorageId(storageId);
                            castedPojo.setColorId(colorId);
                            castedPojo.setCarrierId(carrierId);
                            castedPojo.setBundleId(bundleId);
                            castedPojo.setDtcp(dtcp);
                            castedPojo.setMixPercent(mixPercent);
                        }

                        System.out.println(COMMA_JOINER.join(
                                                             ddmg,
                                                             dateForProcessing,
                                                             pojo.getClass().getName(),
                                                             gson.toJson(pojo)));

                    } catch (Exception e) {
                        throw new RuntimeException("Could not create instance for", e);
                    }

                    inOderListOfPojosToBeVisited.add(pojo);
                } //else {
                  //  throw new RuntimeException("Could not find a match to construct\n" + " 'join object' for currentReferredClassName:" + currentReferredClassName + " classNameForCurrentRow:" + classNameForCurrentRow);
                //}
            }
            System.out.println("list=" + contents.get(0));
        }        
    //}

    private Map<String, String> getMapOfFieldNameToValue(String row) {
        Map<String, String> nameToValue = new HashMap<>();
        String[] fieldAnValues = row.substring(row.indexOf(prefixBeforeClassName)).split(",");

        // Skip the first one because it is the class name
        for (int i = 1; i < fieldAnValues.length; i++) {
            String[] fieldValueSplit = fieldAnValues[i].split(":");
            String fieldName = fieldValueSplit[0];
            String fieldValue = fieldValueSplit[1].trim();

            nameToValue.put(fieldName, fieldValue);
        }

        return nameToValue;
    }

    private String extractValueFromRow(String row, String fieldToExtractValueFrom) {
        String[] fieldAnValues = row.substring(row.indexOf(prefixBeforeClassName)).split(",");

        for (String fieldAndValue : fieldAnValues) {
            String[] fieldValueSplit = fieldAndValue.split(":");
            String fieldName = fieldValueSplit[0];
            String fieldValue = fieldValueSplit[1].trim();

            if (fieldToExtractValueFrom.equals(fieldName)) {
                return fieldValue;
            }
        }

        throw new RuntimeException("Could not find value for field:" + fieldToExtractValueFrom + " in row:" + row);
    }

    private String extractClassNameFromRow(String row) {
        return row.substring(row.indexOf(prefixBeforeClassName)).split("\\s+")[0].split(prefixBeforeClassName)[1];
    }

    public static void main(String[] args) throws IOException {
        ContructingJoinObjectsFromJSON sut = new ContructingJoinObjectsFromJSON("#<");
        sut.run();
    }
}
