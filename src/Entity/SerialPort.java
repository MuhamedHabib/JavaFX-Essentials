package Entity;

public class SerialPort {
    int id;
    String nom;
    String nomConnexion;
    int BitsParSec;
    int BitsDeDonnees;
    String Parite;
    int BitsArret;

    public SerialPort(int id, String nom, String nomConnexion, int bitsParSec, int bitsDeDonnees, String parite, int bitsArret, String controleFlux) {
        this.id = id;
        this.nom = nom;
        this.nomConnexion = nomConnexion;
        BitsParSec = bitsParSec;
        BitsDeDonnees = bitsDeDonnees;
        Parite = parite;
        BitsArret = bitsArret;
        this.controleFlux = controleFlux;
    }

    @Override
    public String toString() {
        return "SerialPort{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", nomConnexion='" + nomConnexion + '\'' +
                ", BitsParSec=" + BitsParSec +
                ", BitsDeDonnees=" + BitsDeDonnees +
                ", Parite='" + Parite + '\'' +
                ", BitsArret=" + BitsArret +
                ", controleFlux='" + controleFlux + '\'' +
                '}';
    }

    public SerialPort(String nom, String nomConnexion, int bitsParSec, int bitsDeDonnees, String parite, int bitsArret, String controleFlux) {
        this.nom = nom;
        this.nomConnexion = nomConnexion;
        BitsParSec = bitsParSec;
        BitsDeDonnees = bitsDeDonnees;
        Parite = parite;
        BitsArret = bitsArret;
        this.controleFlux = controleFlux;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomConnexion() {
        return nomConnexion;
    }

    public void setNomConnexion(String nomConnexion) {
        this.nomConnexion = nomConnexion;
    }

    public int getBitsParSec() {
        return BitsParSec;
    }

    public void setBitsParSec(int bitsParSec) {
        BitsParSec = bitsParSec;
    }

    public int getBitsDeDonnees() {
        return BitsDeDonnees;
    }

    public void setBitsDeDonnees(int bitsDeDonnees) {
        BitsDeDonnees = bitsDeDonnees;
    }

    public String getParite() {
        return Parite;
    }

    public void setParite(String parite) {
        Parite = parite;
    }

    public int getBitsArret() {
        return BitsArret;
    }

    public void setBitsArret(int bitsArret) {
        BitsArret = bitsArret;
    }

    public String getControleFlux() {
        return controleFlux;
    }

    public void setControleFlux(String controleFlux) {
        this.controleFlux = controleFlux;
    }

    public SerialPort() {
    }

    String controleFlux;
}
