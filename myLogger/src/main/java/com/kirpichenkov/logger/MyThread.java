package com.kirpichenkov.logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Владислав on 28.07.2017.
 */
public class MyThread {

    public static void work (final String args[])
    {
        Thread run = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileInputStream fis = new FileInputStream(args[0]);
                    Properties props = new Properties();
                    props.load(fis);
                    Main.newDB db = new Main.newDB();
                    try {
                        while (true) {
                            try {
                                Connection con = DriverManager.getConnection(props.getProperty("dburl").trim(), props.getProperty("dbuser").trim(), props.getProperty("dbpass").trim());
                                db.select(con, Integer.valueOf(props.getProperty("interval")));
                                con.close();
                                fis.close();
                            } catch (SQLException ex){
                                ex.printStackTrace();
                            }
                            Thread.sleep(Integer.valueOf(props.getProperty("interval")) * 1000);
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }  catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        run.start();
    }
}
