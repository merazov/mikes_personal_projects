package com.amazon.objectmappers;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import lombok.Data;
import lombok.NoArgsConstructor;

public class TestAllObjectMappers {
    // @Data
    // @NoArgsConstructor
    // @AllArgsConstructor
    public static class Car {

        private Integer age;

        public Car() {
            super();
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer color) {
            this.age = color;
        }
    }

    @Data
    @NoArgsConstructor
    public static class Pojo {
        private String employee;
        private String job;
        //private String[] to;
        private Boolean active;
        private BigDecimal[] scores;
        private Integer id;
        private LocalDate date;
        private ZonedDateTime zonedDate;
    }

    public <T> T toPojo(Map<String, String> map, Class<T> clazz) {
        final ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());
        module.addDeserializer(BigDecimal[].class, new BigDecimalDeserializer());

        mapper.registerModule(module);

        return mapper.convertValue(map, clazz);
    }

    public static class BigDecimalDeserializer extends StdDeserializer<BigDecimal[]> {
        private static final long serialVersionUID = 1L;

        public BigDecimalDeserializer() {
            super(BigDecimal[].class);
        }

        @Override
        public BigDecimal[] deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String bdsAdString = jsonParser.readValueAs(String.class);
            System.out.println(bdsAdString);
            String[] strs = bdsAdString.split(",");
            System.out.println("bd as string 0=" + strs[0]);
            System.out.println("bd as string 1=" + strs[1]);

            BigDecimal[] ret = new BigDecimal[strs.length];
            for (int i = 0; i < strs.length; i++) {
                System.out.println("bd as string=" + strs[i]);
                ret[i] = new BigDecimal(strs[i]);
            }
            return ret;
        }
    }

    public static class ZonedDateTimeDeserializer extends StdDeserializer<ZonedDateTime> {
        private static final long serialVersionUID = 1L;

        public ZonedDateTimeDeserializer() {
            super(ZonedDateTime.class);
        }

        @Override
        public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return ZonedDateTime.parse(jsonParser.readValueAs(String.class));
        }
    }
    
    public static class LocalDateDeserializer extends StdDeserializer<LocalDate> {
        private static final long serialVersionUID = 1L;

        public LocalDateDeserializer() {
            super(LocalDate.class);
        }

        @Override
        public LocalDate deserialize(JsonParser jsonParser, DeserializationContext ctxt)
                                                                                         throws IOException {
            return LocalDate.parse(jsonParser.readValueAs(String.class));
        }

    }

    public static void main(String[] args) {
        TestAllObjectMappers tester = new TestAllObjectMappers();

        Map<String, String> keyVals = new HashMap<>();
        keyVals.put("employee", "employee1");
        keyVals.put("job", "amazon");
        //keyVals.put("to", "employee1, employee2");
        keyVals.put("active", "true");
        keyVals.put("scores", "1.1,2.2");
        keyVals.put("id", "1");
        keyVals.put("date", "2014-04-10");
        keyVals.put("zonedDate", "2016-08-22T14:30+08:00[Asia/Kuala_Lumpur]");

        // jackson
        Pojo car = tester.toPojo(keyVals, Pojo.class);
        System.out.println("car=" + car);
    }
}
