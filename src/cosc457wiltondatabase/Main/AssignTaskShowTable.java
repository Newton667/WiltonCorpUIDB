/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc457wiltondatabase.Main;

/**
 *
 * @author Adam
 */

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class AssignTaskShowTable {
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";

    public DefaultTableModel getAssignTaskTableModel() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Task_ID", "OfficeID2", "EMP_ID2", "Description"}, 0);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SERVER, ID, PW);
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT Task_ID, OfficeID2, EMP_ID2, Description FROM along28db.AssignTask JOIN along28db.TaskDescription "
                    + "ON Task_Id = taskIDdesc  ");

            while (rs.next()) {
                int taskId = rs.getInt("Task_ID");
                int Office = rs.getInt("OfficeID2");
                int emp = rs.getInt("EMP_ID2");
                String desc = rs.getString("Description");
                model.addRow(new Object[]{taskId, Office, emp, desc});
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        
        return model;
    }
}
