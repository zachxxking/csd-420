/*
 * Zachariah King
 * 7/20/25
 * Module 10 Programming Assignment
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class FanManager extends JFrame {
    // Input fields
    private JTextField idField, firstNameField, lastNameField, favoriteTeamField;

    // Buttons
    private JButton displayButton, updateButton;

    // Database connection settings
    private final String DB_URL = "jdbc:mysql://localhost:3306/databasedb";
    private final String DB_USER = "student1";
    private final String DB_PASSWORD = "pass";

    // Constructor sets up the UI
    public FanManager() {
        super("Fan Manager");

        // Layout: 6 rows x 2 columns, 5px spacing
        setLayout(new GridLayout(6, 2, 5, 5));

        // Add components to the window
        add(new JLabel("Fan ID:"));
        idField = new JTextField(10);
        add(idField);

        add(new JLabel("First Name:"));
        firstNameField = new JTextField(25);
        add(firstNameField);

        add(new JLabel("Last Name:"));
        lastNameField = new JTextField(25);
        add(lastNameField);

        add(new JLabel("Favorite Team:"));
        favoriteTeamField = new JTextField(25);
        add(favoriteTeamField);

        // Buttons to display and update
        displayButton = new JButton("Display");
        updateButton = new JButton("Update");

        add(displayButton);
        add(updateButton);

        // Register event handlers
        displayButton.addActionListener(e -> displayFan());
        updateButton.addActionListener(e -> updateFan());

        // Set up the frame
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center window
        setVisible(true);
    }

    // Display fan info from the database by ID
    private void displayFan() {
        int id = getIdInput();
        if (id == -1) return;  // Invalid ID

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT firstname, lastname, favoriteteam FROM fans WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Populate fields with data from database
                firstNameField.setText(rs.getString("firstname"));
                lastNameField.setText(rs.getString("lastname"));
                favoriteTeamField.setText(rs.getString("favoriteteam"));
            } else {
                JOptionPane.showMessageDialog(this, "No fan found with ID: " + id);
                clearFields();
            }

        } catch (SQLException ex) {
            showError(ex);
        }
    }

    // Update fan info in the database using the input fields
    private void updateFan() {
        int id = getIdInput();
        if (id == -1) return;  // Invalid ID

        // Get input values
        String first = firstNameField.getText().trim();
        String last = lastNameField.getText().trim();
        String team = favoriteTeamField.getText().trim();

        // Check that all fields are filled
        if (first.isEmpty() || last.isEmpty() || team.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, first);
            stmt.setString(2, last);
            stmt.setString(3, team);
            stmt.setInt(4, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Fan updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "No fan found with ID: " + id);
            }

        } catch (SQLException ex) {
            showError(ex);
        }
    }

    // Parses ID field input and validates it
    private int getIdInput() {
        try {
            return Integer.parseInt(idField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID.");
            return -1;
        }
    }

    // Clears all text fields
    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        favoriteTeamField.setText("");
    }

    // Show error message for SQL exceptions
    private void showError(SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
    }

    // Main method to run the app
    public static void main(String[] args) {
        // Load JDBC driver explicitly
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found.");
        }

        // Start the GUI
        SwingUtilities.invokeLater(FanManager::new);
    }
}