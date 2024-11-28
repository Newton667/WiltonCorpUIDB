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

public class OfficeShowTable {
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";

    public DefaultTableModel getOfficeTableModel() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"idOffice", "fname", "lName", "ssn"}, 0);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SERVER, ID, PW);
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT idOffice, fname, lName, SSN FROM along28db.Office");

            while (rs.next()) {
                int OID = rs.getInt("idOffice");
                String F = rs.getString("fname");
                String L = rs.getString("lName");
                int ssn = rs.getInt("SSN");
                model.addRow(new Object[]{OID, F, L, ssn});
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        
        return model;
    }
}
