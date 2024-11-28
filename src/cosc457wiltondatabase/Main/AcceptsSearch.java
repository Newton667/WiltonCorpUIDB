package cosc457wiltondatabase.Main;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class AcceptsSearch {
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/along28db?serverTimezone=EST";

    public DefaultTableModel getAcceptsSearchTable(int OfficeID) {
        // Define table model with appropriate columns
        DefaultTableModel model = new DefaultTableModel(new String[]{"First Name", "Last Name", "Office ID", "Purchase Order ID", "Action", "Customer ID", "Name"}, 0);

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            Connection con = DriverManager.getConnection(SERVER, ID, PW);

            // Prepare SQL query with parameterized employeeID
            String query = "SELECT Office.fname, Office.lName, Office.idOffice, `Purchase Order`.Po_Id AS PurchaseOrder, " +
                           "Accepts.action, Customer.Customer_Id, Customer.Name " +
                           "FROM along28db.Office " +
                           "JOIN along28db.Accepts ON Accepts.OfficeID = Office.idOffice " +
                           "JOIN along28db.`Purchase Order` ON Accepts.PurchaseOrder = `Purchase Order`.Po_Id " +
                           "JOIN along28db.Customer ON Customer.Customer_ID = `Purchase Order`.Customer_ID " +
                           "WHERE Office.idOffice = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setInt(1, OfficeID); // Set employeeID parameter

                // Execute query
                ResultSet rs = pstmt.executeQuery();

                // Populate the table model with data
                while (rs.next()) {
                    String firstName = rs.getString("fname");
                    String lastName = rs.getString("lName");
                    int oID = rs.getInt("idOffice");
                    String action = rs.getString("action");
                    int PurchaseOrder = rs.getInt("PurchaseOrder");
                    int cust = rs.getInt("Customer_ID");
                    String name = rs.getString("Name");
                    model.addRow(new Object[]{firstName, lastName, oID, PurchaseOrder, action, cust, name});
                }
            }

            // Close the connection
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }

        return model;
    }
}
