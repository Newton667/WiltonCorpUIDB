package cosc457wiltondatabase.Main;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ShopDrawingTableShow {
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/along28db?serverTimezone=EST";

    public DefaultTableModel getShopDrawingTableModel() {
        // Define table headers for ShopDrawing table
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"DrawID", "Status", "itemDesc2"}, 
            0
        );
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SERVER, ID, PW);
            Statement stmt = con.createStatement();

            // SQL query to select data from ShopDrawing table
            String query = "SELECT DrawID, Status, itemDesc2 FROM `ShopDrawing`";

            ResultSet rs = stmt.executeQuery(query);

            // Populate the table model with data from the result set
            while (rs.next()) {
                int drawID = rs.getInt("DrawID");
                String status = rs.getString("Status");
                String itemDesc2 = rs.getString("itemDesc2");

                model.addRow(new Object[]{drawID, status, itemDesc2});
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        
        return model;
    }
}
