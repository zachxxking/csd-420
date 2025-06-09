
/*
  Zachariah King
  6/8/25
  M2 Programming Assignment 2
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadData {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("zking_datafile.dat"))) {
            String line;
            System.out.println("Reading from zking_datafile.dat:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
