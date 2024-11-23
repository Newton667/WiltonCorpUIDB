package cosc457wiltondatabase.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; // Import for ResultSet
import java.sql.SQLException;

public class Sales_OrderDescriptionInsert1 {

    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/along28db?serverTimezone=EST";

    // Method to insert a sales order description into the database
    public void insertSalesOrderDescription(int soID, String status, String date, int contractNumber, String salesOrderDescription) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SERVER, ID, PW);

            // Insert into SO Description table
            String insertSalesOrderDescriptionSQL =
                "INSERT INTO `SO Description` (SODesc, Status, Date, `Contract#`, Description, SOID) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertSalesOrderDescriptionSQL)) {
                pstmt.setInt(1, soID); // Use SO_ID as SODesc
                pstmt.setString(2, status);
                pstmt.setString(3, date); // Ensure correct date format
                pstmt.setInt(4, contractNumber);
                pstmt.setString(5, salesOrderDescription);
                pstmt.setInt(6, soID); // Link SOID to SO_ID in Sales Order
                pstmt.executeUpdate();
            }

            System.out.println("SO Description added successfully!");

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error while inserting into SO Description: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
