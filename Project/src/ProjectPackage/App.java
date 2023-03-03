package ProjectPackage;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {

        //get the fle location
        String fileLocation = getFile();
        System.out.println(fileLocation);

        String s;

        //open file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {

            //read each line indiviually 
            while ( (s = reader.readLine()) != null) {
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

        //create scanner
        Scanner textScan = new Scanner(System.in);

        //read in file
        System.out.println("Enter File location: ");
        String fileLocation = textScan.nextLine();
        textScan.close();

        return fileLocation.strip();
    }
}
