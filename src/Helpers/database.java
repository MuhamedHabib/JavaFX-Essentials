package Helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class database {
    private static String url ="jdbc:mysql://localhost:3306/freenac";
    private static String user ="root";
    private static String pwd ="";

    private static Connection conn;

    static database instance;

    private database() {

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println(" connecté !!!!");

        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static database getInstance(){
        if(instance == null)
            instance = new database();

        return instance;
    }

    public static Connection getConn() {
        return conn;
    }




}



