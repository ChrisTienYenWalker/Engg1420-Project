package com.mycompany.Project;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class CountFilter extends Processing_Elements
{
    private ArrayList<File> entries = null;
    private String Key;
    private int Min;
    private ArrayList<File> subFiles = null;

<<<<<<< HEAD
    //constructor
    public CountFilter(ArrayList<String> inputValue, ArrayList<String> pastEntries) {
=======
    public ArrayList<File> getSubFiles() {
        return subFiles;
    }

    public void setSubFiles(ArrayList<File> subFiles) {
        this.subFiles = subFiles;
    }
    
    public void setKey(String Key){
        this.Key = Key;
    }
    
    public String getKey(){
        return Key;
    }
    
    public void setMin(int Min){
        this.Min = Min;
    }
    
    public int getMin(){
        return Min;
    }
    
    public void setEntries(ArrayList<File> entries){
        this.entries = entries;
    }
    
    public ArrayList<File> getEntries(){
        return entries;
    }
    
    public CountFilter(ArrayList<String> inputValue, String pastEntries) {
>>>>>>> af2fc71668b4fa84f42e5ea420910dca8b77d14b
        //if there's no entries use past entries

    }

    //define these functions
    public void opertaions(){

    };
    public void outputs() {
    };
}
