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

    private String suffix;
    ArrayList<String> entries;

    // constructor
    public Rename(ArrayList<String> inputValue, ArrayList<String> pastEntries) {
        for (String text : inputValue) {
            if (text.contains("Suffix") || text.contains("suffix")) {
                suffix = text.replaceAll("suffix", "").replaceAll(" ", "").replaceAll("", "");
            }
        }

        for (String files : pastEntries) {
            inputValue.add(files);
        }
        suffix = "hi";

        loopEntries(inputValue);

        System.out.println("\n");

        // for(String text: outputList){
        // System.out.println(text);
        // }
    }

    // define these functions
    public void operations() {
        if (local) {
            File file = new File(path);

            if (file.isFile()) {
                System.out.println("works");
                String temp = path;
                System.out.println(suffix);
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

            getEntriesRemoteFileNamesDIR();
        }
    };

    // public Processing_elements(ArrayList<String> inputValue,
    // ArrayList<Processing_elements> pastEntries){

    // }

    // constructors will have 2 parameters; an arraylist of the past entries and an
    // arraylist of the information

    public void copyFileUsingStream(File dest) {
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
