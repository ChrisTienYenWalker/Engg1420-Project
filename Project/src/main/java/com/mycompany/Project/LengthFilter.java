package com.mycompany.Project;

import java.util.ArrayList;

class LengthFilter extends Processing_elements {

    //constructor
    public LengthFilter(ArrayList<String> inputValue, ArrayList<Processing_elements> pastEntries) {
        //if there's no entries use past entries
        getEntriesLocal("Project\\src\\ProjectPackage");
        for(String name:entries){
            System.out.println(name);
        }

    }

    //define these functions
    public void opertaions(){

    };
    public void outputs() {
    };
}
