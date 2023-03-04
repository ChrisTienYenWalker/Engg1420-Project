package ProjectPackage;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;  

public class App {
    public static void main(String[] args) throws Exception {

        //get the fle location
        String fileLocation = getFile();
        System.out.println(fileLocation);  
        //open file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {
            String s;
            //read each line indiviually 
            String name = null, type;
            while ((s = reader.readLine()) != null) {
                if(s.contains("name") && name == null){
                    s = s.replace("\"", "").replace("name", "").replace(":", "").replace(",", "");
                    name = s.stripIndent().strip();
                    s = reader.readLine();
                    s = s.replace("\"", "").replace(":", "").replace("[", "");
                    if(!s.contains("processing_elements") && !s.contains("entries") ){
                        System.out.println("Is not a processing_elements or entries");
                        reader.close();
                    }
                    type = s.strip().stripIndent();
                    System.out.println(name);
                    System.out.println(type);
                }
                //create a new function and pass reader in by reference; you'll be able to sort 
            }
            reader.close();

            //exception handling 
          }catch (FileNotFoundException ex){
               System.out.println(ex);
          }
          catch (IOException ex){
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

    public static void generateFliters(){

    }
}

