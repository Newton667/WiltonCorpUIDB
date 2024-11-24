/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc457wiltondatabase.Main;

/**
 *
 * @author zidan
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerInsert {
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";
    
    
     // Method to insert a customer into the database
    public void insertCustomer(int customerID, String customerName, String customerLocation) {
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            Connection con = DriverManager.getConnection(SERVER, ID, PW);

            // Prepare SQL query
            String insertCustomerSQL = "INSERT INTO along28db.Customer (Customer_ID, Name, Location) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertCustomerSQL)) {
                // Set query parameters
                pstmt.setInt(1, customerID);
                pstmt.setString(2, customerName);
                pstmt.setString(3, customerLocation);

                // Execute update and print success message
                int rowsInserted = pstmt.executeUpdate();
                System.out.println(rowsInserted + " row(s) inserted into Customer table.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error inserting customer: " + e.getMessage());
        }
    }
}
