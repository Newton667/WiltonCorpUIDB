package cosc457wiltondatabase.Main;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Sales_Order_TableShow {
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/along28db?serverTimezone=EST";

    public DefaultTableModel jScrollPane1SOTableShow() {
        // Define table headers for Sales Order table
        DefaultTableModel model = new DefaultTableModel(new String[]{"SO_ID", "OfficeID2"}, 0);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SERVER, ID, PW);
            Statement stmt = con.createStatement();
            
            // Query the Sales Order table
            ResultSet rs = stmt.executeQuery("SELECT SO_ID, OfficeID2 FROM `Sales Order`");

            // Populate the table model with data from the result set
            while (rs.next()) {
                int soID = rs.getInt("SO_ID");
                int officeID2 = rs.getInt("OfficeID2");
                model.addRow(new Object[]{soID, officeID2});
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        
        return model;
    }
}