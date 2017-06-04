package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertDb {

    public  void insert(Connection con, String firstName, String lastName, String phone, String email) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO JC_CONTACT (FIRST_NAME, LAST_NAME, PHONE, EMAIL) VALUES (?, ?, ?, ?)");
        stmt.setString(1, firstName);
        stmt.setString(2, lastName);
        stmt.setString(3, phone);
        stmt.setString(4, email);
        stmt.executeUpdate();
        stmt.close();
    }

    public void select(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM JC_CONTACT");
        while (rs.next()) {
            String str = rs.getString("contact_id") + ":" + rs.getString(2);
            System.out.println("Contact:" + str);
        }
        rs.close();
        stmt.close();
    }
}