/*
  Zachariah King
  6/15/25
  M4 Programming Assignment
*/

import java.util.LinkedList;
import java.util.Iterator;

public class LinkedListTraversalTest {

    public static void main(String[] args) {
        // Testing traversal methods using two different list sizes
        System.out.println("Testing with 50,000 integers:");
        runTest(50_000); // First test with 50,000 integers

        System.out.println("\nTesting with 500,000 integers:");
        runTest(500_000); // Then test with 500,000 integers
    }

    /*
      The traversal test for a LinkedList of the specified size;
      Compares the performance of using an Iterator vs. get(index)
    */
    private static void runTest(int size) {
        LinkedList<Integer> list = new LinkedList<>();

        // Populate the LinkedList with sequential integers from 0 to size-1
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        // Traversal Method 1: Using an Iterator

        long startIterator = System.nanoTime(); // Start timing

        Iterator<Integer> iterator = list.iterator(); // Create an iterator for the list
        int sumIterator = 0; // Sum to simulate processing

        // Traverse through the list using the iterator
        while (iterator.hasNext()) {
            sumIterator += iterator.next(); // Access next integer and add to sum
        }

        long endIterator = System.nanoTime(); // End timing


        // Traversal Method 2: Using get(index) method

        long startGet = System.nanoTime(); // Start timing

        int sumGet = 0; // Sum to simulate processing

        // Traverse through the list using get(index) in a loop
        for (int i = 0; i < list.size(); i++) {
            sumGet += list.get(i); // get(i) must traverse the list to reach index i
        }

        long endGet = System.nanoTime(); // End timing

        // Ensure both traversal methods produce the same result

        if (sumIterator != sumGet) {
            System.out.println("ERROR: Sums don't match! Iterator sum: " + sumIterator + ", get(i) sum: " + sumGet);
        } else {
            System.out.println("Sum of elements (verification): " + sumIterator);
        }

        // Print the performance result

        System.out.printf("Iterator traversal time: %.3f ms%n", (endIterator - startIterator) / 1_000_000.0);
        System.out.printf("get(index) traversal time: %.3f ms%n", (endGet - startGet) / 1_000_000.0);
    }
}