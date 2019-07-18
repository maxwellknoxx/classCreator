/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maxwell.classcreator.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Maxwell Knoxx - 07/2019
 */
public class FileController {

    /**
     * 
     * @param sb
     * @param path
     * @param className
     * @param folder
     * @return 
     */
    public static Boolean createJavaFile(StringBuilder sb, String path, String className, String folder) {

        String pathToSave = searchFolder(new File(path), folder.toLowerCase());
        if (pathToSave.equals("")) {
            return false;
        }

        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToSave + "/" + className + folder + ".java"))) {
                writer.write(sb.toString());
            }
            return true;
        } catch (IOException e) {
            FileController.createFile("Error -> " + e.getMessage(), "error", "txt");
        }
        return false;
    }

    public static String searchFolder(File path, String folderName) {

        if (path.isDirectory()) {
            if (path.canRead()) {
                for (String directory : path.list()) {
                    System.out.println(directory);
                    if (directory.equals(folderName)) {
                        String directoryFound = path.toString() + "/" + directory;
                        
                        return directoryFound;
                    }
                }
            }
        } else {
            MessageController.showErrorMessage("The path -> " + path.getAbsolutePath() + " is not a directory");
        }

        return "";
    }

    public static String getDocumentsPath() {
        return System.getProperty("user.home") + "/Documents";
    }

    /**
     *
     * @param message
     * @param fileName
     * @param format
     * @return
     */
    public static Boolean createFile(String message, String fileName, String format) {

        String path = getDocumentsPath();

        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + "/" + fileName + "." + format))) {
                writer.write(message);
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

}
