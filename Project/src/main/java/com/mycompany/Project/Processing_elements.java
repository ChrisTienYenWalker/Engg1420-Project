package com.mycompany.Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

abstract class Processing_elements {

    //array list for information
    ArrayList<String> entries = new ArrayList<String>();
    ArrayList<String> outputList = new ArrayList<String>();

    //classes that will need to be defined
    public abstract void opertaions();
    public abstract void outputs();


    // generates all information based on file location
    //places information in entries array
    public void getEntriesLocal(String filename){

        //checks if it's a txt file or a directory
        if(filename.contains("txt")){
            File file = new File(filename);
            readfile(file);
        }
        else{

            //gets all files in directory
            File folder = new File(filename);
            File[] listOfFiles = folder.listFiles();
            
            //will read all files 
            for (int i = 0; i < listOfFiles.length; i++) {

                //makes sure the file is a file and won't crash the software
              if (listOfFiles[i].isFile()) {
                readfile(listOfFiles[i]);
              } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Read in directory");
              }
            }
        }
    }


    //read file line by line and store in entries array
    public void readfile(File filename){

        //try catch, just in case there's an error
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String s;
            // read each line indiviually while the file is not at the end
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
