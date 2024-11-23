package cosc457wiltondatabase.Main;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Sales_Order_TableShowDesc {
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/along28db?serverTimezone=EST";

    public DefaultTableModel jScrollPane1SOTableShow() {
        // Define table headers for SO Description table
        DefaultTableModel model = new DefaultTableModel(new String[]{"SODesc", "Status", "Date", "Contract#", "Description", "SOID"}, 0);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SERVER, ID, PW);
            Statement stmt = con.createStatement();
            
            // Query the SO Description table
            ResultSet rs = stmt.executeQuery("SELECT SODesc, Status, Date, `Contract#`, Description, SOID FROM `SO Description`");

            // Populate the table model with data from the result set
            while (rs.next()) {
                int soDesc = rs.getInt("SODesc");
                String status = rs.getString("Status");
                String date = rs.getString("Date");
                int contractNumber = rs.getInt("Contract#");
                String description = rs.getString("Description");
                int soID = rs.getInt("SOID");
                model.addRow(new Object[]{soDesc, status, date, contractNumber, description, soID});
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        
        return model;
    }
}
