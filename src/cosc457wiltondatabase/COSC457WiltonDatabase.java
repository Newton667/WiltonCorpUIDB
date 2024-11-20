/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cosc457wiltondatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 *
 * @author along28
 */
public class COSC457WiltonDatabase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        final String ID = "along28";
        final String PW = "COSC*8zeos";
        final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db";

        try {
            Connection con = DriverManager.getConnection(SERVER, ID, PW);
            Statement stmt = con.createStatement();

            // Insert into the Office table
            String insertOfficeSQL = "INSERT INTO along28db.Office (idOffice, fname, lName, SSN) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertOfficeSQL)) {
                pstmt.setInt(1, 102); // Example Office ID
                pstmt.setString(2, "AdamJr"); // Example first name
                pstmt.setString(3, "Gooner"); // Example last name
                pstmt.setInt(4, 333336933); // Example SSN

                int rowsInserted = pstmt.executeUpdate();
                System.out.println(rowsInserted + " row(s) inserted into Office table.");
            }

            // Print out the Office table
            ResultSet rs = stmt.executeQuery("SELECT * FROM along28db.Office");

            while (rs.next()) {
                int OID = rs.getInt("idOffice");
                String first = rs.getString("fname");
                String last = rs.getString("lName");
                int SSN = rs.getInt("SSN");
                System.out.println(OID + ", " + first + ", " + last + ", " + SSN);
            }

            System.out.println();

            // Print out the Employee table
            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM along28db.Employee");

            while (rs2.next()) {
                int EMPid = rs2.getInt("EmployeeID");
                String first2 = rs2.getString("fName");
                String last2 = rs2.getString("lName");
                System.out.println(EMPid + ", " + first2 + ", " + last2);
            }

            System.out.println();

            // Print out AssignTask with associated TaskDescription
            Statement stmt3 = con.createStatement();
            ResultSet rs3 = stmt3.executeQuery(
                    "SELECT at.Task_ID, at.OfficeID2, at.EMP_ID2, td.Description " +
                            "FROM along28db.AssignTask at " +
                            "JOIN along28db.TaskDescription td ON at.Task_ID = td.taskIDDesc");

            while (rs3.next()) {
                int taskID = rs3.getInt("Task_ID");
                int OfficeID = rs3.getInt("OfficeID2");
                int EMPid2 = rs3.getInt("EMP_ID2");
                String description = rs3.getString("Description");
                System.out.println(taskID + ", " + OfficeID + ", " + EMPid2 + ", " + description);
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}

