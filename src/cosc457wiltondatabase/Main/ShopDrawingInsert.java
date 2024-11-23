package cosc457wiltondatabase.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ShopDrawingInsert {

    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/along28db?serverTimezone=EST";

    // Method to insert into ShopDrawing table
    public void insertShopDrawing(int drawID, String status, String itemDesc2) {
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection con = DriverManager.getConnection(SERVER, ID, PW);

            // SQL query to insert into ShopDrawing table
            String insertShopDrawingSQL = "INSERT INTO `ShopDrawing` (DrawID, Status, itemDesc2) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertShopDrawingSQL)) {
                // Set parameters
                pstmt.setInt(1, drawID); // Draw ID
                pstmt.setString(2, status); // Status
                pstmt.setString(3, itemDesc2); // Item Description Foreign Key

                // Execute update
                int rowsInserted = pstmt.executeUpdate();
                System.out.println(rowsInserted + " row(s) inserted into ShopDrawing table.");
            }

            // Close connection
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Example usage
    public static void main(String[] args) {
        ShopDrawingInsert shopDrawingInsert = new ShopDrawingInsert();
        shopDrawingInsert.insertShopDrawing(
            1,                // DrawID
            "Approved",       // Status
            "Widget A"        // itemDesc2 (Foreign Key)
        );
    }
}
