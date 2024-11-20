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

public class EmployeeTableShow {
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";

    public DefaultTableModel getEmployeeTableModel() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"EmployeeID", "First Name", "Last Name"}, 0);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SERVER, ID, PW);
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT EmployeeID, fName, lName FROM along28db.Employee");

            while (rs.next()) {
                int employeeID = rs.getInt("EmployeeID");
                String firstName = rs.getString("fName");
                String lastName = rs.getString("lName");
                model.addRow(new Object[]{employeeID, firstName, lastName});
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        
        return model;
    }
}

