package Service;

import Entity.Reclamation;
import Entity.Responsable;
import Helpers.Maconnexion;
import intService.Iservices;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ServiceReclamation implements Iservices {

    private int id=100;

    Connection cnx;

    public TableView<Reclamation> Reclamations;


    public ServiceReclamation() {
        cnx = Maconnexion.getInstance().getConnection();
    }

    @Override
    public void AddRec(Reclamation r) {
        try {
            Responsable p =  new Responsable();
            String statut = "en attente";
            Statement st = cnx.createStatement();
            String query = "insert into reclamation (id_user,text , statut, type, screenshot, object) values ('"+r.getId_user()+"' ,'" +r.getText()+"' , '"+statut+"', '"+r.getType()+"' , '"+r.getScreenshot()+"', '"+r.getObject()+"' )";
            System.out.println(query);
            st.executeUpdate(query);
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Reclamation> AfficherRec() throws SQLException {

        Statement st = cnx.createStatement();


        List<Reclamation> Reclamation = new ArrayList<>();
        String query = "select * from reclamation ";
        ResultSet rst = st.executeQuery(query);
        while (rst.next()) {
            Reclamation rec = new Reclamation();
            rec.setId_reclamation(rst.getInt(1));
            rec.setType(rst.getString(2));
            rec.setDate_creation(rst.getDate(3));
            rec.setDate_validation(rst.getDate(4));
            rec.setId_user(rst.getInt(5));
            rec.setText(rst.getString(6));
            rec.setStatut(rst.getString(7));
            rec.setScreenshot( rst.getString(8));
            rec.setObject(rst.getString(9));


            Reclamation.add(rec);
        }
        return Reclamation;

    }

    @Override
    public List<Reclamation> AfficherRecUser() throws SQLException{

        Statement st = cnx.createStatement();


        List<Reclamation> Reclamation = new ArrayList<>();
        String query = "select * from reclamation ";
        ResultSet rst = st.executeQuery(query);
        while (rst.next()) {
            Reclamation rec = new Reclamation();
            rec.setId_reclamation(rst.getInt(1));
            rec.setType(rst.getString(2));
            rec.setDate_creation(rst.getDate(3));
            rec.setText(rst.getString(6));
            rec.setStatut(rst.getString(7));
            rec.setScreenshot( rst.getString(8));
            rec.setObject(rst.getString(9));


            Reclamation.add(rec);
        }
        return Reclamation;
    }

    @Override
    public List<Reclamation> AfficherArchive() throws SQLException {
        Statement st = cnx.createStatement();


        List<Reclamation> Reclamation = new ArrayList<>();
        String query = "select * from reclamation where statut='validée'";
        ResultSet rst = st.executeQuery(query);
        while (rst.next()) {
            Reclamation rec = new Reclamation();
            rec.setId_reclamation(rst.getInt(1));
            rec.setType(rst.getString(2));
            rec.setDate_creation(rst.getDate(3));
            rec.setDate_validation(rst.getDate(4));
            rec.setId_user(rst.getInt(5));
            rec.setText(rst.getString(6));
            rec.setScreenshot( rst.getString(8));
            rec.setObject(rst.getString(9));


            Reclamation.add(rec);
        }
        return Reclamation;
    }

    @Override
    public List<Reclamation> AfficherNotif() throws SQLException {
        Statement st = cnx.createStatement();
        List<Reclamation> Reclamation = new ArrayList<>();
        String query = "select * from reclamation where statut='validée'";
        ResultSet rst = st.executeQuery(query);
        while (rst.next()) {
            Reclamation rec = new Reclamation();
            rec.setDate_validation(rst.getDate(4));
            Reclamation.add(rec);
        }
        return Reclamation;
    }
  /*  public List<Reclamation> rechercheReclamations(String type, String valeur) {
        List<Reclamation> myList = new ArrayList<Reclamation>();
        String requete = null;
        try {
          if (type.equals("statut")) {
            requete = "SELECT * from Reclamation where statut ='" + valeur + "'";
            ; //MAJUSCULE NON OBLIGATOIRE
        }

            else if (type.equals("Tout")) {
            requete = "SELECT * from Reclamation where statut like '%" + valeur + "%' or object like '%" + valeur + "%' or text like '%" + valeur + "%  or type like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE
        }

            Statement st = Maconnexion.getInstance().getConnection().prepareStatement(requete); // import java.sql.Statement

           // Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(requete);
        while (rst.next()) {
            Reclamation rec = new Reclamation();
            rec.setId_reclamation(rst.getInt(1));
            rec.setType(rst.getString(2));
            rec.setDate_creation(rst.getDate(3));
            rec.setDate_validation(rst.getDate(4));
            rec.setId_user(rst.getInt(5));
            rec.setText(rst.getString(6));
            rec.setStatut(rst.getString(7));
            rec.setScreenshot("file:C:\\Users\\Mariem.DESKTOP-L3DPUNQ\\IdeaProjects\\untitled\\src\\images\\" + rst.getString(8));
            rec.setObject(rst.getString(9));


            myList.add(rec);
        }
        }catch (SQLException e) {
        e.printStackTrace();
    }
        return myList;

    }
*/

    public ResultSet AfficherReclamationsFiltre(String keyword) {
        try {
            String query = "SELECT * FROM reclamation WHERE type LIKE '%" + keyword + "%'";
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    @Override
    public Reclamation findbyId(int id_reclamation) {
       Reclamation rec = new Reclamation();
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from reclamation where id_reclamation =? ");
            pt.setInt(1,id_reclamation);
            ResultSet rst = pt.executeQuery();

            while (rst.next()) {

                rec.setId_reclamation(rst.getInt(1));
                rec.setDate_creation(rst.getDate(3));
                rec.setId_user(rst.getInt(5));
                rec.setText(rst.getString(6));
                rec.setStatut(rst.getString(7));
                rec.setScreenshot( rst.getString(8));
                rec.setObject(rst.getString(9));
            }
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        return rec;
    }


    public void UpdateRec(Reclamation r){

        PreparedStatement pt = null;
        try {

            pt = cnx.prepareStatement("update reclamation SET text =? , object=?, screenshot=?, type=?  where id_reclamation =? ");
            pt.setString(1, r.getText());
            pt.setString(2, r.getObject());
            pt.setString(3, r.getScreenshot());
            pt.setString(4, r.getType());
            pt.setInt(5, r.getId_reclamation());

            pt.executeUpdate();} catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void UpdateRecStatut(Reclamation r){

        PreparedStatement pt = null;
        try {

            pt = cnx.prepareStatement("update reclamation SET statut =?, date_validation=? where id_reclamation =? ");
            pt.setString(1, r.getStatut());
            pt.setDate(2, r.getDate_validation());
            pt.setInt(3, r.getId_reclamation());

            pt.executeUpdate();} catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void DeleteRec(Reclamation r) {

        try {
            PreparedStatement pt = cnx.prepareStatement("delete from reclamation where id_reclamation =?");
            pt.setInt(1, r.getId_reclamation());
            System.out.println(r.getId_reclamation());

            pt.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }



}

