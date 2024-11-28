/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc457wiltondatabase.Main;

/**
 *
 * @author newto
 */

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class CustomerShowTable {
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";

    public DefaultTableModel getCustomerTableModel() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Customer_ID", "Name", "Location"}, 0);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SERVER, ID, PW);
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT Customer_ID, Name, Location FROM along28db.Customer");

            while (rs.next()) {
                int custID = rs.getInt("Customer_ID");
                String Name = rs.getString("Name");
                String location = rs.getString("Location");
                model.addRow(new Object[]{custID, Name, location});
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        
        return model;
    }
}