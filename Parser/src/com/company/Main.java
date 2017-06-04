package com.company;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) {
        XStream xs = new XStream(new DomDriver());
        Contacts contact = new Contacts();

        try {
            FileInputStream fis = new FileInputStream("Contacts.xml");
            xs.fromXML(fis, contact);

            //Печать десериализованного объекта
            System.out.println(contact.toString());

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }


        InsertDb db = new InsertDb();
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/contactdb";
            String login = "postgres";
            String password = "1101";
            Connection con = DriverManager.getConnection(url, login, password);
            try {
                db.insert(con, contact.getfirstName(), contact.getlastName(), contact.getPhone(), contact.getEmail());
                db.select(con);
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

