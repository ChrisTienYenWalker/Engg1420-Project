package com.mycompany.Project;

import java.util.ArrayList;
import java.io.*;
import java.util.List;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardDownRightHandler;

public class CountFilter extends Processing_elements {

    private ArrayList<File> pastEntries = new ArrayList<>();
    ArrayList<String> outputList = new ArrayList<>();
    private String key;
    private int min;

    public CountFilter(ArrayList<String> inputValues, ArrayList<String> pastEntries) {
        
        Boolean keyBool = false;
        Boolean minBool = false;
        for (String text : inputValues) {
            if(text.contains("Key")){
                keyBool = true;
            }
            if(text.contains("Min")){
                minBool = true;
            }
            if (text.contains("value") && keyBool) {
                key = text.replaceAll("value", "").replaceAll(" ", "").replaceAll(":", "");
                keyBool = false;
            }
            if (text.contains("value") && minBool) {
                min = Integer.parseInt(text.replaceAll("value", "").replaceAll(" ", "").replaceAll(":", ""));
                minBool = false;
            }
        }
        System.out.println("data " + min);
        System.out.println(key);
        for (String files : pastEntries) {
            inputValues.add(files);
        }
        
        loopEntries(inputValues);

        for (String text : data) { // use data arrayList to
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

    @Override
    public void operations() {
        Boolean totalKey = true;

        if (local == false) {
            try {
                File filename = new File(path);
                readfile(filename);

                for(String line: data){
                    System.out.println(line);
                        int count = 0;
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
                        }
                        else{
                            System.out.println("The Key is not found the min number of times.");
                        }
                    }
            catch (Exception e) {
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
                    outputList.add(path);
                }
            } else {
                System.out.println("File path not found");
            }
        }
    }



}
