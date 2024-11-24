/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc457wiltondatabase.Main;

/**
 *
 * @author zidan
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Assign_TaskInsert {
    
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";

    public void insertAssignTask(int Task_ID, int OfficeID2, int EMP_ID2, String Description) {
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            Connection con = DriverManager.getConnection(SERVER, ID, PW);

            // Prepare SQL query
            String insertAssign_TaskSQL = "INSERT INTO along28db.Assign_Task (Task_ID, OfficeID2, EMP_ID2, Description) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertAssign_TaskSQL)) {
                pstmt.setInt(1, Task_ID);
                pstmt.setInt(2, OfficeID2);
                pstmt.setInt(3, EMP_ID2);
                pstmt.setString(4, Description);

                int rowsInserted = pstmt.executeUpdate();
                System.out.println(rowsInserted + " row(s) inserted into Assign_Task table.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error inserting Assign_Task: " + e.getMessage());
        }
    }
    
}
