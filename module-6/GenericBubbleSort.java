/*
 * Zachariah King
 * 6/29/25
 * M6 Programming Assignment
 *
 * M6 Assignment Generic Bubble Sort: This program contains two generic methods using a bubble sort technique
 * to sort elements of varying types using the Comparable interface and the Comparator interface.
 * 
 */

import java.util.Arrays;
import java.util.Comparator;

public class GenericBubbleSort {

    /*
     * Generic bubble sort using natural ordering (Comparable)
     *
     * @param list The array to sort
     * @param <E>  The element type, must extend Comparable
     */

    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        int n = list.length;
        
        // Outer loop: number of passes
        for (int i = 0; i < n - 1; i++) {
            
            // Inner loop: compares adjacent elements
            for (int j = 0; j < n - 1 - i; j++) {
                // If current element is greater than the next, swap them
                if (list[j].compareTo(list[j + 1]) > 0) {
                    E temp = list[j];            // temporary variable for swapping
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
    }

    /*
     * Generic bubble sort using a custom comparator
     *
     * @param list       The array to sort
     * @param comparator Comparator defining the sort order
     * @param <E>        The element type
     */
    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        int n = list.length;
        
        // Outer loop: number of passes
        for (int i = 0; i < n - 1; i++) {
            
            // Inner loop: compares adjacent elements
            for (int j = 0; j < n - 1 - i; j++) {
                // Use comparator to compare elements
                if (comparator.compare(list[j], list[j + 1]) > 0) {
                    E temp = list[j];            // temporary variable for swapping
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
    }

    /*
     * Main method for testing both sorting methods
     */

    public static void main(String[] args) {
        
        // Test 1: Integer array using Comparable
        Integer[] intArray = {5, 2, 9, 1, 3};
        System.out.println("Original Integer array: " + Arrays.toString(intArray));
        
        // Sort using natural ordering (Comparable)
        bubbleSort(intArray);
        System.out.println("Sorted using Comparable: " + Arrays.toString(intArray));


        // Test 2: String array using Comparator
        String[] stringArray = {"lana", "taylor", "lorde", "charlixcx"};
        System.out.println("\nOriginal String array: " + Arrays.toString(stringArray));
        
        // Sort using a Comparator that compares strings by their length
        bubbleSort(stringArray, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());  // shorter strings return first
            }
        });
        System.out.println("Sorted by length using Comparator: " + Arrays.toString(stringArray));
    }
}