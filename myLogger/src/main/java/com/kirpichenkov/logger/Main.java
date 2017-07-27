package com.kirpichenkov.logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by Владислав on 27.07.2017.
 */
public class Main {

    public static void main(final String args[]) throws IOException, SQLException {

        Thread run = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        FileInputStream fis = new FileInputStream(args[0]);
                        Properties props = new Properties();
                        props.load(fis);
                        newDB db = new newDB();
                        Connection con = DriverManager.getConnection(props.getProperty("dburl").trim(), props.getProperty("dbuser").trim(), props.getProperty("dbpass").trim());
                        try {

                            db.select(con, Integer.valueOf(props.getProperty("interval")));
                        } finally {
                            con.close();
                            fis.close();
                        }
                        Thread.sleep(Integer.valueOf(props.getProperty("interval"))*1000); //1000 - 1 сек
                    } catch (InterruptedException ex) {
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        run.start();


    }






}

class newDB{
    private static final Logger log = Logger.getLogger(String.valueOf(newDB.class));
     static Statement stmt;
     static ResultSet rs;

public void  select(Connection con, int n) throws SQLException {

     stmt = con.createStatement();
    rs = stmt.executeQuery("SELECT t1.varSenderGLN, t1.varRecipientGLN, t2.varType, t3.varIndex FROM exite_ru.billing as t1\n" +
            "JOIN exite_ru.doc_types as t2 ON t1.intTypeID = t2.intTypesID\n" +
            "Join exite_ru.index_vardocnum as t3 ON t1.intDocID = t3.intDocsID " +
            "where t1.varDate >= now() - interval "+ n + " second");

    log.info("Данные за последние " + n + " секунд");

    int rowCount = getResultSetRowCount(rs);
    if (rs.next()){
        for (int i = 1; i <= rowCount; i++, rs.next()) {
          String str = /*"sender - "+*/rs.getString("varSenderGLN" )+" " + rs.getString("varRecipientGLN") + " " + rs.getString("varType") + " " +rs.getString("varIndex");
          log.info(str);
        }
    }else log.info("No new documents");

    stmt.close();
    rs.close();



}
    public static int getResultSetRowCount(ResultSet resultSet) {
        int size = 0;
        try {
            resultSet.last();
            size = resultSet.getRow();
            resultSet.beforeFirst();
        }
        catch(SQLException ex) {
            return 0;
        }
        return size;
    }
}
