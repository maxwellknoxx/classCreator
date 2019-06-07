/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maxwell.classcreator;

import java.util.Arrays;

/**
 *
 * @author Macbook
 */
public class tests {

    public static void main(String args[]) {

        /*
        String jsonText = "{\"People\": {\n"
                + "  \"String\": \"Name\",\n"
                + "  \"int\": \"Age\"\n"
                + "}}";

        jsonText = jsonText.replaceAll("\\{", "").replace("}", "");

        String className = jsonText.split("\":")[0];

        System.out.println("Class Name: " + className);

        String[] fields = jsonText.split(",");
        
        for(String field : fields) {
            System.out.println("Field: " + field);
        }
        
        
        String s = "dictionaryNameFullOfSplit";
        
        String[] eita = s.split("(?<=[a-z])(?=[A-Z])");
        
        String fieldFormated = "";
        for(String field : eita) {
            fieldFormated = fieldFormated + field + "_";
        }
        fieldFormated = fieldFormated.substring(0, fieldFormated.lastIndexOf("_")) + "" + fieldFormated.substring(fieldFormated.lastIndexOf("_") + 1);
        System.out.println(fieldFormated);
         */
        String name = "dictionaryName";
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        
        System.out.println(name);

    }

}
