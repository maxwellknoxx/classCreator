/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maxwell.classcreator;

import com.maxwell.classcreator.control.JsonController;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Macbook
 */
public class tests {

    public static void main(String args[]) {

       List<String> listFields = new ArrayList<>();
        String jsonText = "{\n" +
"\"Person\": {\n" +
"  \"Long\": \"Id\",\n" +
"  \"String\": \"Name\",\n" +
"  \"int\": \"Age\",\n" +
"  \"Boolean\": \"Status\"\n" +
"}\n" +
"}";

        jsonText = jsonText.replaceAll("\\{", "").replace("}", "");

        String className =jsonText.split("\":")[0].replace("\"", "");
        
        System.out.println("Class name: " + className);

        String[] fields = jsonText.split(className);

       for(String field : fields) {
           System.out.println("Field: " + field);
       }
        
      
       

    }

}
