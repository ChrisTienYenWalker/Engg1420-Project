package com.mycompany.Project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.management.relation.RoleList;
import javax.xml.crypto.Data;

import org.apache.commons.io.FileUtils;

public class Rename extends Processing_elements {

    private String suffix = "";

    // constructor
    public Rename(ArrayList<String> inputValue, ArrayList<String> pastEntries) {

        //boolean value to help read in suffix value since they are on different lines
        boolean suffixBool = false;

        //loops through the inputValues since it will contain the suffix value
        for (String text : inputValue) {

            // it the line contauns the name value of suffix  
            //the following line should conatin the value for the suffix
            if (text.contains("suffix") || text.contains("Suffix")) {

                //set the suffix boolean to true
                suffixBool = true;
            }
            String subtext = text.substring(0,6).toLowerCase();
            System.out.println(subtext);
            
            //if the next line contains the word value and the suffix boolean is true(one of the lines before contained the word sufffix)
            if (subtext.contains("value") && suffixBool == true) {

                //gets rid of non relatvent information like value and : 
                suffix = text.replace("value", "").replace(".", "").replace(":", "").trim();
                suffixBool = false;
                break;
            }
        }

        //if there was no suffix
        if(suffix == ""){
            
            //return the past entry list
            addPastEntries(pastEntries);

        }
        else{
            
            //add the past entries to the new entries 
            inputValue.addAll(pastEntries);

            //go through each entry 
            loopEntries(inputValue);
        }

        // for(String text: outputList){
        // System.out.println(text);
        // }
    }

    protected void operations() {

        //if statement for if it's local or remote
        if (local) {

            //checks if it's a file or a folder
            File file = new File(path);
            if (file.isFile()) {

                String temp = path;
                temp = temp.substring(0, temp.length() - 4);
                temp = temp.concat(suffix).concat(".txt");
                generateLocalJson(temp);
                readfile(file);
                File newfile = new File(temp);
                try {
                    copyFileUsingStream(newfile);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                generateLocalJson(path.concat(suffix));
                File src = new File(path);
                File desFile = new File(path.concat(suffix));

                desFile.mkdir();

                try {
                    FileUtils.copyDirectory(src, desFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            generateLocalJson(path);
        } else {
            // if it's remote we create a file from the remote with the suffix.
            String temp = getEntriesRemoteFileName(this.entryID);
            if (false) {
                temp = temp.substring(0);
                temp = temp.concat(suffix).concat(".txt");
                generateLocalJson(temp);
                getEntriesRemote(Integer.parseInt(entryID));
                copyFileUsingStream(new File(temp));

            } else {

                generateLocalJson(temp.concat(suffix));
                File dir = new File(temp.concat(suffix));
                dir.mkdirs();
                getEntriesRemoteFileNamesDIR();
                ArrayList<String> childId = new ArrayList<String>(data);
                String absoluteDirPath = dir.getAbsolutePath();
                data.clear();
                for(String childFile: childId){
                    String childFileName = getEntriesRemoteFileName(childFile) + ".txt";
                    System.out.println(childFile);
                    getEntriesRemote(Integer.parseInt(childFile));
                    copyFileUsingStream(new File(absoluteDirPath + "\\" + childFileName));
                    data.clear();
                }

                //create folder
                //create files in folder
            }
            generateRemoteJson();
        }
    };

    // public Processing_elements(ArrayList<String> inputValue,
    // ArrayList<Processing_elements> pastEntries){

    // }

    // constructors will have 2 parameters; an arraylist of the past entries and an
    // arraylist of the information

    private void copyFileUsingStream(File dest) {
        try {
            FileWriter myWriter = new FileWriter(dest);
            for (String text : data) {
                myWriter.write(text + "\n");
            }
            myWriter.close();
        } catch (Exception e) {

        }
    }

}
