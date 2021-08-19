/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author KADER
 */
public class Maconnexion {

    public String URL="jdbc:mysql://127.0.0.1:3306/helpdesk";
    public String LOGIN="root";
    public String PWD= "";
    public Connection cnx;
    public static Maconnexion cn;
    


    private Maconnexion(){
        try {
            cnx=DriverManager.getConnection(URL,LOGIN,PWD);
            System.out.println("connexion etablie");
            
        } catch (SQLException ex) {
          System.out.println("pas de connexion");
          System.out.println(ex.getMessage());
        }
} 
    
     public Connection getConnection(){
         return cnx;
     }
     public static Maconnexion getInstance(){
         if(cn==null)
             cn=new Maconnexion();
         return cn;
     }
}