package com.mycompany.Project;

import java.util.ArrayList;
import java.io.File;

public class List extends Processing_elements {

    // initialize variables
    private int Max;
    private String localPath = "empty";
    private ArrayList<String> outputList = new ArrayList<String>();

    // constructor to process input values and entries
    public List(ArrayList<String> inputValue, ArrayList<String> entries) {
        String tempstr = null;
        for (String str : inputValue){
            System.out.println(str); 

            // find the local path
            if (str.contains("local")){
                tempstr = str.replace("type", " ").replace(":", " ").strip();
            }
            if(str.contains("path") && tempstr.equals("local")){
                this.localPath = str.replace("path :", " ").strip();
            }

            // get the value of Max
            if(str.contains("Max")){
                tempstr = str.replace("name", " ").replace(":", " ").strip();    
            }
            if(str.contains("value") && tempstr.equals("Max")){
                tempstr = str.replace("value", " ").replace(":", " ").strip();
                this.Max = Integer.parseInt(tempstr);
            }
        }  
    }

    // define the operations function to process the input directory and create output list
    public void operations() {
        // check if local path is specified
        if (localPath.equals("empty")) {
            System.out.println("No local path specified");
            return;
        }

        // check if the directory exists
        File dir = new File(localPath);
        if (!dir.exists()) {
            System.out.println("The specified directory does not exist");
            return;
        }

        // get all files in the directory
        File[] files = dir.listFiles();
        if (files == null) {
            System.out.println("The specified directory is empty");
            return;
        }

        // loop through the files
        for (File file : files) {
            if (file.isDirectory()) {
                // if the file is a directory, get its files
                File[] subFiles = file.listFiles();
                if (subFiles == null) {
                    System.out.println("The specified subdirectory is empty");
                } else {
                    // loop through the subdirectory files
                    int count = 0;
                    for (File subFile : subFiles) {
                        if (count >= Max) {
                            break;
                        }
                        if (subFile.isFile()) {
                            // add the file to the output list if it is a file
                            outputList.add(subFile.getAbsolutePath());
                            count++;
                        }
                    }
                }
            }
        }
    }


}