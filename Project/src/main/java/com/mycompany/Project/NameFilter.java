package com.mycompany.Project;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.SourceDataLine;

import org.apache.http.impl.io.SocketOutputBuffer;

public class NameFilter extends Processing_elements 
{
    private ArrayList<String> inputValues;
    private ArrayList<String> pastEntries;
    private ArrayList<String> outputValues = new ArrayList<String>();
    private String key;

    public NameFilter(ArrayList<String> inputValue, ArrayList<String> pastEntries) 
    {
        for (String text : inputValue) 
        {
            if (text.contains("value") || text.contains("Value")) 
            {
                this.key = (text.replaceAll("value", "").replaceAll(" ", "").replaceAll(":", ""));
            }

        }
        for (String files : pastEntries)
         {
            inputValue.add(files);

        }

        loopEntries(inputValue);
    }

    @Override
    public void operations() 
    {

        // if it is local
        if (local == true) 
        {
            File file = new File(path);

            // checking for if it is a single file
            if (file.isFile()) 
            {

                if (file.getName().contains(key)) 
                {
                    addFileToList();
                }
            }

            else 
            {

                // if directory
                if (file.getName().contains(key)) 
                {
                    generateLocalJson(path);
                } 
                else 
                {
                    File folder = new File(path);

                    getEntriesLocalFileNames(path);

                    for (String text : data)
                     {
                        File subFiles = new File(text);
                        if (subFiles.getName().contains(key)) 
                        {
                            generateLocalJson(subFiles.getAbsolutePath());
                        } 
                    }
                }
            }
        }

        else if (local == false)

        {

            // getEntriesRemoteFileNamesDIR will give the file id for remote
            // getEntriesRemoteFileName will give file names based on id
            // remote

            getEntriesRemoteFileNamesDIR();

            String name = getEntriesRemoteFileName(entryID);
            if (name.contains(key)) 
            {
                addFileToList();
            }
        }

        if(local)
        {
            //if it's a directorty 
        }
        else
        {
            //remote
            // String name = getEntriesRemoteFileName();
        }

    };

    // @Override
    // public void outputs()
    // {
    // System.out.println("Output:");
    // for (String entry : outputValues)
    // {
    // System.out.println(entry);
    // }
    // };

    // public void setKey(String key)
    // {
    // this.key = key;
    // }

}
