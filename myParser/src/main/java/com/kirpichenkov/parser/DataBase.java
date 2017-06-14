package com.kirpichenkov.parser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Владислав on 12.06.2017.
 */
public class DataBase {

    public  void insert(Connection con, String number, String date, String deliveryDate, String campainNumber, String deliveryPlace, String sender, String recipient, String ediInterchangeID) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO orders (Order_number, Order_date, Delivery_date, Campain_number, Delivery_place, Sender, Recipient, Edi_interchange_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, number);
        stmt.setString(2, date);
        stmt.setString(3, deliveryDate);
        stmt.setString(4, campainNumber);
        stmt.setString(5, deliveryPlace);
        stmt.setString(6, sender);
        stmt.setString(7, recipient);
        stmt.setString(8, ediInterchangeID);
        stmt.executeUpdate();
        stmt.close();
    }

}
