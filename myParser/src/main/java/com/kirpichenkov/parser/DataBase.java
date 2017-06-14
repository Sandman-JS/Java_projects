package com.kirpichenkov.parser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Владислав on 12.06.2017.
 */
public class DataBase {
    public static void getConnect(){

        String url = "jdbc:mysql://127.0.0.1:3306/mydbtest1?autoReconnect=true&useSSL=false";
        String user = "kirpichenkov";
        String password = "1001";


        try {
            Connection con = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
