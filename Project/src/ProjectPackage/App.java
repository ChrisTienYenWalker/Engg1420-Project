package ProjectPackage;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class App {
    public static void main(String[] args) throws Exception {

        // get the fle location
        String fileLocation = getFile();
        System.out.println(fileLocation);
        // open file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {
            String s;
            // read each line indiviually
            String name = null, type = null;
            while ((s = reader.readLine()) != null && name == null) {
                if (s.contains("name") && name == null) {
                    s = s.replace("\"", "").replace("name", "").replace(":", "").replace(",", "");
                    name = s.stripIndent().strip();
                    s = reader.readLine();

                    //this part might be useless
                    if (s.contains("processing_elements")) {
                        type = "processing_elements";
                    }
                    else if(s.contains("entries")){
                        type = "entries";
                    }
                    else{
                        System.out.println("Is not a processing_elements or entries");
                        reader.close();
                    }
                    System.out.println(name);
                    System.out.println(type);
                    break;
                }
                // create a new function and pass reader in by reference; you'll be able to sort
            }
            generateFliters(reader);
            reader.close();

            // exception handling
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

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
        try {
            String s;
            int insideParameters = 0;
            boolean newfilter = false;
            String type = null;
            ArrayList <String> filterDetails = new ArrayList<String>();
            while ((s = reader.readLine()) != null) {
                if(s.contains("{")){
                    insideParameters++;
                }
                if(s.contains("}")){
                    insideParameters--;
                }
                if(insideParameters > 0){
                    newfilter = true;
                }
                if(insideParameters == 0 && type != null){
                    newfilter = false;
                    generatefilterClass(filterDetails, type);
                    filterDetails.removeAll(filterDetails);
                    type = null;
                }
                if(newfilter == true && !(s.strip().contains("{")|| s.strip().contains("}"))){
                    filterDetails.add(s.replace("\"", " ").replace(",", ""));
                    
                    if(s.contains("type") && type == null){
                        type = s;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    public static void generatefilterClass(ArrayList<String> inputValues, String type){
        System.out.println("\n");
        type = type.strip().replace("type", "").replace("\"", "").replace(":", "").replace(",", "");
        System.out.println(type.stripIndent());
        for (String num : inputValues) { 		      
            System.out.println(num); 		
       }
        switch(type.stripIndent()){
            case "List":    
                System.out.println("works");
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
