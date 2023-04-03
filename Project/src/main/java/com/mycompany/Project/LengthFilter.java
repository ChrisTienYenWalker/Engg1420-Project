package com.mycompany.Project;

import java.util.ArrayList;
import java.io.*;
import java.util.function.Consumer;
import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;

public class LengthFilter extends Processing_elements{
    //Declarlation of Variables + Getters / Setters
    private long length = 0;
    private String Operator;
    
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

    
    //Constructor
    public LengthFilter(ArrayList<String> inputs, ArrayList<String> entries){
        String tempstr = "";
        //Extract Operators & Length Values.
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
        
        //Couldnt extract required variables.
        if(Operator == null || length == 0){
            System.out.println("Parameters couldn't be found in JSON, check formatting");
        }

        //add this file into inputs arrayList
        else
        {
        inputs.addAll(entries);
        loopEntries(inputs);
        }

        
        // for (String text : data) {
        //    System.out.println(text);
        // }
       
         //OUTPUTS
         System.out.println("EXTRACTED CONTENT");
         System.out.println(getOperator());
         System.out.println(getLength());
    }



    @Override
    public void operations()
    {
        //LOCAL CASE
        if(local){
        File subFile = new File(path);
        if (subFile.isFile()){
            System.out.println("LENGTH OF FILE: " + subFile.length());
            switch (getOperator()) {

                case "EQ":
                    if (subFile.length() == getLength())
                    {
                        addFileToList();
                    }
                    else
                    {
                        System.out.println("FILE: " + subFile.getName() + " Does not meet requirements for Operator");
                    }
                    break;
    
                case "NEQ":
                    if (subFile.length() != getLength())
                    {
                    addFileToList();
                    }
                    else
                    {
                        System.out.println("FILE: " + subFile.getName() + " Does not meet requirements for Operator");
                    }
                    break;
               
                case "GT":
                    if (subFile.length() > getLength())
                    {
                    addFileToList();
                    }
                    else
                    {
                        System.out.println("FILE: " + subFile.getName() + " Does not meet requirements for Operator");
                    }
                    break;
    
                case "GTE":
                    if (subFile.length() >= getLength())
                    {
                    addFileToList();
                    }
                    else
                    {
                        System.out.println("FILE: " + subFile.getName() + " Does not meet requirements for Operator");
                    }
                    break;
    
                case "LT":    
                    if (subFile.length() < getLength())
                    {
                    addFileToList();
                    }
                    else
                    {
                        System.out.println("FILE: " + subFile.getName() + " Does not meet requirements for Operator");
                    }
                    break;
    
                case "LTE":     
                    if (subFile.length() <= getLength())
                    {
                    addFileToList();
                    }
                    else
                    {
                        System.out.println("FILE: " + subFile.getName() + " Does not meet requirements for Operator");
                    }
                    break;
    
                default:
                    System.out.println("Operator does not exist, all files outtputted");
                    addFileToList();  
                    break;
                }
        }
        //LengthFilter only deals with directories
        else if(!subFile.isFile())
        {
            System.out.println("File does not exist or is directory.");
        }

        }

        //Remote Case
        else if (!local)
        {
            if (isRemoteDIR(entryID)){
                System.out.println("File is a directory, please enter a file");
            }
            else if (!isRemoteDIR(entryID)){
                //getEntriesRemote(Interger.parseInt(entryID));
                long fileSize = getRemoteFileSize(entryID);
                System.out.println("File Size: " + fileSize);

                switch (getOperator()) {

                    case "EQ":
                        if (fileSize == getLength())
                        {
                        addFileToList();
                        }
                        else{
                            entryFail();
                        }
                        break;
        
                    case "NEQ":
                        if (fileSize != getLength())
                        {
                        addFileToList();
                        }
                        else{
                            entryFail();
                        }
                        break;
                   
                    case "GT":
                        if (fileSize > getLength())
                        {
                        addFileToList();
                        }
                        else{
                            entryFail();
                        }
                        break;
        
                    case "GTE":
                        if (fileSize >= getLength())
                        {
                        addFileToList();
                        }
                        else{
                            entryFail();
                        }
                        break;
        
                    case "LT":    
                        if (fileSize < getLength())
                        {
                        addFileToList();
                        }
                        else{
                            entryFail();
                        }
                        
                        break;
        
                    case "LTE":
                        if (fileSize <= getLength())
                        {
                        addFileToList();
                        }
                        else{
                            entryFail();
                        }
                        break;
        
                    default:
                        System.out.println("Operator does not exist, all files outtputted");
                        addFileToList();  
                        break;
                    }
                
            }
        }
        }

    //Get File Size for Remote
    protected long getRemoteFileSize(String entryID) {
        String servicePrincipalKey = "x0BmysMxlH_XfLoc69Kk";
        String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiOGFkZTZjNTctZDIxNS00ZmYyLThkOTctOTE1YjRiYWUyZWIzIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogImNCeWdXYnh6YU9jRHZVcUdBU1RfcURTY0plcWw3aU9Ya19SZVFleUpiTzQiLAoJCSJ4IjogIjZNSXNuODRLanFtMEpTUmhmS2tHUTRzbGhkcldCbVNMWk9nMW5oWjhubFkiLAoJCSJ5IjogIlpkZ1M1YWIxdU0yaVdaWHVpdmpBc2VacC11LWlJUlc4MjFwZWhENVJ5bUkiLAoJCSJkIjogIldjN091cDFYV3FudjlEVFVzQWZIYmxGTDFqU3UwRWJRY3g0LXNqbG0xRmMiLAoJCSJpYXQiOiAxNjc3Mjk3NTU0Cgl9Cn0=";
        AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);
        RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                servicePrincipalKey, accessKey);
        // create a new file and store the remote file in a new local file  

        // delete old file
        File deleteFile = new File("Project\\remoteFile.txt");

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
                .exportDocument(this.repoID, Integer.parseInt(entryID), null, consumer)
                .join();
        long length = deleteFile.length();
        deleteFile.delete();
        return length;
    }

    //Simple Function used in Remote switch case
    private void entryFail()
    {
        System.out.println("Entry did not meet size requirements.");
    }

}


