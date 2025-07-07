/*
 * Zachariah King
 * 7/6/25
 * M8 Programming Assignment
 */

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ZachThreeThreads {

    // Number of characters each thread should output
    private static final int COUNT = 10_000;

    // JTextArea to display the combined output of all threads
    private JTextArea textArea;

    // Constructor sets up the GUI
    public ZachThreeThreads() {
        JFrame frame = new JFrame("ZachThreeThreads");

        // Initialize JTextArea with 30 rows and 60 columns
        textArea = new JTextArea(30, 60);

        // Use monospaced font for consistent character width
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        // Make JTextArea read-only to prevent user editing
        textArea.setEditable(false);

        // Put the text area inside a scroll pane so I can scroll through large output
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add scroll pane to the frame's content pane
        frame.getContentPane().add(scrollPane);

        // Automatically size the frame to fit contents
        frame.pack();

        // Close the program when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Start the three threads
    public void startThreads() {
        // Thread that outputs random letters
        Thread lettersThread = new Thread(() -> outputRandomLetters());

        // Thread that outputs random digits
        Thread digitsThread = new Thread(() -> outputRandomDigits());

        // Thread that outputs random special characters
        Thread specialCharsThread = new Thread(() -> outputRandomSpecialChars());

        // Start all three threads concurrently
        lettersThread.start();
        digitsThread.start();
        specialCharsThread.start();
    }

    // Outputs COUNT random lowercase letters to the text area
    private void outputRandomLetters() {
        Random random = new Random();

        for (int i = 0; i < COUNT; i++) {
            // Generate a random letter between 'a' and 'z'
            char letter = (char) ('a' + random.nextInt(26));

            // Append this letter to the text area
            appendText(String.valueOf(letter));
        }
    }

    // Outputs COUNT random digits (0-9) to the text area
    private void outputRandomDigits() {
        Random random = new Random();

        for (int i = 0; i < COUNT; i++) {
            // Generate a random digit character between '0' and '9'
            char digit = (char) ('0' + random.nextInt(10));

            // Append this digit to the text area
            appendText(String.valueOf(digit));
        }
    }

    // Outputs COUNT random special characters from the set {! @ # $ % & *}
    private void outputRandomSpecialChars() {
        Random random = new Random();

        // Array of allowed special characters
        char[] specialChars = {'!', '@', '#', '$', '%', '&', '*'};

        for (int i = 0; i < COUNT; i++) {
            // Pick a random character from specialChars array
            char ch = specialChars[random.nextInt(specialChars.length)];

            // Append this special character to the text area
            appendText(String.valueOf(ch));
        }
    }

    /*
     * Appends the given text to the JTextArea.
     * Since Swing components are not thread-safe, this method
     * uses SwingUtilities.invokeLater to update the text area
     * on the Event Dispatch Thread later on.
     */
    private void appendText(String text) {
        SwingUtilities.invokeLater(() -> textArea.append(text));
    }

    // Main method to run the program and test all threads
    public static void main(String[] args) {
        // Configure GUI creation and thread start on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Create an instance of ZachThreeThreads
            ZachThreeThreads app = new ZachThreeThreads();

            // Start the three threads that produce output
            app.startThreads();
        });
    }
}