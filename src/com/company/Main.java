package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter search term: ");
        String input = scanner.nextLine();

        try {

            File startingFolder = new File("testData");
            System.out.println("Starting Path: " + startingFolder.getCanonicalPath());
            searchDirectory(input, startingFolder);

            //System.out.println("Contains: " + Arrays.toString(startingFolder.list()));
            //System.out.println("-------");
            //System.out.println();

            //printInfo(startingFolder);
        } catch (Exception e) {
            System.out.println("Oops");
        }
    }

    public static void searchDirectory(String searchTerm, File file) {

        if (file.isFile()) {
            try {
                /*
                Scanner scanner = new Scanner(file.getAbsoluteFile());
                while (scanner.hasNextLine()) {
                    if (scanner.next().equals(searchTerm)) {
                        System.out.println(file.getAbsolutePath());
                        break;
                    }
                }
                */

                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    if (scanner.next().equals(searchTerm)) {
                        System.out.println(file.getAbsolutePath());
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (file.isDirectory()) {
            File[] paths = file.listFiles();
            for (File subFile: paths) {
                searchDirectory(searchTerm, subFile);
            }
        }
    }

    public static void printInfo(File file) {
        // Om det är en vanlig fil: Skriv ut namnet på filen
        // Om det är en mapp: Skriv ut sökvägen på mappen, och gå in i mappen

        if (file.isFile()) {
            System.out.println("Fil: " + file.getName());
        } else if (file.isDirectory()) {
            try {
                System.out.println("Mapp: " + file.getCanonicalPath());

                File[] folderContents = file.listFiles();
                for (int i = 0; i < folderContents.length; i++) {
                    File f = folderContents[i];
                    printInfo(f);
                }

            } catch (Exception e) {
                System.out.println("Oops");
            }
        }
    }
}
