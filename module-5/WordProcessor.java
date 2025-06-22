/*
  Zachariah King
  6/22/25
  M5 Programming Assignment

  WordProcessor reads a text file, extracts unique words (case-insensitive),
  and displays them in both ascending and descending order.
*/

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class WordProcessor {

    /*
      Reads all words from a given text file and returns a set of unique words,
      converted to lowercase for case-insensitive comparison.
     */

    public static Set<String> readUniqueWords(String filePath) throws IOException {
        // Read all lines from the file
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        // TreeSet with case-insensitive ordering ensures uniqueness and sorted order
        Set<String> wordSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

        // Loop through each line
        for (String line : lines) {
            // Split line into words using whitespace as the separator
            String[] words = line.split("\\s+");

            for (String word : words) {
                // Remove any non-letter characters (like punctuation), then lowercase
                word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

                // Only add non-empty words
                if (!word.isEmpty()) {
                    wordSet.add(word);
                }
            }
        }

        return wordSet;
    }

    /*
      Displays the contents of a set of words in either ascending or descending order.
    */

    public static void displayWords(Set<String> wordSet, boolean ascending) {
        // Convert set to list so we can reverse it if needed
        List<String> sortedWords = new ArrayList<>(wordSet);

        // Reverse the list if descending order is requested
        if (!ascending) {
            Collections.reverse(sortedWords);
        }

        // Print a header
        System.out.println(ascending ? "Ascending Order: " : "Descending Order: ");

        // Print each word
        for (String word : sortedWords) {
            System.out.println(word);
        }
    }

    /*
      Main method runs the test and then displays the sorted word list.
    */

    public static void main(String[] args) {
        String fileName = "collection_of_words.txt";

        try {
            // Run internal test to verify program behavior
            Test.run(fileName);

            // Read unique words from the file
            Set<String> uniqueWords = readUniqueWords(fileName);

            // Display final output
            System.out.println("\n=== Final Output ===\n");

            // Display in ascending order
            displayWords(uniqueWords, true);

            System.out.println(); // Add a line break

            // Display in descending order
            displayWords(uniqueWords, false);

        } catch (IOException e) {
            // Handle file read errors
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /*
      Internal static class to perform simple test assertions on the main functionality.
    */

    static class Test {
        public static void run(String fileName) {
            try {
                // Read and extract unique words
                Set<String> uniqueWords = readUniqueWords(fileName);

                // Check that all expected pop stars are present
                assert uniqueWords.contains("ariana") : "Missing 'ariana'";
                assert uniqueWords.contains("taylor") : "Missing 'taylor'";
                assert uniqueWords.contains("drake") : "Missing 'drake'";
                assert uniqueWords.contains("billie") : "Missing 'billie'";
                assert uniqueWords.contains("ed") : "Missing 'ed'";
                assert uniqueWords.contains("dua") : "Missing 'dua'";
                assert uniqueWords.contains("shakira") : "Missing 'shakira'";
                assert uniqueWords.contains("selena") : "Missing 'selena'";

                // Make sure we have exactly 8 unique names
                assert uniqueWords.size() == 8 : "Expected 8 unique names, got " + uniqueWords.size();

                System.out.println("All tests passed successfully!\n");

            } catch (AssertionError ae) {
                // Catch failed assertions
                System.err.println("Test failed: " + ae.getMessage());
            } catch (IOException e) {
                // Handle file I/O errors
                System.err.println("Test failed due to file error: " + e.getMessage());
            }
        }
    }
}
