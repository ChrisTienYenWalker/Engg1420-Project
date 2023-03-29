package com.mycompany.Project;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class LengthFilter extends Processing_elements {

    private ArrayList<File> filteredFiles = null;
    private long length;
    private String op = "";
    private File folder;
    File[] noPrevious;
    private ArrayList<File> subFiles = null;
    private boolean containsContent = false;
 
     

    //constructor
    public LengthFilter(ArrayList<String> inputs, ArrayList<String> entries) {
        
        String tempstr = "";
        String localPath = "";


        for (String str : inputs){
            System.out.println(str); 

            

            if (str.contains("local")){
                tempstr = str.replace("type", " ").replace(":", " ").strip();
            }

            //"path": "c:\\sample\\text_files"
            if(str.contains("path") && tempstr.equals("local")){
                localPath = str.replace("path :", " ").strip();

                getFiles(localPath);
            }


            //Get Operators / Length
            if(str.contains("Length")){
                tempstr = str.replace("name", " ").replace(":", " ").strip();    
                
                  
            }
            
            if (str.contains("value") && tempstr.equals("Length")){
                tempstr = str.replace("value", " ").replace(":", " ").strip();
               
               
                this.length = Integer.parseInt(tempstr);
                
            }
            
            if (str.contains("Operator")){
                tempstr = str.replace("name", " ").replace(":", " ").strip();
               
                
            }
            
            if (str.contains("value") && tempstr.equals("Operator")){
                tempstr = str.replace("value", " ").replace(":", " ").strip();
               
                this.op = tempstr;
            }

        

            
        }

        System.out.println("Operator: " + op);
        System.out.println("Length: " + length);
        System.out.println("Path:" + localPath);
        //GOOD UP UNTIL THIS POINT
        
      
        

        ArrayList<File> getPath = new ArrayList<>();

        //FILTER FILES
    
            try {
                if(entries != null && containsContent == false) {
                  
                    for (String filePath : entries) {
                        File file = new File(filePath);
                        getPath.add(file);
                    }
        
                    for (File userInput : getPath) {
        
                        if (userInput.isFile()) {
                            filteredFiles.add(userInput);
                        }
        
                    }
                    this.operations();
                    this.outputs();
                    
                
                } else if(containsContent == true){

                    for (File file: noPrevious){
                        getPath.add(file);
                    }

                    for (File userInput: getPath){

                        if(userInput.isFile()){
                            filteredFiles.add(userInput);
                        }
                        
                    }

                   
                    this.operations();
                    this.outputs();

                }
                
                else{
                    System.out.println("No entries found");
                }


            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
            } 

        }
       
    
       
        
        

    

    
    public void getFiles(String foldername) {
        File folder = new File(foldername);
        System.out.println("DOES EXIST " + folder.exists());
        //WORKS HERE
        System.out.println(folder.isDirectory());

        if (folder.isDirectory() == true){
            this.noPrevious = folder.listFiles();
            this.containsContent = true; 

        }
        else if (folder.isFile()){ 
            noPrevious[0] = folder;
            this.containsContent = true;
        }

        //printFiles();

    }

    

    //define these functions
    @Override
    public void operations() {
        switch (op) {

            case "EQ":
                if (filteredFiles != null ) {
                   

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
            
              if (filteredFiles != null ) {
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
        if (subFiles != null){
            for (File printFile : subFiles) {
                System.out.println(printFile.getName());
            }
        }
        else{
            System.out.println("No Files found");
        }
        
    }

}

