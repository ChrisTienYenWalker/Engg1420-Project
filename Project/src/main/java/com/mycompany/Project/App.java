package com.mycompany.Project;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class App {

    //arraylist for the past filters
    public static ArrayList<Processing_elements> processingClassList = new ArrayList<Processing_elements>();
    public static void main(String[] args) throws Exception {


        // get the fle location
        String fileLocation = getFile();
        System.out.println(fileLocation);

        // open file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {
       
            String s;
            String name = null;

            // read each line indiviually
            while ((s = reader.readLine()) != null && name == null) {

                //name gives senario name 
                if (s.contains("name") && name == null) {
                    s = s.replace("\"", "").replace("name", "").replace(":", "").replace(",", "");
                    name = s.stripIndent().strip();
                    s = reader.readLine();
                    System.out.println(name);
                    break;
                }
                //the next line should be processing
                //this line does not contain relavent information
                reader.readLine();
            }

            //generate filters
            generateFliters(reader);
            reader.close();

            // exception handling
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    //gets the json file from user input
    public static String getFile() {

        // create scanner
        Scanner textScan = new Scanner(System.in);

        // read in file
        System.out.println("Enter File location: ");
        String fileLocation = textScan.nextLine();
        textScan.close();

        return fileLocation.strip();
    }

    public static void generateFliters(BufferedReader reader) {

        //continue to read the file
        try {


            String s;
            boolean newfilter = false;

            //insideParameters is to help match curly brackets and make sure each scenario is seperate
            int insideParameters = 0;
            String type = null;

            //stores each line in an arraylist
            ArrayList <String> filterDetails = new ArrayList<String>();

            while ((s = reader.readLine()) != null) {

                //keeps track of if the reader is still in the same scenario
                if(s.contains("{")){
                    insideParameters++;
                }
                if(s.contains("}")){
                    insideParameters--;
                }

                //if it is exiting the scenario 
                if(insideParameters > 0){
                    newfilter = true;
                }

                //if it's exiting the scenario
                if(insideParameters == 0 && type != null){

                    //create a new filter class
                    newfilter = false;
                    generatefilterClass(filterDetails, type);
                    filterDetails.removeAll(filterDetails);
                    type = null;
                }

                //and details from each line as long as it is not a curly bracket line
                if(newfilter == true && !(s.strip().contains("{")|| s.strip().contains("}"))){


                    //help clean up text to make it easier to use
                    filterDetails.add(s.replace("\"", " ").replace(",", ""));
                    
                    //identify which type of scenario it is
                    if(s.contains("type") && type == null){
                        type = s;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    //create new scenario class
    public static void generatefilterClass(ArrayList<String> inputValues, String type){

        System.out.println("\n");

        type = type.strip().replace("type", "").replace("\"", "").replace(":", "").replace(",", "");
       
        System.out.println(type.stripIndent());

        //switch statement to match the type to the scenario
        //then create the new object and add it to the arraylist of past filters
        switch(type.stripIndent()){
            case "List":    
                System.out.println("works");
                LengthFilter filter = new LengthFilter(inputValues, processingClassList);
                processingClassList.add(filter);
                break;
            case "LengthFilter":    
                System.out.println("works");
                break;
            case "NameFilter":    
                System.out.println("works");
                break;
            case "ContentFilter":    
                System.out.println("works");
                break;
            case "CountFtiler":    
                System.out.println("works");
                break;
            case "Split":    
                System.out.println("works");
                break;
            case "Rename":    
                System.out.println("works");
                break;
            case "Print":    
                System.out.println("works");
                break;

            default:
                System.out.println("type does not exist");
                break;

        }
    }

}
