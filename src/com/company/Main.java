package com.company;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter search term: ");
        String input = scanner.nextLine();

        try {

            File startingFolder = new File("testData");
            System.out.println("Starting Path: ");
            System.out.println(startingFolder.getCanonicalPath());
            System.out.println();
            System.out.println("Search result for " + input );
            printMatchingFile(input, startingFolder);


        } catch (Exception e) {
            System.out.println("Oops");
        }
    }

    public static void printMatchingFile(String searchTerm, File file) {

        if (file.isFile()) {
            try {

                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    if (scanner.next().equals(searchTerm)) {
                        System.out.println(file.getAbsolutePath());
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Could not access " + file.getAbsolutePath());
                e.printStackTrace();
            }
        } else if (file.isDirectory()) {
            File[] paths = file.listFiles();
            for (File subFile: paths) {
                printMatchingFile(searchTerm, subFile);
            }
        }
    }
}

