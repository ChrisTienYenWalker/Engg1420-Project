package com.mycompany.Project;

import java.util.ArrayList;

public class NameFilter extends Processing_elements 
{
    private ArrayList<String> inputValues;
    private ArrayList<String> pastEntries;
    private ArrayList<String> outputValues = new ArrayList<String>();
    private String key;
    
    
    public NameFilter(ArrayList<String> inputValue, ArrayList<String> pastEntries) 
    {
        for (String text : inputValue) {
            System.out.println(text);

            if (text.contains("value") || text.contains("Value")) {
                this.key = (text.replaceAll("value", "").replaceAll(" ", "").replaceAll(":", ""));
            }

        }
        for (String files : pastEntries) {
            inputValue.add(files);
        }
        
        loopEntries(inputValue);
   }
    
    
    

    @Override
    public void operations()
    {

        if(local)
        {
            //if it's a directorty 
        }
        else
        {
            //remote
            String name = getEntriesRemoteFileName();
        }

        //if it's true use this function below: 
        //addFileToList()
           for (String entry : inputValues) 
           {
            if (entry.contains("name")) 
            {
                String name = entry.split(":")[1].trim();
                if (name.contains(key)) 
                {
                    outputValues.add(entry);
                }
                addFileToList();
            }
        }
    };
    
    
    // @Override
    // public void outputs() 
    // {
    //     System.out.println("Output:");
    //     for (String entry : outputValues) 
    //     {
    //         System.out.println(entry);
    //     }
    // };
    
    // public void setKey(String key) 
    // {
    //     this.key = key;
    // }
    
    
}