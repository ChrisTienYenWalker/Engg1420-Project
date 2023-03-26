package com.mycompany.Project;

import java.util.ArrayList;

public class Rename extends Processing_elements {

    private String suffix;
    //constructor
    public Rename(ArrayList<String> inputValue, ArrayList<String> pastEntries) {
        //if there's no entries use past entries

        for(String text: inputValue){
            if(text.contains("Suffix") || text.contains("suffix")){
                suffix = text.replaceAll("suffix", "").replaceAll(" ", "").replaceAll("", "");
            }
        }
    }

    //define these functions
    public void opertaions(){

    };
    public void outputs() {
    };
}