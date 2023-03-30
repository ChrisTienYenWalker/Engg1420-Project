package com.mycompany.Project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ContentFilter extends Processing_elements {

    private String key;
    private String pastEntries;
    private ArrayList<String> inputValues;
    private ArrayList<String> outputValues = new ArrayList<String>();

    ContentFilter(ArrayList<String> inputValues, ArrayList<String> pastEntries) {
        String repoID = null; 
        String entryID = null; 
        String path = null;
        int remote = 0;
        boolean local = false;

        for (String text : inputValues) {
            System.out.println(text);
  
            if (text.contains("value") || text.contains("value")) {
                key = text.replaceAll("value", "").replaceAll(" ", "").replaceAll(":", "");
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
            getEntriesRemoteFileNames(26);
        }catch(Exception e){
            // System.out.println(e);
        }

        try{
            getEntriesLocal(path);
        }catch(Exception e){
            // System.out.println(e);
        }
        for(String text: data){   // use data arrayList
            System.out.println(text);
        }

    }
    public void setPastEntries(String pastEntries) {
        this.pastEntries = pastEntries;
    } // doesn't matter
    

    public void operations(){

        for (String element : data) {

            try (BufferedReader reader = new BufferedReader(new FileReader(element))) {

                String line;

                while ((line = reader.readLine()) != null) {

                    if (!line.contains(key)) {
                        System.err.println("Key is not found in each line");   // May have to fix logic so program doesn't break when no key is found
                       
                    }

                }

                outputValues.add(element);

                reader.close();

            } catch (FileNotFoundException ex) {
                System.out.println(ex);

            } catch (IOException ex) {
                System.out.println(ex);
            }

        }
    }

    @Override
    public void outputs() {
        // return outputValues;
    }

}

