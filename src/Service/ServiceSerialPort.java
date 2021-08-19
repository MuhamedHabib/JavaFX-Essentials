package Service;

import Entity.Reclamation;
import Entity.Responsable;
import Entity.SerialPort;
import intService.IserviceSerialPort;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceSerialPort implements IserviceSerialPort {
    Connection cnx;

    @Override
    public void AddInterface(SerialPort r) {
        try {
            SerialPort p =  new SerialPort();
            String statut = "en attente";
            Statement st = cnx.createStatement();
            String query = "insert into SerialPort (id ,nom , nomConnexion, BitsParSec, BitsDeDonnees, Parite, BitsArret) values ('"+r.getId()+"' ,'" +r.getNom()+"' , '"+r.getNomConnexion()+"', '"+r.getBitsParSec()+"' , '"+r.getBitsDeDonnees()+"', '"+r.getParite()+"', '"+r.getBitsArret()+"' )";
            System.out.println(query);
            st.executeUpdate(query);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<SerialPort> AfficherPort() throws SQLException {
        Statement st = cnx.createStatement();


        List<SerialPort> SerialPort = new ArrayList<>();
        String query = "select * from SerialPort ";
        ResultSet rst = st.executeQuery(query);
        while (rst.next()) {
            SerialPort rec = new SerialPort();
            rec.setId(rst.getInt(1));
            rec.setNom(rst.getString(2));
            rec.setNomConnexion(rst.getString(3));
            rec.setBitsParSec(rst.getInt(4));
            rec.setBitsDeDonnees(rst.getInt(5));
            rec.setParite(rst.getString(6));
            rec.setBitsArret(rst.getInt(7));
            rec.setControleFlux( rst.getString(8));
          //  rec.setObject(rst.getString(9));


            SerialPort.add(rec);
        }
        return SerialPort;
    }

    @Override
    public void UpdatePort(SerialPort r) throws SQLException {

    }

    @Override
    public void DeletePort(SerialPort r) {
        try {
            PreparedStatement pt = cnx.prepareStatement("delete from SerialPort where id =?");
            pt.setInt(1, r.getId());
            System.out.println(r.getId());

            pt.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public SerialPort findbyId(int id_port) {
        SerialPort rec = new SerialPort();
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from SerialPort where id =? ");
            pt.setInt(1,id_port);
            ResultSet rst = pt.executeQuery();

            while (rst.next()) {

                rec.setId(rst.getInt(1));
                rec.setNom(rst.getString(2));
                rec.setNomConnexion(rst.getString(3));
                rec.setBitsParSec(rst.getInt(4));
                rec.setBitsDeDonnees(rst.getInt(5));
                rec.setParite(rst.getString(6));
                rec.setBitsArret(rst.getInt(7));
                rec.setControleFlux( rst.getString(8));
             /*   rec.setId_reclamation(rst.getInt(1));
                rec.setDate_creation(rst.getDate(3));
                rec.setId_user(rst.getInt(5));
                rec.setText(rst.getString(6));
                rec.setStatut(rst.getString(7));
                rec.setScreenshot( rst.getString(8));
                rec.setObject(rst.getString(9)); */
            }
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        return rec;
    }
}
