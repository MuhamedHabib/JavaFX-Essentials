package intService;

import Entity.SerialPort;

import java.sql.SQLException;
import java.util.List;

public interface IserviceSerialPort {

    void AddInterface(SerialPort r);
    List<SerialPort> AfficherPort() throws SQLException;
   // List<SerialPort> AfficherBlockPort() throws SQLException;
   // List<SerialPort> AfficherArchive() throws SQLException;
   // List<SerialPort> AfficherNotif() throws SQLException;
    void UpdatePort(SerialPort r) throws SQLException;
    void DeletePort(SerialPort r);
    SerialPort findbyId(int id_reclamation);

}
