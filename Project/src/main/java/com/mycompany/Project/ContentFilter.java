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

        setInputValues(inputValues);
        for (String text : inputValues) {  
            if (text.contains("value") || text.contains("value")) {
                key = text.replaceAll("value", "").replaceAll(" ", "").replaceAll(":", "");
            }
            if(text.contains("type") && text.contains("local")){
                String path;
                if (text.contains("path")) {
                    path = text.replaceAll("path", "").replaceAll(" ", "").replaceAll(":", "");
                }
            }
            if (text.contains("type") && text.contains("remote")) {
                String repoID, entryID; 
                if (text.contains("repoId") || text.contains("value")) {
                    repoID = text.replaceAll("repoId", "").replaceAll(" ", "").replaceAll(":", "");
                }
                if (text.contains("entryId") || text.contains("value")) {
                    entryID = text.replaceAll("entryId", "").replaceAll(" ", "").replaceAll(":", "");
                }
            }
        }

        

    }

    public void setInputValues(ArrayList<String> inputValues) {
        this.inputValues = inputValues;
    }

    public void setPastEntries(String pastEntries) {
        this.pastEntries = pastEntries;
    } // doesn't matter

    
    @Override
    public void operations() {

        for (String element : inputValues) {

            try (BufferedReader reader = new BufferedReader(new FileReader(element))) {

                String line;

                while ((line = reader.readLine()) != null) {

                    if (!line.contains(key)) {
                        System.err.println("Key is not found in each line");
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
