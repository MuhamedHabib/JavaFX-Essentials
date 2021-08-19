package Entity;

import java.sql.Date;

public class Reclamation {

    private String type;
    private  int id_reclamation;
    private Date date_creation;
    private Date date_validation;
    private int id_user;
    private String text;
    private String statut;
    private String Screenshot;
    private String object;


    public Reclamation(){};

    public Reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public Reclamation(int id_reclamation, String text) {
        this.id_reclamation = id_reclamation;
        this.text = text;
    }

    public Reclamation(int id_user, String text, String object) {
        this.id_user = id_user;
        this.text = text;
        this.object = object;
    }

    public Reclamation(String type, int id_reclamation, Date date_creation, Date date_validation, int id_user, String text, String statut) {
        this.type = type;
        this.id_reclamation = id_reclamation;
        this.date_creation = date_creation;
        this.date_validation = date_validation;
        this.id_user = id_user;
        this.text = text;
        this.statut = statut;
    }


    public Reclamation(String type, Date date_creation, Date date_validation, int id_user, String text, String statut, String screenshot, String object) {
        this.type = type;
        this.date_creation = date_creation;
        this.date_validation = date_validation;
        this.id_user = id_user;
        this.text = text;
        this.statut = statut;
        Screenshot = screenshot;
        this.object = object;
    }

    public Reclamation(String text) { this.text=text;  }

    public Reclamation(String type, String text, String object) {
        this.type = type;
        this.text = text;
        this.object = object;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getText() { return text; }

    public void setText(String text) {
        this.text = text;
    }

    public  int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Date getDate_validation() { return date_validation; }

    public void setDate_validation(Date date_validation) { this.date_validation = date_validation; }

    public int getId_user(int id_user) {
        return this.id_user;
    }
    public int getId_user() {
        return id_user;
    }


    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut=statut;
    }

    public String getScreenshot() {
        return Screenshot;
    }

    public void setScreenshot(String screenshot) {
        Screenshot = screenshot;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "type='" + type + '\'' +
                ", date_creation=" + date_creation +
                ", date_validation=" + date_validation +
                ", id_user=" + id_user +
                ", text='" + text + '\'' +
                ", statut='" + statut + '\'' +
                ", Screenshot='" + Screenshot + '\'' +
                ", object='" + object + '\'' +
                '}';
    }
}

