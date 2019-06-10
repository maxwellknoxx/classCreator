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

        String test = "public   String set name(  String  name) {";
        test = test.replace("(_)+", "");
        
        System.out.println(test);

    }

}
