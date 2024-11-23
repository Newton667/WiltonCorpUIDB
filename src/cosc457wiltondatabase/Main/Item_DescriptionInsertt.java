package cosc457wiltondatabase.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Item_DescriptionInsertt {

    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/along28db?serverTimezone=EST";

    // Method to insert into Item Description table
    public void insertItemDescription(String itemDesc, String itemStatus, String invoiceStatus, double cost, int quantity, int soItem2) {
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection con = DriverManager.getConnection(SERVER, ID, PW);

            // SQL query to insert into Item Description table
            String insertItemDescriptionSQL = "INSERT INTO `Item Description` (itemDesc, itemStatus, invoiceStatus, Cost, Quantity, SOItem2) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertItemDescriptionSQL)) {
                // Set parameters
                pstmt.setString(1, itemDesc); // Item Description
                pstmt.setString(2, itemStatus); // Item Status
                pstmt.setString(3, invoiceStatus); // Invoice Status
                pstmt.setDouble(4, cost); // Cost
                pstmt.setInt(5, quantity); // Quantity
                //pstmt.setString(6, itemDescriptioncol); // Item Description Column
                pstmt.setInt(6, soItem2); // Sales Order Item Foreign Key

                // Execute update
                int rowsInserted = pstmt.executeUpdate();
                System.out.println(rowsInserted + " row(s) inserted into Item Description table.");
            }

            // Close connection
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
