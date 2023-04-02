package com.mycompany.Project;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.SourceDataLine;

import org.apache.http.impl.io.SocketOutputBuffer;

public class NameFilter extends Processing_elements {
    private ArrayList<String> inputValues;
    private ArrayList<String> pastEntries;
    private ArrayList<String> outputValues = new ArrayList<String>();
    private String key;

    public NameFilter(ArrayList<String> inputValue, ArrayList<String> pastEntries) {
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

        if (local == true) 
        {
            File file = new File(path);
            if (file.isFile()) 
            {

                if (file.getName().contains(key)) 
                {
                    System.out.println("entries contain key");
                    addFileToList();
                } 
                else 
                {
                    System.out.println("No entries contain key");
                }
            } else {

                // if directory
                if (file.getName().contains(key)) 
                {
                    generateLocalJson(path);
                } 
                else 
                {
                    File folder = new File(path);
                    System.out.println(path);
                    System.out.println(folder.exists());

                    getEntriesLocalFileNames(path);

                    for (String text : data) 
                    {
                        File subFiles = new File(text);
                        System.out.println(subFiles.getName());
                        if (subFiles.getName().contains(key))
                        {
                            generateLocalJson(path);
                        } 
                        else 
                        {
                            System.out.println("No entries contain key");
                        }
                    }
                }
            }

        }

        else 
        {

            // getEntriesRemoteFileNamesDIR will give the file id for remote
            // getEntriesRemoteFileName will give file names based on id
            // remote

            getEntriesRemoteFileNamesDIR();

            String name = getEntriesRemoteFileName(entryID);
            if (name.contains(key)) {
                addFileToList();
            }
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
