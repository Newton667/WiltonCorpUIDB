package cosc457wiltondatabase.Main;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Works_On_Search {
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/along28db?serverTimezone=EST";

    public DefaultTableModel getWorksOnSearchTable(int employeeID) {
        // Define table model with appropriate columns
        DefaultTableModel model = new DefaultTableModel(new String[]{"First Name", "Last Name", "SO_ID", "Description"}, 0);

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            Connection con = DriverManager.getConnection(SERVER, ID, PW);

            // Prepare SQL query with parameterized employeeID
            String query = "SELECT fName, lName, SO_ID2, Description " +
                           "FROM Employee " +
                           "JOIN WorksOn ON EmpID = EmployeeID " +
                           "WHERE EmpID = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setInt(1, employeeID); // Set employeeID parameter

                // Execute query
                ResultSet rs = pstmt.executeQuery();

                // Populate the table model with data
                while (rs.next()) {
                    String firstName = rs.getString("fName");
                    String lastName = rs.getString("lName");
                    int soID = rs.getInt("SO_ID2");
                    String desc = rs.getString("Description");
                    model.addRow(new Object[]{firstName, lastName, soID, desc});
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

