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

public class Works_OnShowTable {
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";

    public DefaultTableModel getWorksOnTableModel() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"EmpID", "SO_ID", "Description"}, 0);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SERVER, ID, PW);
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT EmpID, SO_ID2, Description FROM along28db.WorksOn");

            while (rs.next()) {
                int emp = rs.getInt("EmpID");
                int SO = rs.getInt("SO_ID2");
                String desc = rs.getString("Description");
                model.addRow(new Object[]{emp, SO, desc});
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        
        return model;
    }
}