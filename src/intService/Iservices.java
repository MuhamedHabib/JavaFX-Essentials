package intService;

import Entity.Reclamation;

import java.sql.SQLException;
import java.util.List;

public interface Iservices {

     void AddRec(Reclamation r);
     List<Reclamation> AfficherRec() throws SQLException;
     List<Reclamation> AfficherRecUser() throws SQLException;
     List<Reclamation> AfficherArchive() throws SQLException;
     List<Reclamation> AfficherNotif() throws SQLException;
     void UpdateRec(Reclamation r) throws SQLException;
     void DeleteRec(Reclamation r);
     Reclamation findbyId(int id_reclamation);
}
