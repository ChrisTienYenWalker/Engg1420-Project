package com.mycompany.Project;

import java.util.ArrayList;
import java.io.*;
import java.util.List;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardDownRightHandler;

public class CountFilter extends Processing_elements {

    private ArrayList<File> pastEntries = new ArrayList<>(); //Decleration of arraylist that takes in a list of files
    ArrayList<String> outputList = new ArrayList<>(); //Decleration of an arraylist that takes in a list of strings
    private String key;
    private int min;

    public CountFilter(ArrayList<String> inputValues, ArrayList<String> pastEntries) { //Constructor
        
        Boolean keyBool = false; //KeyBool and MinBool used to differentiate the value that is assigened to key and min.
        Boolean minBool = false;
        for (String text : inputValues) {
            if(text.contains("Key")){ //Checking if key is found, if so set to true
                keyBool = true;
            }
            if(text.contains("Min")){ //Checking if min is found if so set to true
                minBool = true;
            }
            if (text.contains("value") && keyBool) { //When value is found and keybool is true, the if loop replaces all the excess (words,semicolons,spaces) and sets key to the raw value entered.
                key = text.replaceAll("value", "").replaceAll(" ", "").replaceAll(":", "");
                keyBool = false;
            }
            if (text.contains("value") && minBool) { //When value is found and Minbool is true, the if loop replaces all the excess (words,semicolons,spaces) and sets Min to the raw value entered.
                min = Integer.parseInt(text.replaceAll("value", "").replaceAll(" ", "").replaceAll(":", ""));
                minBool = false;
            }
        }
        System.out.println("data " + min); //Just a check to see that the values entered are correct.
        System.out.println(key);
        for (String files : pastEntries) {  //Add all the files from the past entries to new arraylist inputValues
            inputValues.add(files);
        }
        
        loopEntries(inputValues);

        for (String text : data) { // use data arrayList to print each line of text
           // System.out.println(text);
        }
    }

    public static boolean ifFile(String filePath) { //Method to check if the path entered is a file
        File file = new File(filePath);
        return file.isFile();
    }

    public static boolean ifDirectory(String filePath) { //Method to check if the path entered is a directory
        File file = new File(filePath);
        return file.isDirectory();
    }

    @Override //Override Operations method from the superclass
    public void operations() {
        

        if (local == false) { //If it is remote enter loop
            Boolean totalKey = false; //Sets totalkey to false

            getEntriesRemote(Integer.parseInt(entryID));

            // try {
            //     File filename = new File(path); //gets the filename
            //     readfile(filename);

                for(String line: data){ //Reads through each line of the data
                        int count = 0; //Set the count value to zero
                            if (line.contains(key)) {
                                count++;
                                if (count < min) {
                                    totalKey = false;
                                    break;
                                }
                            }
                        }
                        if (totalKey == true) {
                            addFileToList();
                           System.out.println(":(");
                        }
                        else{
                            System.out.println("The Key is not found the min number of times.");
                        }
        } else {
            if (ifDirectory(path)) {
                System.out.println("Please use valid file path");
            } else if (ifFile(path)) {
                Boolean totalKey = false; //Sets totalkey to true

                File newFile = new File(path);
                readfile(newFile);

                int count = 0;
                for (String line : data) {
                    if (line.contains(key)) {
                        count++;
                    }
                }
                    if (count < min) {
                        totalKey = false;
                        System.out.println("The key " + key + " is not found at least " + min + " times in the file");
                    } else {
                        totalKey = true;
                        System.out.println("The Key " + key + " is found at least " + min + " times in the file");
                        outputList.add(path);
                    }
                
                    
                
                // if (totalKey == true) {
                //     System.out.println("The key " + key + " is found at least " + min + " times in the file");
                //     outputList.add(path);
                // }
            } else {
                System.out.println("File path not found");
            }
        }
    }
}
