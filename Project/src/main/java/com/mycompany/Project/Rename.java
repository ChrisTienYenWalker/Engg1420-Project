package com.mycompany.Project;

import java.io.File;
import java.util.ArrayList;


public class Rename extends Processing_elements {

    private String suffix;
    ArrayList<String> entries;
    //constructor
    public Rename(ArrayList<String> inputValue, ArrayList<String> pastEntries) {
        for(String text: inputValue){
            if(text.contains("Suffix") || text.contains("suffix")){
                suffix = text.replaceAll("suffix", "").replaceAll(" ", "").replaceAll("", "");
            }
        }
        
        for (String files : pastEntries) {
            inputValue.add(files);
        }
        
        loopEntries(inputValue);
    }

    //define these functions
    public void operations(){
        if(local){
            File file = new File(path);
            if(file.isFile()){
                String temp = path;
                temp = temp.substring(0,temp.length()-4);
                temp = temp.concat(suffix);
                generateLocalJson(temp);
            }
            else{
                generateLocalJson(path.concat(suffix));
            }
            generateLocalJson(path);
        }
        else{
            //if it's remote we create a file from the remote with the suffix.
            String temp = getEntriesRemoteFileName();
            if(temp.contains("txt")){
                temp = temp.substring(0,temp.length()-4);
                temp = temp.concat(suffix);
                generateLocalJson(temp);
            }
            else{
                generateLocalJson(path.concat(suffix));
            }

            generateRemoteJson();
        }
    };
    
    // public Processing_elements(ArrayList<String> inputValue,
    // ArrayList<Processing_elements> pastEntries){

    // }

    // constructors will have 2 parameters; an arraylist of the past entries and an
    // arraylist of the information

}

