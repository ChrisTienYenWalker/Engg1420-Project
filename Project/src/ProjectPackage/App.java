package ProjectPackage;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        String fileLocation = getFile();
        System.out.println(fileLocation);

        String s;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {
            while ( (s = reader.readLine()) != null) {
                System.out.println(s);
               String[] words = s.split("");
               for(int i = 0; i < words.length; i++){
               }
            }
            reader.close();
          }catch (FileNotFoundException ex){
               System.out.println(ex);
          }
          catch (IOException ex){
              System.out.println(ex);
          }
    }

    public static String getFile() {
        Scanner textScan = new Scanner(System.in);
        System.out.println("Enter File location: ");
        String fileLocation = textScan.nextLine();
        textScan.close();
        return fileLocation.strip();
    }
}
