package cosc457wiltondatabase.Main;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Item_DescriptionTableShow {
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/along28db?serverTimezone=EST";

    public DefaultTableModel getItemDescriptionTableModel() {
        // Define table headers for Item Description table
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"itemDesc", "itemStatus", "invoiceStatus", "Cost", "Quantity", "SOItem2"}, 
            0
        );
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SERVER, ID, PW);
            Statement stmt = con.createStatement();

            // SQL query to select data from Item Description table
            String query = "SELECT itemDesc, itemStatus, invoiceStatus, Cost, Quantity, SOItem2 FROM `Item Description`";

            ResultSet rs = stmt.executeQuery(query);

            // Populate the table model with data from the result set
            while (rs.next()) {
                String itemDesc = rs.getString("itemDesc");
                String itemStatus = rs.getString("itemStatus");
                String invoiceStatus = rs.getString("invoiceStatus");
                double cost = rs.getDouble("Cost");
                int quantity = rs.getInt("Quantity");

                int soItem2 = rs.getInt("SOItem2");

                model.addRow(new Object[]{itemDesc, itemStatus, invoiceStatus, cost, quantity, soItem2});
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        
        return model;
    }
}
