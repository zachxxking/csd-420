/*
  Zachariah King
  6/15/25
  M3 Programming Assignment
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class TestRemoveDuplicates {

    public static void main(String[] args) {
        // Create new ArrayList to hold the original values
        ArrayList<Integer> originalList = new ArrayList<>();

        // Create a Random object to generate random numbers
        Random rand = new Random();

        // Fill the original list with 50 random integers between 1 and 20
        for (int i = 0; i < 50; i++) {
            int randomNumber = rand.nextInt(20) + 1; // Generates a number from 1 to 20
            originalList.add(randomNumber);
        }

        // Print the original list (which may have duplicates)
        System.out.println("Original List (with duplicates):");
        System.out.println(originalList);

        // Call the removeDuplicates method and store the result
        ArrayList<Integer> uniqueList = removeDuplicates(originalList);

        // Print the list after removing duplicates
        System.out.println("\nList after removing duplicates:");
        System.out.println(uniqueList);
    }

    // Generic static method removes duplicate elements from the ArrayList

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        // Create a HashSet from the list.
        HashSet<E> set = new HashSet<>(list);

        // Convert the HashSet back into an ArrayList and return it
        return new ArrayList<>(set);
    }
}