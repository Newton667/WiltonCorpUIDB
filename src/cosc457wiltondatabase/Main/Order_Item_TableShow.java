package cosc457wiltondatabase.Main;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Order_Item_TableShow {
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/along28db?serverTimezone=EST";

    public DefaultTableModel getSalesOrderItemTableModel() {
        // Define table headers for Sales Order Item table
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"SOItem", "SO_ID"}, 
            0
        );
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SERVER, ID, PW);
            Statement stmt = con.createStatement();

            // SQL query to select data from Sales Order Item table
            String query = "SELECT SOItem, SO_ID FROM `Sales Order Item`";

            ResultSet rs = stmt.executeQuery(query);

            // Populate the table model with data from the result set
            while (rs.next()) {
                int soItem = rs.getInt("SOItem");
                int soID = rs.getInt("SO_ID");

                model.addRow(new Object[]{soItem, soID});
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        
        return model;
    }
}
