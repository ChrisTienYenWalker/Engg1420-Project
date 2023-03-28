// package com.mycompany.Project;

// import java.io.File;
// import java.util.ArrayList;

// public class Rename extends Processing_elements {

//     private String suffix;
//     //constructor
//     public Rename(ArrayList<String> inputValue, ArrayList<String> pastEntries) {
//         //if there's no entries use past entries
//         entries = pastEntries;
//         for(String text: inputValue){
//             if(text.contains("Suffix") || text.contains("suffix")){
//                 suffix = text.replaceAll("suffix", "").replaceAll(" ", "").replaceAll("", "");
//             }
//         }
//     }

//     //define these functions
//     public void operations(){
//         for(int i = 0; i < entries.size(); i++){
//             File file = new File(entries.get(i));
//             if(file.isFile()){
//                 String temp = entries.get(i);
//                 temp = temp.substring(0,temp.length()-4);
//                 temp = temp.concat(suffix);
//                 entries.add(i, temp);
//             }
//             else{
//                 entries.add(i, entries.get(i).concat(suffix));
//             }
//         }
//     };
//     public void outputs() {
//         //return entries;
//     };
// }