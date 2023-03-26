// package com.mycompany.Project;

<<<<<<< HEAD
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
=======
// import java.util.ArrayList;

// public class ContentFilter extends Processing_elements {
    
//     private String key;
//     private ArrayList<String> inputValues;
//     private ArrayList<String> outputValues = null;

//     //constructor
//     public ContentFilter(ArrayList<String> inputValue, ArrayList<String> pastEntries) {
//         //if there's no entries use past entries
//     public ContentFilter(ArrayList<String> inputValue, String pastEntries) {
        
//         setInputValues(entryList);
//         setKey(key);
//     }
>>>>>>> 47bd7562eb94274431cb7893433479090b579002

//     public void setInputValues(ArrayList<String> inputValues) {
//         this.inputValues = inputValues;
//     }

<<<<<<< HEAD
    public void setPastEntries(String pastEntries) {
        this.pastEntries = pastEntries;
    } // doesn't matter

    
    @Override
    public void operations() {

        for (String element : inputValues) {

            try (BufferedReader reader = new BufferedReader(new FileReader(element))) {
=======
//     public void setKey(String key) {
//         this.key = key;
//     }

    
    
//     //define these functions
//     public void operations(){

        
//         for (String element : inputValues) {
            
//             try (BufferedReader reader = new BufferedReader(new FileReader(element))) {
>>>>>>> 47bd7562eb94274431cb7893433479090b579002

//                 String line;

//                 while ((line = reader.readLine()) != null) {

<<<<<<< HEAD
                    if (!line.contains(key)) {
                        System.err.println("Key is not found in each line");
                    }

                }
=======
//                     if (!line.contains(key)) {
//                         System.err.println("Key is not found in each line");
//                         System.exit(1);
//                     }
//                 }
>>>>>>> 47bd7562eb94274431cb7893433479090b579002

//                 outputValues.add(element);

<<<<<<< HEAD
                reader.close();

            } catch (FileNotFoundException ex) {
                System.out.println(ex);
=======
//                 reader.close();
                
//             } catch (FileNotFoundException ex) {
//             System.out.println(ex);
>>>>>>> 47bd7562eb94274431cb7893433479090b579002

//             } catch (IOException ex) {
//                 System.out.println(ex);
//             }

<<<<<<< HEAD
        }
    }

    @Override
    public void outputs() {

        // return outputValues;
    }

}
=======
//         }
        
//     };
//     public void outputs() {
//     };
// }
>>>>>>> 47bd7562eb94274431cb7893433479090b579002
