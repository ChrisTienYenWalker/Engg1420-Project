<<<<<<< HEAD
// package com.mycompany.Project;

// import java.io.File;
// import java.util.ArrayList;
// import java.util.Scanner;
// import java.io.*;

// public class CountFilter extends Processing_elements {
//     private ArrayList<File> entries = null;
//     private String Key;
//     private int Min;
//     private ArrayList<File> subFiles = null;

// <<<<<<< HEAD
//     // constructor
//     public CountFilter(ArrayList<String> inputValue, ArrayList<String> pastEntries) {

// =======
// >>>>>>> 5699710a51cf54207880e5361e196c035e0b8fa4
//     public ArrayList<File> getSubFiles() {
//         return subFiles;
//     }

//     public void setSubFiles(ArrayList<File> subFiles) {
//         this.subFiles = subFiles;
//     }

//     public void setKey(String Key) {
//         this.Key = Key;
//     }

//     public String getKey() {
//         return Key;
//     }

//     public void setMin(int Min) {
//         this.Min = Min;
//     }

//     public int getMin() {
//         return Min;
//     }

//     public void setEntries(ArrayList<File> entries) {
//         this.entries = entries;
//     }

//     public ArrayList<File> getEntries() {
//         return entries;
//     }
// <<<<<<< HEAD

//     public CountFilter(ArrayList<String> inputValue, String pastEntries) {
// =======
=======
package com.mycompany.Project;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class CountFilter extends Processing_elements {
    private ArrayList<File> entries = null;
    private String Key;
    private int Min;
    private ArrayList<File> subFiles = null;

    public ArrayList<File> getSubFiles() {
        return subFiles;
    }

    public void setSubFiles(ArrayList<File> subFiles) {
        this.subFiles = subFiles;
    }

    public void setKey(String Key) {
        this.Key = Key;
    }

    public String getKey() {
        return Key;
    }

    public void setMin(int Min) {
        this.Min = Min;
    }

    public int getMin() {
        return Min;
    }

    public void setEntries(ArrayList<File> entries) {
        this.entries = entries;
    }

    public ArrayList<File> getEntries() {
        return entries;
    }
>>>>>>> 18cbb84c323fda8d93b0d42e6c3b48293ebfe0f8
    
//     public static boolean hasKey(String entry, String Key, int Min){
//         try(BufferedReader br = new BufferedReader(new FileReader(entry))){
//             String line;
//             int count = 0;
//             while ((line = br.readLine()) != null){
//                 if (line.contains(key)){
//                     count++;
//                     if(count >= min){
//                         return true;
//                     }
//                 }
//             }
//         }catch (IOException e){
//             e.printStackTrace();
//         }
//         return false;
//     }
    
<<<<<<< HEAD
//     public CountFilter(ArrayList<String> entries, String Key, int Min) {
// >>>>>>> 5699710a51cf54207880e5361e196c035e0b8fa4
//         //if there's no entries use past entries
=======
    public static ArrayList<String> fileProcess (ArrayList<String> entries, String key, int min){
        List<String> result = new ArrayList<String>();
        
        for (int entry = 0; entry < entries.toString().length(); entry++){
            if (isFile(entry)){
                if (hasKey(entry, key, min)){
                    result.add(entry);
                }
            }
        }
        return (ArrayList<String>) result;
    }
}
    
    public CountFilter(ArrayList<String> entries, String Key, int Min) {
>>>>>>> 18cbb84c323fda8d93b0d42e6c3b48293ebfe0f8

//     }

//     // define these functions
//     public void operations() {

//     };

//     public void outputs() {
//     };
// }
