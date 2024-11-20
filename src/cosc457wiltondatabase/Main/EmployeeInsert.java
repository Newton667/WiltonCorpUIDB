/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc457wiltondatabase.Main;

/**
 *
 * @author newto
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeInsert {

    private static final String ID = "along28";
    private static final String PW = "COSC*8zeos";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";

    // Method to insert an employee into the database
    public void insertEmployee(int employeeID, String firstName, String lastName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SERVER, ID, PW);

            String insertEmployeeSQL = "INSERT INTO along28db.Employee (EmployeeID, fName, lName) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertEmployeeSQL)) {
                pstmt.setInt(1, employeeID);
                pstmt.setString(2, firstName);
                pstmt.setString(3, lastName);

                int rowsInserted = pstmt.executeUpdate();
                System.out.println(rowsInserted + " row(s) inserted into Employee table.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
    }
}
