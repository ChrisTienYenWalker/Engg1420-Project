package com.mycompany.Project;

import java.io.File;
import java.util.ArrayList;

public class Print extends Processing_elements {
    ArrayList<String> entries;
    
    //constructor
    public Print(ArrayList<String> inputValue, ArrayList<String> pastEntries) {
        //if there's no entries use past entries
        pastEntries = entries;
    }

    //define these functions
    public void operations(){
        for(int i = 0; i < entries.size(); i++){
            String outputString = null;
            try{
                File file = new File(entries.get(i));

            //if local
            if(file.isFile()){

                String absolute = file.getAbsolutePath();

                long length = file.length();

                String[] split = entries.get(i).split("\\");
                
                outputString = "Path: " + absolute + ", length: " + length + ", name: " + split[split.length-1];
            }

            //if remote
            else{
                String absolute = file.getAbsolutePath();
                long length = 5;//length function in processing elements;

                String[] split = entries.get(i).split("\\");
                
                outputString = "Path: " + absolute + ", length: " + length + ", name: " + split[split.length-1];
            }
        }catch(Exception e){
            System.out.println(e);
        }
        //if it isn't local it's remote
        if(outputString == null){

            String entryId = entries.get(i);
           
            String absolute = "";
            
            long length = 5;//length function in processing elements;

            String[] split = entries.get(i).split("\\");

            outputString = "Path: " + absolute + ", length: " + length + ", name: " + split[split.length-1];

        }
       outputList.add(outputString);
            
        }
    };
    public void outputs() {
        //return entries
    };
}

