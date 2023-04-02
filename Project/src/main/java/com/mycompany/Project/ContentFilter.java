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

        for (String text : inputValues) { // Extracts key from inputValues
            if (text.contains("value") || text.contains("Value")) {
                key = text.replaceAll("value", "").replaceAll(" ", "").replaceAll(":", "");
            }
        }

        for (String files : pastEntries) { // Merge pastEntries with inputValues
            inputValues.add(files);
        }

        loopEntries(inputValues); // Loop through and print each line from input values
                                  // If local, extract path
                                  // If remote, extract entryID and repoID

        // try {
        // getEntriesRemoteFileName();
        // } catch (Exception e) {
        // // System.out.println(e);
        // }

        // try {
        // getEntriesLocal(path);
        // } catch (Exception e) {
        // System.out.println(e);
        // }
        /*
         * for (String text : data) { // Data arrayList contains the file contents of
         * that entry
         * System.out.println(text);
         * }
         */
    }

    @Override
    public void operations() { // *Assume operations only requires one instance of key in each file for it to be passed on to next filter*
                               //  If I dont't change anything *Assume operations only searches for key in single file entries*

        if (local == false) { // *Scenario for remote entries*

            boolean hasKey = false; // hasKey false by default

            if (true) {  // If remote entry is a single document
               
                getEntriesRemote(Integer.parseInt(entryID));  // Parse entryID as an int and create instance of remote client with getEntriesRemote method.
                                                              // If remote entry is single document, create local instance of document with custom filepath and read that file.
                                                              // After successful, close client.

                for (String element : data) {  // Iterate through each line of the local file that was just created from the remote client.
                    
                    if (element.contains(key)) {  
                        hasKey = true;
                        break;
                    }
                }

                if (hasKey) {               // If document contains the key print message and generateRemoteJson method.
                    generateRemoteJson();   // Method will add type, entryID, repoID to output ArrayList.
                    System.out.println("Key has been found in the contents of the file.");

                } else {                    // Otherwise, message will be prompted and the document will not be passed to the next filter.
                    System.out.println("Key is not found in the contents of the entry.");
                }
            } else {
                // If remote entry is a directory
            }



            // try {

            // File filename = new File(this.path);
            // readfile(filename);

            // for (String line : data) {
            // System.out.println(line);
            // if (line.contains(key)) {
            // hasKey = true;
            // break;
            // }
            // }
            // if (hasKey == true) {
            // System.out.println("Key has been found in the contents of the file.");
            // addFileToList();
            // }
            // } catch (Exception ex) {
            // System.out.println(ex);
            // }


        }

        else { // *Scenario for local entries*
               // Read from single file contents whose path was extracted from inputValues ArrayList.

            if (ifDirectory(path)) { // Check if local entry is a directory.
                System.out.println("Invalid Input Type: Expecting file input");
            }

            if (ifFile(path)) { // Check if local entry is a single file.
                boolean hasKey = false;          // hasKey false by default
                File newFile = new File(path);   // Create new local file with the extracted path to hold contents of file.
                readfile(newFile);
                for (String element : data) {    // Read contents of file line by line.

                    if (element.contains(key)) { // Check if key is in contents of the file.
                        hasKey = true;
                        break;
                    }
                }

                if (hasKey) {            
                    localScenario = true;
                    System.out.println("Key has been found in the contents of the file."); // If key is found, print prompt that key has been found and set localScenario boolean to true.

                } else {
                    System.out.println("Key is not found in the contents of the entry."); // If key is not found, prompt that key is not found and it will not be passed on.
                }

            } else {
                System.out.println("File path is invalid or does not exist"); // If file isn't path or directory it is invalid
            }
            if (localScenario) {          // If localScenario it true, generateLocalJson method.
                generateLocalJson(path);  // Method will add type and successful file path to the output ArrayList.
            }
        }

    }

    public static boolean ifFile(String filePath) {      // Method that checks if entry is a file
        File file = new File(filePath);
        return file.isFile();
    }

    public static boolean ifDirectory(String filePath) { // Method that checks if entry is a directory
        File file = new File(filePath);
        return file.isDirectory();
    }
}
