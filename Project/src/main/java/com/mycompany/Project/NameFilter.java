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
        this.inputValues = inputValue;
        this.pastEntries = pastEntries;
    }
    
    
    

    @Override
    public void operations()
    {
           for (String entry : inputValues) 
           {
            if (entry.contains("name")) 
            {
                String name = entry.split(":")[1].trim();
                if (name.contains(key)) 
                {
                    outputValues.add(entry);
                }
            }
        }
    };
    
    
    
    @Override
    public void outputs() 
    {
        System.out.println("Output:");
        for (String entry : outputValues) 
        {
            System.out.println(entry);
        }
    };
    
    public void setKey(String key) 
    {
        this.key = key;
    }
    
    
}
