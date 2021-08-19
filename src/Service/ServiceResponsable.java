package Service;

import Entity.Responsable;
import Helpers.database;
import intService.IServicess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceResponsable implements IServicess<Responsable> {

    Connection cnx ;
    private ResultSet rs;
    private Statement st;
    PreparedStatement pstmt = null;
    private int id_user;




    public ServiceResponsable() {

           cnx = database.getInstance().getConn();
       }
    @Override
    public void add(Responsable p) throws SQLException{
        Statement st = cnx.createStatement();
        String req =" insert into Responsable ( Nom , Prenom , NumeroGSM , username , passwd , Email) values('"+p.getNom()+" ' , '"+p.getPrenom() +"' , '" +p.getNumeroGSM()+ "' , '"+p.getUsername()+ "' , '"+p.getPasswd()+ "' , '"+p.getEmail()+"' )";
        System.out.println(req);
        st.executeUpdate(req);

    }


    @Override
       public List<Responsable> read() throws SQLException{
          List<Responsable> ls;
          ls = new ArrayList<Responsable>();
          Statement st = cnx.createStatement();
         // String req = "select * from responsable Where status='Membre'";
         String req = "select * from responsable Where NOM='a'";
          ResultSet rs = st.executeQuery(req);

           while(rs.next()){

               Responsable p =new Responsable ();
               p.setId(rs.getInt(1));
               p.setNom(rs.getString(2));
               p.setPrenom(rs.getString(3));
               p.setNumeroGSM(rs.getString(4));
               p.setUsername(rs.getString(5));
               p.setPasswd(rs.getString(6));
               p.setEmail(rs.getString(7));
             //  p.setStatus(rs.getString(8)); membre or admin
             //  p.setImage(rs.getString(9)); a discuter avec l encadrent
             //  p.setEtat(rs.getString(10)); a discuter
               ls.add(p);
           }
           return ls;
       }





    @Override
       public void update(Responsable p) throws SQLException {
           PreparedStatement pt = cnx.prepareStatement("update responsable set Nom = ?,Prenom = ?,NumeroGSM = ?,username = ?,passwd = ?,Email = ? where id = ? ");
           pt.setInt(1,p.getId());
           pt.setString(2, p.getNom());
           pt.setString(3, p.getPrenom());
           pt.setString(4, p.getNumeroGSM());
           pt.setString(5, p.getUsername());
           pt.setString(6, p.getPasswd());
           pt.setString(7, p.getEmail());


           pt.executeUpdate();
       }

       @Override
       public void delete(Responsable p) throws SQLException {
           PreparedStatement pt = cnx.prepareStatement("delete from responsable where id = ?");
           pt.setInt(1, p.getId());
           pt.executeUpdate();
       }
    public void Supp(Responsable user) {
        String requete = "delete from responsable where id = '"+user.getId()+"'";
        try {
            st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Compte Supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }







    @Override
    public Responsable findById(int id_user) {
        String query = "select * from responsable where id= ? ";
        Responsable student = null;
        try {
            pstmt = cnx.prepareStatement(query);
            pstmt.setInt(1, id_user);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                student = new Responsable(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                        );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }


    public Responsable findByEmail(String Email) {
        String query = "select * from responsable where Email= ? ";
        Responsable student = null;
        try {
            pstmt = cnx.prepareStatement(query);
            pstmt.setString(1, Email);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                student = new Responsable(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }

    public int geIdbyUsername(String email) throws SQLException {

        /* CurrentUser cu = CurrentUser.CurrentUser(); */
        int id = 0;
        try {
            String request = "SELECT id_user FROM responable where email='" + email + "'";
            Statement s = cnx.createStatement();
            ResultSet result = s.executeQuery(request);
            while (result.next()) {
                id = result.getInt("id");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return id;
    }



    public void updatePassword(Responsable user) {
        String requete = "UPDATE responsable SET mdp='"+user.getPasswd()+"' WHERE id='"+user.getId()+"'";
        try {
            st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("mot de passe modifié");
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void updateNom(Responsable user) {
        String requete = "UPDATE responsable SET Nom='" + user.getNom() + "',Prenom='"+user.getPrenom()+"' WHERE id='"+user.getId()+"'";
        try {
            st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Nom modifié");
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   }

