package com.mycompany.Project;

import java.util.ArrayList;

public class ContentFilter extends Processing_elements {
    
    private String key;
    private ArrayList<String> inputValues;
    private ArrayList<String> outputValues = null;

    //constructor
<<<<<<< HEAD
    public ContentFilter(ArrayList<String> inputValue, ArrayList<String> pastEntries) {
        //if there's no entries use past entries
=======
    public ContentFilter(ArrayList<String> inputValue, String pastEntries) {
        
        setInputValues(entryList);
        setKey(key);
    }

    public void setInputValues(ArrayList<String> inputValues) {
        this.inputValues = inputValues;
    }
>>>>>>> af2fc71668b4fa84f42e5ea420910dca8b77d14b

    public void setKey(String key) {
        this.key = key;
    }

    
    
    //define these functions
    public void opertaions(){

        
        for (String element : inputValues) {
            
            try (BufferedReader reader = new BufferedReader(new FileReader(element))) {

                String line;

                while ((line = reader.readLine()) != null) {

                    if (!line.contains(key)) {
                        System.err.println("Key is not found in each line");
                        System.exit(1);
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
        
    };
    public void outputs() {
    };
}
