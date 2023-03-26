package com.mycompany.Project;

import java.io.*;
import java.util.ArrayList;

public class LengthFilter extends Processing_elements {

    private ArrayList<File> filteredFiles = null;
    private long length;
    private String op;

    private ArrayList<File> subFiles = null;

    public void setSubFiles(ArrayList<File> subFiles) {
        this.subFiles = subFiles;
    }

    public void setFilteredFiles(ArrayList<File> filteredFiles) {
        this.filteredFiles = filteredFiles;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public void setOp(String op) {
        this.op = op;
    }

    //constructor
    public LengthFilter(ArrayList<String> inputs, ArrayList<String> entries) {
        
        String tempstr = "";
        
        
        for (String str : inputs){
            System.out.println(str); 
            
            if(str.contains("Length")){
                tempstr = str.replace("name", " ").replace(":", " ").strip();         
            }
            
            if (str.contains("value") && tempstr.equals("Length")){
                tempstr = str.replace("value", " ").replace(":", " ").strip();
                this.length = Integer.parseInt(tempstr);
            }
            
            if (str.contains("Operator")){
                tempstr = str.replace("Operator", " ").replace(":", " ").strip();
            }
            
            if (str.contains("value") && tempstr.equals("Operator")){
                tempstr = str.replace("value", " ").replace(":", " ").strip();
                this.op = tempstr;
            }
            
            
            
        }
        
       
        ArrayList<File> getPath = null;

        if (entries != null) {

            for (String filePath : entries) {
                File file = new File(filePath);
                getPath.add(file);
            }

            for (File userInput : getPath) {

                if (userInput.isFile() == true) {
                    filteredFiles.add(userInput);
                }

            }
            this.operations();
            this.outputs();
            
        } else {
            System.out.println("No Entries found.");
        }

    }

    //define these functions
    @Override
    public void operations() {
        switch (op) {

            case "EQ":
                if (filteredFiles != null) {
                    for (File subFile : filteredFiles) {

                        if (subFile.length() == length) {
                            subFiles.add(subFile);
                        }

                    }
                }
                break;

            case "NEQ":
                if (filteredFiles != null) {
                    for (File subFile : filteredFiles) {

                        if (subFile.length() != length) {
                            subFiles.add(subFile);
                        }
                    }
                }
                break;

            case "GT":
                if (filteredFiles != null) {
                    for (File subFile : filteredFiles) {

                        if (subFile.length() > length) {
                            subFiles.add(subFile);
                        }
                    }
                }
                break;

            case "GTE":
                if (filteredFiles != null) {
                    for (File subFile : filteredFiles) {

                        if (subFile.length() >= length) {
                            subFiles.add(subFile);
                        }
                    }
                }
                break;

            case "LT":
                if (filteredFiles != null) {
                    for (File subFile : filteredFiles) {

                        if (subFile.length() < length) {
                            subFiles.add(subFile);
                        }
                    }
                }
                break;

            case "LTE":
                if (filteredFiles != null) {
                    for (File subFile : filteredFiles) {

                        if (subFile.length() <= length) {
                            subFiles.add(subFile);
                        }
                    }
                }
                break;

            default:
                if (filteredFiles != null) {
                    System.out.println("Operator does not exist, all files outputted.");
                    for (File subFile : filteredFiles) {
                        subFiles.add(subFile);
                    }
                }
                break;

        }

    }
    
    @Override
    public void outputs() {

        for (File printFile : subFiles) {
            System.out.println(printFile.getName());
        }
    }

}


