package com.mike.serialization.jsonpath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

/**
 * Read https://www.baeldung.com/guide-to-jayway-jsonpath
 */
public class ParsingStringAsDocumentAndAccessingWithPath {
    
    public static void main(String[] args) {
        String json = "{" + 
                "    \"tool\": " + 
                "    {" + 
                "        \"jsonpath\": " + 
                "        {" + 
                "            \"creator\": " + 
                "            {" + 
                "                \"name\": \"Jayway Inc.\"," + 
                "                \"location\": " + 
                "                [" + 
                "                    \"Malmo\"," + 
                "                    \"San Francisco\"," + 
                "                    \"Helsingborg\"" + 
                "                ]" + 
                "            }" + 
                "        }" + 
                "    }," + 
                " " + 
                "    \"book\": " + 
                "    [" + 
                "        {" + 
                "            \"title\": \"Beginning JSON\"," + 
                "            \"price\": 49.99" + 
                "        }," + 
                " " + 
                "        {" + 
                "            \"title\": \"JSON at Work\"," + 
                "            \"price\": 29.99" + 
                "        }" + 
                "    ]" + 
                "}";
        
        String jsonpathCreatorNamePath = "$['tool']['jsonpath']['creator']['name']";
        String jsonpathCreatorLocationPath = "$['tool']['jsonpath']['creator']['location'][*]";

        DocumentContext jsonContext = JsonPath.parse(json);

        String jsonpathCreatorName = jsonContext.read(jsonpathCreatorNamePath);
        List<String> jsonpathCreatorLocation = jsonContext.read(jsonpathCreatorLocationPath);

        assertEquals("Jayway Inc.", jsonpathCreatorName);
        assertTrue(jsonpathCreatorLocation.toString().contains("Malmo"));
        assertTrue(jsonpathCreatorLocation.toString().contains("San Francisco"));
        assertTrue(jsonpathCreatorLocation.toString().contains("Helsingborg"));
    }
}
