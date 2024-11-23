package cosc457wiltondatabase.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Order_ItemInsert {

    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/along28db?serverTimezone=EST";

    // Method to insert into Sales Order Item table
    public void insertOrderItem(int soItem, int soIDSaleTable) {
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection con = DriverManager.getConnection(SERVER, ID, PW);

            // SQL query to insert into Sales Order Item table
            String insertOrderItemSQL = "INSERT INTO `Sales Order Item` (SOItem, SO_IDSaleTable) VALUES (?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertOrderItemSQL)) {
                // Set parameters
                pstmt.setInt(1, soItem);
                pstmt.setInt(2, soIDSaleTable);

                // Execute update
                int rowsInserted = pstmt.executeUpdate();
                System.out.println(rowsInserted + " row(s) inserted into Sales Order Item table.");
            }

            // Close connection
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Example usage
    public static void main(String[] args) {
        Order_ItemInsert orderItemInsert = new Order_ItemInsert();
        orderItemInsert.insertOrderItem(101, 1); // Example SOItem and SO_IDSaleTable
    }
}
