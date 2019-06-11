/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maxwell.classcreator;

import com.maxwell.classcreator.view.View;

/**
 *
 * @author Macbook
 */
public class ClassCreator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            View view = new View();
            view.setResizable(false);
            view.setLocationRelativeTo(null);
            view.setTitle("Class creator");
            view.setVisible(true);
        } catch (Exception e) {
        }

    }

}
