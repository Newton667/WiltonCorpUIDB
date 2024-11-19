/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cosc457wiltondatabase;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author along28
 */
public class COSC457WiltonDatabase {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowMainGUI());
    }

    private static void createAndShowMainGUI() {
        JFrame frame = new JFrame("Database Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Add Menu Bar
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Options");
        JMenuItem officeMenuItem = new JMenuItem("Add Office");
        JMenuItem employeeMenuItem = new JMenuItem("Add Employee");

        menu.add(officeMenuItem);
        menu.add(employeeMenuItem);
        menuBar.add(menu);

        frame.setJMenuBar(menuBar);

        // Create main panel for initial page
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the Database Management System", SwingConstants.CENTER);
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

        frame.add(mainPanel, BorderLayout.CENTER);

        // Event handling for menu items
        officeMenuItem.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.add(createOfficePanel(frame), BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });

        employeeMenuItem.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.add(createEmployeePanel(frame), BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });

        frame.setVisible(true);
    }

    private static JPanel createOfficePanel(JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel idLabel = new JLabel("Office ID:");
        JTextField idField = new JTextField();

        JLabel fnameLabel = new JLabel("First Name:");
        JTextField fnameField = new JTextField();

        JLabel lnameLabel = new JLabel("Last Name:");
        JTextField lnameField = new JTextField();

        JLabel ssnLabel = new JLabel("SSN:");
        JTextField ssnField = new JTextField();

        JButton submitButton = new JButton("Submit");
        JButton viewAllButton = new JButton("View All");

        panel.add(idLabel);
        panel.add(idField);
        panel.add(fnameLabel);
        panel.add(fnameField);
        panel.add(lnameLabel);
        panel.add(lnameField);
        panel.add(ssnLabel);
        panel.add(ssnField);
        panel.add(submitButton);
        panel.add(viewAllButton);

        submitButton.addActionListener(e -> {
            String idText = idField.getText();
            String fname = fnameField.getText();
            String lname = lnameField.getText();
            String ssnText = ssnField.getText();

            try {
                int id = Integer.parseInt(idText);
                int ssn = Integer.parseInt(ssnText);
                insertIntoOffice(id, fname, lname, ssn);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers for Office ID and SSN.");
            }
        });

        viewAllButton.addActionListener(e -> {
            String records = getAllOffices();
            JOptionPane.showMessageDialog(frame, records, "All Offices", JOptionPane.INFORMATION_MESSAGE);
        });

        return panel;
    }

    private static JPanel createEmployeePanel(JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel empIdLabel = new JLabel("Employee ID:");
        JTextField empIdField = new JTextField();

        JLabel fnameLabel = new JLabel("First Name:");
        JTextField fnameField = new JTextField();

        JLabel lnameLabel = new JLabel("Last Name:");
        JTextField lnameField = new JTextField();

        JButton submitButton = new JButton("Submit");
        JButton viewAllButton = new JButton("View All");

        panel.add(empIdLabel);
        panel.add(empIdField);
        panel.add(fnameLabel);
        panel.add(fnameField);
        panel.add(lnameLabel);
        panel.add(lnameField);
        panel.add(submitButton);
        panel.add(viewAllButton);

        submitButton.addActionListener(e -> {
            String empIdText = empIdField.getText();
            String fname = fnameField.getText();
            String lname = lnameField.getText();

            try {
                int empId = Integer.parseInt(empIdText);
                insertIntoEmployee(empId, fname, lname);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number for Employee ID.");
            }
        });

        viewAllButton.addActionListener(e -> {
            String records = getAllEmployees();
            JOptionPane.showMessageDialog(frame, records, "All Employees", JOptionPane.INFORMATION_MESSAGE);
        });

        return panel;
    }

    private static void insertIntoOffice(int idOffice, String fname, String lName, int ssn) {
        final String ID = "along28";
        final String PW = "COSC*8zeos";
        final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";

        try (Connection con = DriverManager.getConnection(SERVER, ID, PW)) {
            String insertOfficeSQL = "INSERT INTO along28db.Office (idOffice, fname, lName, SSN) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertOfficeSQL)) {
                pstmt.setInt(1, idOffice);
                pstmt.setString(2, fname);
                pstmt.setString(3, lName);
                pstmt.setInt(4, ssn);

                int rowsInserted = pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, rowsInserted + " row(s) inserted into Office table.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private static void insertIntoEmployee(int empId, String fname, String lname) {
        final String ID = "along28";
        final String PW = "COSC*8zeos";
        final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";

        try (Connection con = DriverManager.getConnection(SERVER, ID, PW)) {
            String insertEmployeeSQL = "INSERT INTO along28db.Employee (EmployeeID, fName, lName) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertEmployeeSQL)) {
                pstmt.setInt(1, empId);
                pstmt.setString(2, fname);
                pstmt.setString(3, lname);

                int rowsInserted = pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, rowsInserted + " row(s) inserted into Employee table.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private static String getAllOffices() {
        final String ID = "along28";
        final String PW = "COSC*8zeos";
        final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";

        StringBuilder records = new StringBuilder("Office Records:\n");

        try (Connection con = DriverManager.getConnection(SERVER, ID, PW)) {
            String query = "SELECT * FROM along28db.Office";
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    records.append("ID: ").append(rs.getInt("idOffice"))
                            .append(", First Name: ").append(rs.getString("fname"))
                            .append(", Last Name: ").append(rs.getString("lName"))
                            .append(", SSN: ").append(rs.getInt("SSN"))
                            .append("\n");
                }
            }
        } catch (SQLException e) {
            records.append("Error fetching data: ").append(e.getMessage());
        }

        return records.toString();
    }

    private static String getAllEmployees() {
        final String ID = "along28";
        final String PW = "COSC*8zeos";
        final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";

        StringBuilder records = new StringBuilder("Employee Records:\n");

        try (Connection con = DriverManager.getConnection(SERVER, ID, PW)) {
            String query = "SELECT * FROM along28db.Employee";
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    records.append("ID: ").append(rs.getInt("EmployeeID"))
                            .append(", First Name: ").append(rs.getString("fName"))
                            .append(", Last Name: ").append(rs.getString("lName"))
                            .append("\n");
                }
            }
        } catch (SQLException e) {
            records.append("Error fetching data: ").append(e.getMessage());
        }

        return records.toString();
    }
}
