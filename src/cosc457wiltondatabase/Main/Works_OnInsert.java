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

public class Works_OnInsert {
    
    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";
    
    /**
     * Inserts a new record into the Works_On table.
     *
     * @param empID       The Employee ID
     * @param soID2       The Sales Order ID
     * @param description The description of the work
     */
    
  public void insertWorksOn(int empID, int soID2, String description) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SERVER, ID, PW);

            String insertWorks_Onsql = "INSERT INTO Works_On (EmpID, SO_ID2, Description) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(insertWorks_Onsql);
            pstmt.setInt(1, empID);
            pstmt.setInt(2, soID2);
            pstmt.setString(3, description);

            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted + "Data inserted into Works_On table successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    
}
