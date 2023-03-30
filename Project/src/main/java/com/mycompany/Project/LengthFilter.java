package com.mycompany.Project;

import java.util.ArrayList;
import java.io.*;

public class LengthFilter extends Processing_elements{
    private long length;
    private String Operator;
    private String localPath = "empty";
    private boolean firstEntry = false;
    private ArrayList<File>filteredFiles = null;
    private ArrayList<File>subFiles = null;

    public void setOperator(String operator){
        this.Operator = operator;
    }

    public String getOperator(){
        return this.Operator;
    }

    public void setLength(long length){
        this.length = length;
    }

    public long getLength(){
        return this.length;
    }

    public void setLocalPath(String localPath){
        this.localPath = localPath;
    }

    public String getLocalPath(){
        return this.localPath;
    }


    

    public LengthFilter(ArrayList<String> inputs, ArrayList<String> entries){
        String tempstr = null;

      

        for (String str : inputs){
             System.out.println(str);


             //check if local or remte (implement rmte case)
             if (str.contains("local"))
            {
                tempstr = str.replace("type", " ").replace(":", " ").strip();
                
            }

            //if local getPath
            if(str.contains("path") && tempstr.equals("local"))
            {
                localPath = str.replace("path :", " ").strip();           
            }
            //get length value
            if(str.contains("Length"))
            {
                tempstr = str.replace("name", " ").replace(":", " ").strip();                  
            }
            //parse length value to integer
            if (str.contains("value") && tempstr.equals("Length"))
            {
                tempstr = str.replace("value", " ").replace(":", " ").strip();
                setLength(Integer.parseInt(tempstr));
                
            }          
            //Operator found
            if (str.contains("Operator"))
            {
                tempstr = str.replace("name", " ").replace(":", " ").strip();              
            }          
            //set operator 
            if (str.contains("value") && tempstr.equals("Operator"))
            {
                tempstr = str.replace("value", " ").replace(":", " ").strip();
                setOperator(tempstr);
            }

        }
        //OUTPUTS
        System.out.println("EXTRACTED CONTENT");
        System.out.println(getOperator());
        System.out.println(getLocalPath());
        System.out.println(getLength());


       
        /*
         * There will be two cases
         * 1. If I am the first entry therefore I am extracting from a single file.
         * 2. I am taking away from previous entries.
         */

         if (getLocalPath() != null){
            firstEntry = true;
         }
         System.out.println(firstEntry);


         if (firstEntry = true && new File(getLocalPath()).exists())
         {
            filteredFiles = new ArrayList<File>();
            File file = new File(getLocalPath());     
            filteredFiles.add(file);

            this.operations();
            this.outputs();
         }

         else{
            if (!localPath.equals("empty"))
            System.out.println("File does not exist or is directory");
         }

         if (localPath.equals("empty")){
            System.out.println("PASSED");
            filteredFiles = new ArrayList<>();

            for (String filePath : entries){
                if (new File (filePath).exists()){
                    File file = (new File (filePath));
                    filteredFiles.add(file);
                }

            }

            this.operations();
            this.outputs();
         }






    }

    @Override
    public void operations()
    {
        

        switch (getOperator()) {

            case "EQ":
            subFiles = new ArrayList<>();
            if (firstEntry){
                  
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() == getLength())
                    {
                        subFiles.add(subFile);
                    }
                    
                    
                }
           
                
            }
            else if (!firstEntry){
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() == getLength())
                    {
                        subFiles.add(subFile);
                    }
                    
                    
                }
            }
                break;

            case "NEQ":
            subFiles = new ArrayList<>();
            if (firstEntry){
                  
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() != getLength())
                    {
                        subFiles.add(subFile);
                    }
                    
                    
                }
                
            }

            else if (!firstEntry){
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() != getLength())
                    {
                        subFiles.add(subFile);
                    }
                    
                    
                }
            }
                break;

            case "GT":
            subFiles = new ArrayList<>();
            if (firstEntry){
                  
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() > getLength())
                    {
                        subFiles.add(subFile);
                    }     
                }
                
            } else if (!firstEntry){
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() > getLength())
                    {
                        subFiles.add(subFile);
                    }     
                }
            }
                break;

            case "GTE":
            subFiles = new ArrayList<>();
            if (firstEntry)
            { 
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() >= getLength())
                    {
                        subFiles.add(subFile);
                    }
                }
                
            }
            else if (!firstEntry){
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() >= getLength())
                    {
                        subFiles.add(subFile);
                    }
                }
            }
            

                break;

            case "LT":    
            subFiles = new ArrayList<>();
                if (firstEntry)
                {    
                    for (File subFile : filteredFiles)
                    {
                        System.out.println("LENGTH " + subFile.length());
                        if (subFile.length() < getLength())
                        {
                            subFiles.add(subFile);
                        }                
                    }     
                }
                else if (!firstEntry){
                    for (File subFile : filteredFiles)
                    {
                        System.out.println("LENGTH " + subFile.length());
                        if (subFile.length() < getLength())
                        {
                            subFiles.add(subFile);
                        }
                    }
                }

                break;

            case "LTE":
            subFiles = new ArrayList<>();
            if (firstEntry)
            {
                  
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() <= getLength())
                    {
                        subFiles.add(subFile);
                    }
                    
                    
                }
                
            }
            else if (!firstEntry){
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() <= getLength())
                    {
                        subFiles.add(subFile);
                    }
                }
            }

                break;

            default:
                subFiles = new ArrayList<>();
                System.out.println("Operator does not exist, all files outtputted");

                      for (File subFile : filteredFiles)
                      {
                        subFiles.add(subFile);           
                      }
                
                break;

            }
        }
    
    

    @Override
    public void outputs()
    {

        if (subFiles != null){
            for (File printFiles: subFiles){
                System.out.println("FILES FILTERED: " + printFiles.getName());
            }
            
            System.out.println("if no files were outputted requirements were not met or no past entries");
        }
        else{
            System.out.println("No files found.");
        }
    }
 
}
