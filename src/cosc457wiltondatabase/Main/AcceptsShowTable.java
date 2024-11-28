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

public class AcceptsShowTable {
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";

    public DefaultTableModel getAcceptsTableModel() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"action", "PurchaseOrder", "OfficeID"}, 0);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SERVER, ID, PW);
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT action, PurchaseOrder, OfficeID FROM along28db.Accepts");

            while (rs.next()) {
                String action = rs.getString("action");
                int PO = rs.getInt("PurchaseOrder");
                String office = rs.getString("OfficeID");
                model.addRow(new Object[]{action, PO, office});
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        
        return model;
    }
}
