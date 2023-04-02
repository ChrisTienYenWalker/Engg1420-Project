package com.mycompany.Project;

import java.io.*;
import java.util.ArrayList;

public class ContentFilter extends Processing_elements {


    private boolean localScenario = false;
    private ArrayList<String> outputValues = new ArrayList<String>();
    private ArrayList<File> outputFileList = new ArrayList<File>();
    private ArrayList<File> outputValuesFile = new ArrayList<File>();
    private String key;

    public ContentFilter(ArrayList<String> inputValues, ArrayList<String> pastEntries) {

        for (String text : inputValues) {
            if (text.contains("value") || text.contains("Value")) {
                key = text.replaceAll("value", "").replaceAll(" ", "").replaceAll(":", "");
            }
        }

        for (String files : pastEntries) {
            inputValues.add(files);
        }
        
        loopEntries(inputValues);

        // try {
        //     getEntriesRemoteFileName();
        // } catch (Exception e) {
        //     // System.out.println(e);
        // }

        // try {
        //     getEntriesLocal(path);
        // } catch (Exception e) {
            // System.out.println(e);
        // }
        /* 
        for (String text : data) { // Data arrayList contains the file contents of that entry
            System.out.println(text);
        }
        */
    }

    @Override
    public void operations() {
        // createFileFromRemote(client null, repoID, entryID); // use this method for
        // remote case?

        if (local == false) { // If content filter is first case scenario read from pastEntries
                              // Add files that contain key to outputFileList ArrayList
            boolean hasKey = false;
            try {

                File filename = new File(this.path);
                readfile(filename);

                for(String line: data){
                    System.out.println(line);
                    if (line.contains(key)) {
                        hasKey = true;
                        break;
                    }
                }
                if (hasKey == true) {
                    System.out.println("Key has been found in the content of the file.");
                    addFileToList();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }   
        
        } 

        else { // Read from single file contents whose path was extracted from inputValues
                 // ArrayList
            if (ifDirectory(path)) {
                System.out.println("Invalid Input Type: Expecting file input");
            }

            if (ifFile(path)) { // **ASSUME you are looking for key in each index of data ArrayList
                boolean hasKey = false;
                for (String element : data) {
                    // String lines[] = element.split("\n");
                  
                    // for (String line : lines){
                    if (element.contains(key)) {
                        hasKey = true;
                        break;
                    } 
                }

                if (hasKey == true) {
                    localScenario = true;
                    System.out.println("Key has been found in the content of the file.");
                    
                } else {
                    System.out.println("Key is not found in the content of the entry.");
                }


            } else {
                System.out.println("File path is invalid or does not exist");
            }

        }

        if (localScenario){
            generateLocalJson(path);
        }

    }

    // @Override
    // public void outputs() { // return new string entries arraylist with file paths that contained key
    //                         // return file arraylist that contains each file with contents containing key
    //     if (local == false){
    //         //return outputFileList;
    //     }
    //     else{
    //         if(localScenario){ // need to convert data into file ArrayList and return the single file element
    //             //return data;

    //         }
    //     }

    // }


    // outputs();



    public static boolean ifFile(String filePath) {
        File file = new File(filePath);
        return file.isFile();
    }

    public static boolean ifDirectory(String filePath) {
        File file = new File(filePath);
        return file.isDirectory();
    }
}
