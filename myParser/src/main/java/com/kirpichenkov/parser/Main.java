package com.kirpichenkov.parser;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import javafx.geometry.Pos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;

/**
 * Created by Владислав on 11.06.2017.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("src/main/resources/order.xml");
        XStream xs = new XStream(new StaxDriver());
        xs.processAnnotations(Order.class);
        Order order = (Order) xs.fromXML(fis);

        XStream xstream = new XStream(new JettisonMappedXmlDriver ());
        String json = xstream.toXML(order);
        System.out.println(json);



        DataBase db = new DataBase();
        try {
        String url = "jdbc:mysql://127.0.0.1:3306/mydbtest1?autoReconnect=true&useSSL=false";
        String user = "kirpichenkov";
        String password = "1001";

        Connection con = DriverManager.getConnection(url, user, password);

        try {

            db.insert(con, order.getNumber(), order.getDate(), order.getDeliveryDate(), order.getCampainNumber(), order.getHead().getDeliveryPlace(), order.getHead().getSender(), order.getHead().getRecipient(), order.getHead().getEdiInterchangeID());

        }  finally {
                con.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    }


