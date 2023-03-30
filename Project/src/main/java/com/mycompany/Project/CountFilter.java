package com.mycompany.Project;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.List;

public abstract class CountFilter extends Processing_elements
{

    private String key;
    private int min;
    private ArrayList<File> pastEntries = new ArrayList<>();
    private String repoID = null;
    private String entryID = null;
    private String path = null;
    private Boolean local = false;
    ArrayList <File> fileList = new ArrayList<>();
    ArrayList <File> outputList = new ArrayList<>();

    public CountFilter(ArrayList<String> inputValues, ArrayList<String> pastEntries) {
        
        int remote = 0;
        
        for(String filePath : pastEntries){
            File newFile = new File(filePath);
            fileList.add(newFile);
        }

        for (String text : inputValues) {
            System.out.println(text);
  
            if (text.contains("value") || text.contains("Value")) {
                key = text.replaceAll("value", "").replaceAll(" ", "").replaceAll(":", "");
            }
            if (text.contains("value") || text.contains("value")){
                min = Integer.parseInt(text.replaceAll("value", "").replaceAll(" ", "").replaceAll(":", ""));
            }
            if(text.contains("type") && text.contains("local"))
                local = true;
            
            if(local){
                if (text.contains("path")) {
                    path = text.replaceAll("path", "").replaceAll(" ", "").replaceAll(":", "");
                }
            }
            if (text.contains("type") && text.contains("remote")) {
                remote = 2;
            }
            if(remote > 0){
                if (text.contains("repoId") || text.contains("value")) {
                    repoID = text.replaceAll("repoId", "").replaceAll(" ", "").replaceAll(":", "");
                }
                if (text.contains("entryId") || text.contains("value")) {
                    entryID = text.replaceAll("entryId", "").replaceAll(" ", "").replaceAll(":", "");
                }
                remote--;
            }
        }
        try{
            getEntriesRemoteFileNames("r-0001d410ba56", 26);
        }catch(Exception e){
            // System.out.println(e);
        }

        try{
            getEntriesLocal(path);
        }catch(Exception e){
            // System.out.println(e);
        }
        for(String text: data){   // use data arrayList to 
            System.out.println(text);
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

    public void opertaions() {
        Boolean totalKey = true;
    
        if (local == false) {
            try {
                for (int i = 0; i < fileList.size(); i++) {
                    try (BufferedReader br = new BufferedReader(new FileReader(fileList.get(i)))) {
                        String line;
                        int count = 0;
                        while ((line = br.readLine()) != null) {
                            if (line.contains(key)) {
                                count++;
                                if (count < min) {
                                    totalKey = false;
                                    break;
                                }
                            }
                        }
                        if (totalKey == true) {
                            outputList.add(fileList.get(i));
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            if (ifDirectory(path)) {
                System.out.println("Please use valid file path");
            } else if (ifFile(path)) {
                int count = 0;
                for (String line : data) {
                    if (line.contains(key)) {
                        count++;
                        if (count < min) {
                            totalKey = false;
                            break;
                        }
                    }
                }
                if (totalKey == true) {
                    outputList.add(new File(path));
                }
            } else {
                System.out.println("File path not found");
            }
        }
    }

    public void outputs(){ 
       
    }

}
