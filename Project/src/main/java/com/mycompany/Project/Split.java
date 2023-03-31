package com.mycompany.Project;

import java.io.*;
import java.util.ArrayList;

public class Split extends Processing_elements {

    private ArrayList<File> splitFiles = null;
    private ArrayList<File> splitFile = null;
    private int lines;
    private String localPath = "empty";

    // constructor that takes inputValue and entries as parameters
    public Split(ArrayList<String> inputValue, ArrayList<String> entries) {
        // process input values
        for (String str : inputValue) {
            // check if input value contains "Lines"
            if (str.contains("Lines")) {
                String[] parts = str.split(":");
                // check number of lines and assign it to this.lines
                this.lines = Integer.parseInt(parts[1].trim());
            } else if (str.contains("local")) {
                String[] parts = str.split(":");
                // assign the local path to this.localPath
                this.localPath = parts[1].trim();
            }
        }

        // check if localPath exists and process it
        if (!localPath.equals("empty") && new File(localPath).exists()) {
            // if localPath is a file, add it to splitFiles
            if (new File(localPath).isFile()) {
                splitFiles = new ArrayList<File>();
                splitFiles.add(new File(localPath));
                this.operations();
            } else {
                // print a message to indicate localPath is a directory
                System.out.println("localPath is a directory, ignoring.");
            }
        } else {
            // if localPath is not specified or does not exist, loop through entries and add files to splitFiles
            splitFiles = new ArrayList<File>();
            for (String entry : entries) {
                // create a new File object from each entry and check if it exists and is a file
                File file = new File(entry);
                if (file.exists() && file.isFile()) {
                    splitFiles.add(file);
                }
            }
            this.operations();
        }
    }

    // method to split each file in splitFiles into parts and write them to new files
    public void operations() {
        if (splitFiles != null) {
            // create a new ArrayList for splitFile
            splitFile = new ArrayList<File>();
            for (File file : splitFiles) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    int lineNumber = 0;
                    int partNumber = 1;
                    ArrayList<String> lines = new ArrayList<String>(this.lines);
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                        lineNumber++;
                        if (lineNumber == this.lines) {
                            // write lines to new file
                            String fileName = file.getName() + ".part" + partNumber + ".txt";
                            File newFile = new File(file.getParentFile(), fileName);
                            try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
                                // write each line to the new file
                                for (String l : lines) {
                                    writer.write(l);
                                    writer.newLine();
                                }
                            }
                            // add the new file to splitFile
                            splitFile.add(newFile);
                            // clear lines and reset lineNumber
                            lines.clear();
                            lineNumber = 0;
                            partNumber++;
                        }
                    }
                    // write remaining lines to new file
                    if (!lines.isEmpty()) {
                        String fileName = file.getName() + ".part" + partNumber + ".txt";
                        File newFile = new File(file.getParentFile(), fileName);
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
                            for (String l : lines) {
                                writer.write(l);
                                writer.newLine();
                            }
                        }
                        splitFile.add(newFile);
                    }
                } catch (IOException e) {
                    e.printStackTrace(); //Handles IOException
                }
            }
        }
    }

}