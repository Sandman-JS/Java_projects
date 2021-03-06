package com.kirpichenkov.logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggerFactory;

/**
 * Created by Владислав on 27.07.2017.
 */
public class Main {

    public static void main(final String args[]) throws IOException, SQLException {

        /*Thread run = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileInputStream fis = new FileInputStream(args[0]);
                    Properties props = new Properties();
                    props.load(fis);
                    newDB db = new newDB();
                    Connection con = DriverManager.getConnection(props.getProperty("dburl").trim(), props.getProperty("dbuser").trim(), props.getProperty("dbpass").trim());
                    while (true) {
                            db.select(con, Integer.valueOf(props.getProperty("interval")));
                        Thread.sleep(Integer.valueOf(props.getProperty("interval")) * 1000);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        });*/

        MyThread thread1 = new MyThread();

        thread1.work(new String[]{args[0]});



    }


    static class newDB {
        private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(newDB.class);
        static Statement stmt;
        static ResultSet rs;

        public void select(Connection con, int n) throws SQLException {

            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT t1.varSenderGLN, t1.varRecipientGLN, t2.varType, t3.varIndex FROM exite_ru.billing as t1\n" +
                    "JOIN exite_ru.doc_types as t2 ON t1.intTypeID = t2.intTypesID\n" +
                    "Join exite_ru.index_vardocnum as t3 ON t1.intDocID = t3.intDocsID " +
                    "where t1.varDate >= now() - interval " + n + " second");

            int rowCount = getResultSetRowCount(rs);
            if (rs.next()) {
                for (int i = 1; i <= rowCount; i++, rs.next()) {
                    String str = /*"sender - "+*/rs.getString("varSenderGLN") + " " + rs.getString("varRecipientGLN") + " " + rs.getString("varType") + " " + rs.getString("varIndex");
                    log.info(str);
                }
            } else log.info("No new documents");

            stmt.close();
            rs.close();


        }

        public static int getResultSetRowCount(ResultSet resultSet) {
            int size = 0;
            try {
                resultSet.last();
                size = resultSet.getRow();
                resultSet.beforeFirst();
            } catch (SQLException ex) {
                return 0;
            }
            return size;
        }
    }
}
