/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import Entity.Responsable;
import Service.ServiceResponsable;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author intel
 */
public class SignUpController implements Initializable {

    public TextField txt_prenom;
    public TextField txt_nom;
    public TextField txt_numgsm;
    public TextField txt_status;
    public Label lblStatus;
    @FXML
    private JFXButton btn_signup;

    @FXML
    private TextField txt_username;
    
    @FXML
    private TextField txt_email;

    @FXML
    private PasswordField txt_password;
    
    Connection con;
    Statement st;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txt_username.setOnKeyPressed((KeyEvent event) -> {

            if (null != event.getCode()) switch (event.getCode()) {
                case ENTER:
                    btn_signup.fire();
                    break;
                case UP:
                    txt_password.requestFocus();
                    break;
                case DOWN:
                    txt_email.requestFocus();
                    break;
                default:
                    break;
            }
        });
        
        txt_email.setOnKeyPressed((KeyEvent event) -> {
            if (null != event.getCode()) switch (event.getCode()) {
                case ENTER:
                    btn_signup.fire();
                    break;
                case UP:
                    txt_username.requestFocus();
                    break;
                case DOWN:
                    txt_password.requestFocus();
                    break;
                default:
                    break;
            }
        });

        txt_password.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                btn_signup.fire();
            } else if (event.getCode() == KeyCode.UP) {
                txt_email.requestFocus();
            }
        });
    }

    @FXML
   /* public void signUp(ActionEvent event) {
        String username = txt_username.getText().trim();
        String password = txt_password.getText();
        String email = txt_email.getText().trim();
        Pattern pattern = Pattern.compile("([a-z0-9_\\-\\.])+\\@([a-z0-9_\\-\\.])+\\.([a-z]{2,4})$");
        if (username.isEmpty() && password.isEmpty() && email.isEmpty()) {
            Notifications notification = pushNotify("Empty Fields", "Please fill in the fields");
            notification.showError();
            txt_username.requestFocus();
//            txt_password.setStyle("-fx-border-color: red;");
//            txt_username.setStyle("-fx-border-color: red;");
        } else if (password.isEmpty()) {
            Notifications notification = pushNotify("Empty Password", "Please fill in the password");
            notification.showError();
            txt_password.requestFocus();
        } else if (username.isEmpty()) {
            Notifications notification = pushNotify("Empty Username", "Please fill in your username");
            notification.showError();
            txt_username.requestFocus();
        } else if (email.isEmpty()) {
            Notifications notification = pushNotify("Empty EMail", "Please fill in your email address");
            notification.showError();
            txt_email.requestFocus();
        } else if(!pattern.matcher(email).matches()) {
            Notifications notification = pushNotify("Invalid Email", "Please Provide a valid email address");
            notification.showError();
            txt_email.requestFocus();
        }else{
            Image img = new Image(getClass().getResourceAsStream("images/ok.png"));
             Notifications notification = pushNotify("Everything Was Okay", "Good Job :)");
            notification.graphic(new ImageView(img));
            notification.show();
            clear();
        }

    }*/

    public void clear() {
        txt_username.setText("");
        txt_password.setText("");
    }

    public Notifications pushNotify(String title, String text) {
        Notifications notification = Notifications.create()
                .title(title)
                .text(text)
                .hideAfter(Duration.seconds(7))
                .position(Pos.TOP_RIGHT)
                .onAction((ActionEvent e) -> {
                    System.out.println("clicked on notification");
                });
        return notification;
    }

    public void signUp(ActionEvent actionEvent) {
        //check if not empty
        String etat = "false";
        if (txt_nom.getText().isEmpty() ||
                txt_prenom.getText().isEmpty() ||
                txt_numgsm.getText().isEmpty() ||
                txt_username.getText().isEmpty() ||
                txt_password.getText().isEmpty() ||
                txt_status.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Enter all details");
        } else {
            ServiceResponsable sp= new ServiceResponsable();
            try{
                Responsable p1= new Responsable();
                p1.setNom(txt_nom.getText());
                p1.setPrenom(txt_prenom.getText());
                p1.setNumeroGSM(txt_numgsm.getText());
                p1.setUsername(txt_username.getText());
              
                p1.setPasswd(txt_password.getText());
                p1.setEmail(txt_email.getText());
                p1.setStatus(txt_status.getText());
            //    p1.setDate_naissance(txtDOB.getValue().toString());
               
              //  p1.setStatus(txt_status.getValue().toString());
           //     p1.setImage(imagePath);
           //     p1.setEtat(etat);
                sp.add(p1);
                lblStatus.setTextFill(Color.GREEN);
                lblStatus.setText("Added Successfully");

                Notifications notification = pushNotify("Everything Was Okay", "Good Job :)");

                notification.show();
                clearFields();

            }catch (Exception e){
                System.out.println(e.getMessage());
                lblStatus.setTextFill(Color.TOMATO);
                lblStatus.setText(e.getMessage());
            }
        }
    }

    private void clearFields() {
        txt_nom.clear();
        txt_prenom.clear();
        txt_numgsm.clear();
        txt_username.clear();
        txt_password.clear();
        txt_email.clear();
        txt_status.clear();
    }
}
