package Entity;

public class Responsable {
    Integer id;
    String Nom;
    String Prenom;
    String NumeroGSM;
    String username;
    String passwd;
    String Email;
    String status;

    public Responsable() {

    }

    public Responsable(String nom, String prenom, String numeroGSM, String username, String passwd, String email,String status) {
        this.Nom = nom;
        this.Prenom = prenom;
        this.NumeroGSM = numeroGSM;
        this.username = username;
        this.passwd = passwd;
        this.Email = email;
        this.status=status;
    }

    public Responsable(String email, String passwd) {
        this.Email=email;
        this.passwd=passwd;
    }

    @Override
    public String toString() {
        return "Responsable{" +
                "id=" + id +
                ", Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", NumeroGSM='" + NumeroGSM + '\'' +
                ", username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", Email='" + Email + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Responsable(Integer id, String nom, String prenom, String numeroGSM, String username, String passwd, String email,String status) {
        this.id = id;
        this.Nom = nom;
        this.Prenom = prenom;
        this.NumeroGSM = numeroGSM;
        this.username = username;
        this.passwd = passwd;
        this.Email = email;
        this.status=status;

    }
    public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getNumeroGSM() {
        return NumeroGSM;
    }

    public void setNumeroGSM(String numeroGSM) {
        NumeroGSM = numeroGSM;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}



