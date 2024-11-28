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

public class Purchase_OrderInsert{
    
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";
    
    // Method to insert an Accepts record into the database
    public void POInsert(int poID, int cust, String desc) {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection con = DriverManager.getConnection(SERVER, ID, PW);

            // SQL query to insert a new record into the Accepts table
            String insertAcceptsSQL = "INSERT INTO along28db.`Purchase Order` (PO_ID, Customer_ID, Description) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertAcceptsSQL)) {
                // Set parameters for the SQL query
                pstmt.setInt(1, poID);
                pstmt.setInt(2, cust);
                pstmt.setString(3, desc);

                // Execute the query and display the number of rows inserted
                int rowsInserted = pstmt.executeUpdate();
                System.out.println(rowsInserted + " row(s) inserted into Purchase Order table.");
            }

            // Close the connection
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            // Handle errors during database connection or SQL execution
            System.err.println("Error: " + e.getMessage());
        }
    }
}