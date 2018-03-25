package com.mike.serialization;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Person {

    public final String id;
    public final String name;
    public final Person parent;

    public String getAllParametersConcatenated() {
        return id + name + parent.toString();
    }
    
    public static class PersonSerializer implements JsonSerializer<Person> {

        @Override
        public JsonElement serialize(Person person, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject result = new JsonObject();
            result.add("id", new JsonPrimitive(person.getId()));
            result.add("name", new JsonPrimitive(person.getName()));
            Person parent = person.getParent();
            if (parent != null) {
                result.add("parent", new JsonPrimitive(parent.getId()));
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Person p = new Person("1", "Joe", new Person("2", "Mike", null));
        com.google.gson.Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, new PersonSerializer())
                .create();
        System.out.println(gson.toJson(p));
        System.out.println(p.getAllParametersConcatenated());
    }
}
