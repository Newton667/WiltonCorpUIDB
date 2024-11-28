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

public class AcceptsInsert {
    
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";
    
    // Method to insert an Accepts record into the database
    public void insertAccepts(String action, int PurchaseOrder, int OfficeID) {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection con = DriverManager.getConnection(SERVER, ID, PW);

            // SQL query to insert a new record into the Accepts table
            String insertAcceptsSQL = "INSERT INTO along28db.Accepts (action, PurchaseOrder, OfficeID) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertAcceptsSQL)) {
                // Set parameters for the SQL query
                pstmt.setString(1, action);
                pstmt.setInt(2, PurchaseOrder);
                pstmt.setInt(3, OfficeID);

                // Execute the query and display the number of rows inserted
                int rowsInserted = pstmt.executeUpdate();
                System.out.println(rowsInserted + " row(s) inserted into Accepts table.");
            }

            // Close the connection
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            // Handle errors during database connection or SQL execution
            System.err.println("Error: " + e.getMessage());
        }
    }
}
