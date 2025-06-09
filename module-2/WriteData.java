
/*
  Zachariah King
  6/8/25
  M2 Programming Assignment 1
*/

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class WriteData {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] intArray = new int[5];
        double[] doubleArray = new double[5];

        // Generate random integers and doubles
        for (int i = 0; i < 5; i++) {
            intArray[i] = rand.nextInt(100); // integers between 0-99
            doubleArray[i] = rand.nextDouble() * 100; // doubles between 0.0-100.0
        }

        // Append to file
        try (FileWriter writer = new FileWriter("zking_datafile.dat", true)) {
            writer.write("Integers: ");
            for (int val : intArray) {
                writer.write(val + " ");
            }
            writer.write("\nDoubles: ");
            for (double val : doubleArray) {
                writer.write(String.format("%.2f ", val)); // round to 2 decimals
            }
            writer.write("\n---\n"); // separator for entries
            System.out.println("Data written to zking_datafile.dat successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the file.");
            e.printStackTrace();
        }
    }
}
