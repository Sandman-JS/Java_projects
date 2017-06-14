package com.kirpichenkov.parser;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.thoughtworks.xstream.XStream;
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
        FileReader fis = new FileReader("src/main/resources/order.xml");
        XStream xs = new XStream(new StaxDriver());
        xs.processAnnotations(Order.class);     // inform XStream to parse annotations in Order class
        xs.processAnnotations(Head.class);
        xs.processAnnotations(Position.class);
        xs.processAnnotations(Characteristic.class);
        Head head = (Head) xs.fromXML(fis);
        System.out.println(head.getPositionList().get(0));










/*        DataBase db = new DataBase();
        db.getConnect();*/





    }
    }

