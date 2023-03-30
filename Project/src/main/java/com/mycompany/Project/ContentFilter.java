package com.mycompany.Project;

import java.io.*;
import java.util.ArrayList;

public class ContentFilter extends Processing_elements {

    private String key;
    private String repoID = null;
    private String entryID = null;
    private String path = null;
    private boolean local = false;
    private boolean localScenario = false;
    private ArrayList<String> outputValues = new ArrayList<String>();
    private ArrayList<File> fileList = new ArrayList<File>();
    private ArrayList<File> outputFileList = new ArrayList<File>();
    private ArrayList<File> outputValuesFile = new ArrayList<File>();

    public ContentFilter(ArrayList<String> inputValues, ArrayList<String> pastEntries) {
        int remote = 0;

        for (String filePath : pastEntries) {
            File newFile = new File(filePath);
            fileList.add(newFile);
        }

        for (String text : inputValues) {
            System.out.println(text);

            if (text.contains("value") || text.contains("Value")) {
                key = text.replaceAll("value", "").replaceAll(" ", "").replaceAll(":", "");
            }
            if (text.contains("type") && text.contains("local"))
                local = true;

            if (local) {
                if (text.contains("path")) {
                    path = text.replaceAll("path", "").replaceAll(" ", "").replaceAll(":", "");
                }
            }
            if (text.contains("type") && text.contains("remote")) {
                remote = 2;
            }
            if (remote > 0) {
                if (text.contains("repoId") || text.contains("value")) {
                    repoID = text.replaceAll("repoId", "").replaceAll(" ", "").replaceAll(":", "");
                }
                if (text.contains("entryId") || text.contains("value")) {
                    entryID = text.replaceAll("entryId", "").replaceAll(" ", "").replaceAll(":", "");
                }
                remote--;
            }
        }
        try {
            getEntriesRemoteFileNames(26);
        } catch (Exception e) {
            // System.out.println(e);
        }

        try {
            getEntriesLocal(path);
        } catch (Exception e) {
            // System.out.println(e);
        }
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
            boolean hasKey = true;
            String line;
            try {
                for (int i = 0; i < fileList.size(); i++) {
                    BufferedReader br = new BufferedReader(new FileReader(fileList.get(i)));
                                                                //idk why I am getting a bracket syntax error for br
                    while ((line = br.readLine()) != null) {
                        if (!line.contains(key)) {
                            hasKey = false;
                            break;
                        }
                    }
                    if (hasKey == true) {
                        outputFileList.add(fileList.get(i));
                    }
                }

            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
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
                    } else {
                        hasKey = false;
                        break;
                    }
                }

                if (hasKey) {
                    localScenario = true;
                    //outputValuesFile
                } else {
                    System.out.println("Key is not found in every line of file");
                }


            } else {
                System.out.println("File path is invalid or does not exist");
            }

        }
    }

    @Override
    public void outputs() { // return new string entries arraylist with file paths that contained key
                            // return file arraylist that contains each file with contents containing key
        if (local == false){
            //return outputFileList;
        }
        else{
            if(localScenario){ // need to convert data into file ArrayList and return the single file element
                //return data;

            }
        }

    }

    public static boolean ifFile(String filePath) {
        File file = new File(filePath);
        return file.isFile();
    }

    public static boolean ifDirectory(String filePath) {
        File file = new File(filePath);
        return file.isDirectory();
    }
}
