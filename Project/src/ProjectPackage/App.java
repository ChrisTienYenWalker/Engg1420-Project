package ProjectPackage;

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
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
               String[] words = s.split("");
               for(int i = 0; i < words.length; i++){
               }
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
}

