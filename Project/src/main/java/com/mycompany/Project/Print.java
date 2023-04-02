package com.mycompany.Project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.function.Consumer;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;

public class Print extends Processing_elements {

    // constructor
    protected Print(ArrayList<String> inputValue, ArrayList<String> pastEntries) {

        for (String files : pastEntries) {
            inputValue.add(files);
        }

        loopEntries(inputValue);


    }

    // define these functions
    protected void operations() {

        if (local) {
            try {
                File file = new File(path);

                // if local
                if (file.isFile()) {

                    String absolute = file.getAbsolutePath();

                    long length = file.length();

                    String[] split = path.split("\\\\");

                    System.out.println("Path: " + absolute + "\nLength: " + Long.toString(length) + "\nName: " + split[split.length - 1] + "\n");
                }

                // if remote
                else {
                    String absolute = file.getAbsolutePath();

                    long length = getFolderSize(file);// length function in processing elements;

                    String[] split = path.split("\\\\");

                    System.out.println("Path: " + absolute + "\nLength: " + Long.toString(length) + "\nName: " + split[split.length - 1] + "\n");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("reading Remote");
            // remote:
            String name = getEntriesRemoteFileName(this.entryID);

            String absolute = getEntriesAbsolutePath();

            long length = 0;
            if(true){
                getEntriesRemoteFileNamesDIR();
                for(String childFile: data){
                    length += getRemoteFileSize(childFile);// length function in processing elements;
                }
            }else{
                length = getRemoteFileSize(this.entryID);// length function in processing elements;
            }

            System.out.println("Path: " + absolute + "\nLength: " + Long.toString(length) + "\nName: " + name + "\n");

        }

    };

    protected long getFolderSize(File folder) {
        long length = 0;

        // listFiles() is used to list the
        // contents of the given folder
        File[] files = folder.listFiles();

        int count = files.length;

        // loop for traversing the directory
        for (int i = 0; i < count; i++) {
            if (files[i].isFile()) {
                length += files[i].length();
            } else {
                length += getFolderSize(files[i]);
            }
        }
        return length;
    }

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

}
