package cosc457wiltondatabase.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sales_OrderInsert {

    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";

    // Method to insert a sales order into the database
    public void insertSalesOrder(int soID, int officeID2) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SERVER, ID, PW);

            String insertSalesOrderSQL = "INSERT INTO along28db.`Sales Order` (SO_ID, OfficeID2) VALUES (?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertSalesOrderSQL)) {
                pstmt.setInt(1, soID);
                pstmt.setInt(2, officeID2);

                int rowsInserted = pstmt.executeUpdate();
                System.out.println(rowsInserted + " row(s) inserted into Sales Order table.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
    }

    // Example usage
    public static void main(String[] args) {
        Sales_OrderInsert salesOrderInsert = new Sales_OrderInsert();
        salesOrderInsert.insertSalesOrder(101, 202); // Example SO_ID and OfficeID2 values
    }
}
