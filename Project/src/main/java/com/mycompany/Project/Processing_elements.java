package com.mycompany.Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.AttributesClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntry;
import com.laserfiche.repository.api.clients.impl.model.Folder;    

abstract class Processing_elements {

    // array list for information
    ArrayList<String> data = new ArrayList<String>();
    ArrayList<String> outputList = new ArrayList<String>(); 
    protected String repoID = null;
    protected String entryID = null;
    protected String path = null;
    protected Boolean local = false;
    protected int remote = 0;

    // classes that will need to be defined
    public abstract void operations();

    public ArrayList<String>  outputs() {
        return outputList;
    };    
    
    public void addFileToList(){
        if(local == true && path != null){
            generateLocalJson(this.path);
        }
        else if(remote == 0 && entryID != null && repoID != null){
            generateRemoteJson();
        }
    }
    public void generateLocalJson(String path){
        outputList.add("type: local");
        outputList.add("path: " + path);
    };

    public void generateRemoteJson(){
        outputList.add("type: Remote");
        outputList.add("repoID: " + this.repoID);
        outputList.add("entryID: " + this.entryID);
    }
    public void loopEntries(ArrayList<String> inputValues){
        for (String text : inputValues) {
            System.out.println(text);

            if (text.contains("type") && text.contains("local"))
                local = true;

            if (local) {
                if (text.contains("path")) {
                    path = text.replaceAll("path", "").replaceAll(" ", "").replaceAll(":", "");
                    operations();
                    local = false;
                    path = null;

                }
            }
            if (text.contains("type") && text.contains("remote")) {
                remote = 2;
            }
            if (remote > 0) {
                if (text.contains("repoId") || text.contains("value")) {
                    repoID = text.replaceAll("repoId", "").replaceAll(" ", "").replaceAll(":", "");
                }
                if (text.contains("entryId") || text.contains("value")) {
                    entryID = text.replaceAll("entryId", "").replaceAll(" ", "").replaceAll(":", "");
                }
                remote--;

                operations();
                repoID = null;
                entryID = null;
            }
        }
    }
    public void getEntriesLocalFileNames(String filename) {
        File folder = new File(filename);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            data.add(listOfFiles[i].toString());
        }
    }

    // generates all information based on file location
    // places information in entries array
    public void getEntriesLocal(String filename) {

        // checks if it's a txt file or a directory
        if (filename.contains("txt")) {
            File file = new File(filename);
            readfile(file);
        } else {

            // gets all files in directory
            File folder = new File(filename);
            File[] listOfFiles = folder.listFiles();

            // will read all files
            for (int i = 0; i < listOfFiles.length; i++) {

                // makes sure the file is a file and won't crash the software
                if (listOfFiles[i].isFile()) {
                    readfile(listOfFiles[i]);
                } else if (listOfFiles[i].isDirectory()) {
                    System.out.println("Read in directory");
                }
            }
        }
    }

    // read file line by line and store in entries array
    public void readfile(File filename) {

        // try catch, just in case there's an error
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String s;
            // read each line indiviually while the file is not at the end
            while ((s = reader.readLine()) != null) {
                data.add(s);
            }
            reader.close();
            // exception handling
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void getEntriesRemoteFileNamesDIR() {
        String servicePrincipalKey = "x0BmysMxlH_XfLoc69Kk";
        String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiOGFkZTZjNTctZDIxNS00ZmYyLThkOTctOTE1YjRiYWUyZWIzIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogImNCeWdXYnh6YU9jRHZVcUdBU1RfcURTY0plcWw3aU9Ya19SZVFleUpiTzQiLAoJCSJ4IjogIjZNSXNuODRLanFtMEpTUmhmS2tHUTRzbGhkcldCbVNMWk9nMW5oWjhubFkiLAoJCSJ5IjogIlpkZ1M1YWIxdU0yaVdaWHVpdmpBc2VacC11LWlJUlc4MjFwZWhENVJ5bUkiLAoJCSJkIjogIldjN091cDFYV3FudjlEVFVzQWZIYmxGTDFqU3UwRWJRY3g0LXNqbG0xRmMiLAoJCSJpYXQiOiAxNjc3Mjk3NTU0Cgl9Cn0=";
        AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);
        RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                servicePrincipalKey, accessKey);
        ODataValueContextOfIListOfEntry result = client
                .getEntriesClient()
                .getEntryListing(this.repoID, Integer.parseInt(this.entryID), true, null, null, null, null, null, "name", null, null,
                        null)
                .join();
        List<Entry> RemoteEntries = result.getValue();
        for (Entry childEntry : RemoteEntries) {
            data.add(childEntry.getName());
        }
    }

    public String getEntriesRemoteFileName() {
        String servicePrincipalKey = "x0BmysMxlH_XfLoc69Kk";
        String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiOGFkZTZjNTctZDIxNS00ZmYyLThkOTctOTE1YjRiYWUyZWIzIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogImNCeWdXYnh6YU9jRHZVcUdBU1RfcURTY0plcWw3aU9Ya19SZVFleUpiTzQiLAoJCSJ4IjogIjZNSXNuODRLanFtMEpTUmhmS2tHUTRzbGhkcldCbVNMWk9nMW5oWjhubFkiLAoJCSJ5IjogIlpkZ1M1YWIxdU0yaVdaWHVpdmpBc2VacC11LWlJUlc4MjFwZWhENVJ5bUkiLAoJCSJkIjogIldjN091cDFYV3FudjlEVFVzQWZIYmxGTDFqU3UwRWJRY3g0LXNqbG0xRmMiLAoJCSJpYXQiOiAxNjc3Mjk3NTU0Cgl9Cn0=";
        AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);
        RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                servicePrincipalKey, accessKey);
        Entry entry = client.getEntriesClient().getEntry(this.repoID, Integer.parseInt(this.entryID), null).join();

        return entry.getName();
    }

    public String getEntriesAbsolutePath() {
        String servicePrincipalKey = "x0BmysMxlH_XfLoc69Kk";
        String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiOGFkZTZjNTctZDIxNS00ZmYyLThkOTctOTE1YjRiYWUyZWIzIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogImNCeWdXYnh6YU9jRHZVcUdBU1RfcURTY0plcWw3aU9Ya19SZVFleUpiTzQiLAoJCSJ4IjogIjZNSXNuODRLanFtMEpTUmhmS2tHUTRzbGhkcldCbVNMWk9nMW5oWjhubFkiLAoJCSJ5IjogIlpkZ1M1YWIxdU0yaVdaWHVpdmpBc2VacC11LWlJUlc4MjFwZWhENVJ5bUkiLAoJCSJkIjogIldjN091cDFYV3FudjlEVFVzQWZIYmxGTDFqU3UwRWJRY3g0LXNqbG0xRmMiLAoJCSJpYXQiOiAxNjc3Mjk3NTU0Cgl9Cn0=";
        AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);
        RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                servicePrincipalKey, accessKey);
        Entry entry = client.getEntriesClient().getEntry(this.repoID, Integer.parseInt(this.entryID), null).join();

        return entry.getFullPath();
    }

    public String getEntriesLength() {
        String servicePrincipalKey = "x0BmysMxlH_XfLoc69Kk";
        String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiOGFkZTZjNTctZDIxNS00ZmYyLThkOTctOTE1YjRiYWUyZWIzIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogImNCeWdXYnh6YU9jRHZVcUdBU1RfcURTY0plcWw3aU9Ya19SZVFleUpiTzQiLAoJCSJ4IjogIjZNSXNuODRLanFtMEpTUmhmS2tHUTRzbGhkcldCbVNMWk9nMW5oWjhubFkiLAoJCSJ5IjogIlpkZ1M1YWIxdU0yaVdaWHVpdmpBc2VacC11LWlJUlc4MjFwZWhENVJ5bUkiLAoJCSJkIjogIldjN091cDFYV3FudjlEVFVzQWZIYmxGTDFqU3UwRWJRY3g0LXNqbG0xRmMiLAoJCSJpYXQiOiAxNjc3Mjk3NTU0Cgl9Cn0=";
        AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);
        RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                servicePrincipalKey, accessKey);
        Entry entry = client.getEntriesClient().getEntry(this.repoID, Integer.parseInt(this.entryID), null).join();

        return entry.getVolumeName();
    }

    public void getEntriesRemote(Integer entryId) {

        // key details
        String servicePrincipalKey = "x0BmysMxlH_XfLoc69Kk";
        String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiOGFkZTZjNTctZDIxNS00ZmYyLThkOTctOTE1YjRiYWUyZWIzIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogImNCeWdXYnh6YU9jRHZVcUdBU1RfcURTY0plcWw3aU9Ya19SZVFleUpiTzQiLAoJCSJ4IjogIjZNSXNuODRLanFtMEpTUmhmS2tHUTRzbGhkcldCbVNMWk9nMW5oWjhubFkiLAoJCSJ5IjogIlpkZ1M1YWIxdU0yaVdaWHVpdmpBc2VacC11LWlJUlc4MjFwZWhENVJ5bUkiLAoJCSJkIjogIldjN091cDFYV3FudjlEVFVzQWZIYmxGTDFqU3UwRWJRY3g0LXNqbG0xRmMiLAoJCSJpYXQiOiAxNjc3Mjk3NTU0Cgl9Cn0=";
        AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);

        // open client
        RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                servicePrincipalKey, accessKey);

        // create instance of the client/open file location
        Entry entry = client.getEntriesClient().getEntry(this.repoID, entryId, null).join();

        // if the entry is a file
        if (entry.getEntryType().toString() == "Document") {
            createFileFromRemote(client, this.repoID, entryId);
            // place file information in arraylist
            File file = new File("Project\\remoteFile.txt");
            readfile(file);
        }

        // if the entryID type is a folder
        if (entry.getEntryType().toString() == "Folder") {
            ODataValueContextOfIListOfEntry result = client
                    .getEntriesClient()
                    .getEntryListing(this.repoID, Integer.parseInt(this.entryID), true, null, null, null, null, null, "name", null, null, null)
                    .join();
            List<Entry> RemoteEntries = result.getValue();
            for (Entry childEntry : RemoteEntries) {
                getEntriesRemote(childEntry.getId());
            }
        } else {
            System.out.println("Error occured");
        }

        client.close();
    }

    public void createFileFromRemote(RepositoryApiClient client, String repoId, int entryId) {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        // get the file details
        client.getEntriesClient()
                .exportDocument(repoId, entryId, null, consumer)
                .join();
    }
    // public Processing_elements(ArrayList<String> inputValue,
    // ArrayList<Processing_elements> pastEntries){

    // }

    // constructors will have 2 parameters; an arraylist of the past entries and an
    // arraylist of the information
}
