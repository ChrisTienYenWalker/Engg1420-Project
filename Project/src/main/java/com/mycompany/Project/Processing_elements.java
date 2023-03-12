package com.mycompany.Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

abstract class Processing_elements {
    ArrayList<String> entries = new ArrayList<String>();
    ArrayList<String> outputList = new ArrayList<String>();
    public abstract void opertaions();
    public abstract void outputs();

    public void getEntriesLocal(String filename){
        if(filename.contains("txt")){
            File file = new File(filename);
            readfile(file);
        }
        else{
            File folder = new File(filename);
            File[] listOfFiles = folder.listFiles();
            
            for (int i = 0; i < listOfFiles.length; i++) {
              if (listOfFiles[i].isFile()) {
                readfile(listOfFiles[i]);
              } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Read in directory");
              }
            }
        }
    }

    public void readfile(File filename){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String s;
            // read each line indiviually
            while ((s = reader.readLine())!=null) {
                entries.add(s);
            }
            reader.close();
            // exception handling
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void getEntriesRemote(String repoId, String entryId){

    }

    //constructors will have 2 parameters; an arraylist of the past entries and an arraylist of the information
}
