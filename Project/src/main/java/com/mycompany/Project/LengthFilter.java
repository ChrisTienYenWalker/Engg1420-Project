package com.mycompany.Project;

import java.util.ArrayList;
import java.io.*;



import java.util.function.Consumer;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;

public class LengthFilter extends Processing_elements{
    private long length;
    private String Operator;
    private ArrayList<File>filteredFiles = null;
    private ArrayList<File>subFiles = null;

    public void setOperator(String operator){
        this.Operator = operator;
    }

    public String getOperator(){
        return this.Operator;
    }

    public void setLength(long length){
        this.length = length;
    }

    public long getLength(){
        return this.length;
    }

    


    

    public LengthFilter(ArrayList<String> inputs, ArrayList<String> entries){
        String tempstr = "";
        for (String text : inputs) {
            System.out.println(text);


            if (text.contains("Length")){
                tempstr = "Length";
            }

            if (text.contains("Operator")){
                tempstr = "Operator";
            }

            if ((text.contains("value") || text.contains("Value")) && tempstr.equals("Length")) {
                this.length = Long.parseLong((text.replaceAll("value", "").replaceAll(" ", "").replaceAll(":", ""))); 
            }
            if ((text.contains("value") || text.contains("Value")) && tempstr.equals("Operator")) {
                this.Operator = text.replaceAll("value", "").replaceAll(" ", "").replaceAll(":" , ""); 
            }
        }

        for (String files : entries) {
            inputs.add(files);
        }

        
        loopEntries(inputs);

        for (String text : data) { // use data arrayList to
            System.out.println(text);
        }
        //OUTPUTS
        System.out.println("EXTRACTED CONTENT");
        System.out.println(getOperator());
        System.out.println(getLength());


       
        /*
         * There will be two cases
         * 1. If I am the first entry therefore I am extracting from a single file.
         * 2. I am taking away from previous entries.
         */

        


         if (local == true && new File(path).isDirectory() == false)
         {
            filteredFiles = new ArrayList<File>();

            if (new File(path).exists()){
                File file = new File(path);     
                filteredFiles.add(file);
                this.operations();
            }    
         }
         else{
            System.out.println("File does not exist or is directory (SIZE 0)");
         }

         ///////    THIS IS FINE    //////////////

         if (local == false && inputs == null){
            filteredFiles = new ArrayList<File>();
            this.operations();
            this.length = getRemoteFileSize();           
         }

         if(inputs != null){
            filteredFiles = new ArrayList<>();

             for (String filePath : inputs){
                 if (new File (filePath).exists() && new File(filePath).isDirectory() == false){
                   File file = (new File (filePath));
                  filteredFiles.add(file);
                }

            }

             this.operations();
         }


              



    }


    public boolean isDirectory(File file){
        if (file.isDirectory()){
            return true;
        }
        else{
            return false;
        }  
    }

    @Override
    public void operations()
    {
        

        switch (getOperator()) {

            case "EQ":
            subFiles = new ArrayList<>();
            if (local){
                  
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() == getLength())
                    {
                        addFileToList();
                    }
                    
                    
                }
           
                
            }
            else if (!local){
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() == getLength())
                    {
                        addFileToList();
                    }
                    
                    
                }
            }
                break;

            case "NEQ":
            subFiles = new ArrayList<>();
            if (local){
                  
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() != getLength())
                    {
                        addFileToList();
                    }
                    
                    
                }
                
            }

            else if (!local){
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() != getLength())
                    {
                        addFileToList();
                    }
                    
                    
                }
            }
                break;

            case "GT":
            subFiles = new ArrayList<>();
            if (local){
                  
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() > getLength())
                    {
                        addFileToList();
                    }     
                }
                
            } else if (!local){
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() > getLength())
                    {
                        addFileToList();
                    }     
                }
            }
                break;

            case "GTE":
            subFiles = new ArrayList<>();
            if (local)
            { 
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() >= getLength())
                    {
                        addFileToList();
                    }
                }
                
            }
            else if (!local){
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() >= getLength())
                    {
                        addFileToList();
                    }
                }
            }
            

                break;

            case "LT":    
            subFiles = new ArrayList<>();
                if (local)
                {    
                    for (File subFile : filteredFiles)
                    {
                        System.out.println("LENGTH " + subFile.length());
                        if (subFile.length() < getLength())
                        {
                            addFileToList();
                        }                
                    }     
                }
                else if (!local){
                    for (File subFile : filteredFiles)
                    {
                        System.out.println("LENGTH " + subFile.length());
                        if (subFile.length() < getLength())
                        {
                            addFileToList();
                        }
                    }
                }

                break;

            case "LTE":
            subFiles = new ArrayList<>();
            if (local)
            {
                  
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() <= getLength())
                    {
                        addFileToList();
                    }
                    
                    
                }
                
            }
            else if (!local){
                for (File subFile : filteredFiles)
                {
                    System.out.println("LENGTH " + subFile.length());
                    if (subFile.length() <= getLength())
                    {
                        addFileToList();
                    }
                }
            }

                break;

            default:
                subFiles = new ArrayList<>();
                System.out.println("Operator does not exist, all files outtputted");

                     for(int i = 0; i < filteredFiles.size(); i++){
                        addFileToList();
                     }
                
                break;

            }
        }

        public long getRemoteFileSize() {

            String servicePrincipalKey = "x0BmysMxlH_XfLoc69Kk";
            String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiOGFkZTZjNTctZDIxNS00ZmYyLThkOTctOTE1YjRiYWUyZWIzIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogImNCeWdXYnh6YU9jRHZVcUdBU1RfcURTY0plcWw3aU9Ya19SZVFleUpiTzQiLAoJCSJ4IjogIjZNSXNuODRLanFtMEpTUmhmS2tHUTRzbGhkcldCbVNMWk9nMW5oWjhubFkiLAoJCSJ5IjogIlpkZ1M1YWIxdU0yaVdaWHVpdmpBc2VacC11LWlJUlc4MjFwZWhENVJ5bUkiLAoJCSJkIjogIldjN091cDFYV3FudjlEVFVzQWZIYmxGTDFqU3UwRWJRY3g0LXNqbG0xRmMiLAoJCSJpYXQiOiAxNjc3Mjk3NTU0Cgl9Cn0=";
            AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);
    
            RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                    servicePrincipalKey, accessKey);
            // create a new file and store the remote file in a new local file
    
            // delete old file
            File deleteFile = new File("Project\\remoteFile.txt");
            deleteFile.delete();
    
            // create new file
            final String FILE_NAME = "Project\\remoteFile.txt";
            Consumer<InputStream> consumer = inputStream -> {
                File exportedFile = new File(FILE_NAME);
                try (FileOutputStream outputStream = new FileOutputStream(exportedFile)) {
                    byte[] buffer = new byte[1024];
                    while (true) {
                        int length = inputStream.read(buffer);
                        if (length == -1) {
                            break;
                        }
                        outputStream.write(buffer, 0, length);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        inputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
    
            // get the file details
            client.getEntriesClient()
                    .exportDocument(this.repoID, Integer.parseInt(this.entryID), null, consumer)
                    .join();
            File remotefile = new File("Project\remoteFile.txt");
            return remotefile.length();
        }

}
