package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Entity.*;
import Service.ServiceResponsable;
import Helpers.*;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

import javax.swing.*;



public class loginController implements Initializable {

    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public loginController() {
        con = database.getInstance().getConn();

    }


    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnSignin;

    @FXML
    private Label btnForgot;

    @FXML
    private Button btnFB;

    @FXML
    private Button btnSignup;

    @FXML
    private Label lblErrors;

    @FXML
    private Button connecter;



    @FXML
    public void handleButtonAction(MouseEvent event) throws SQLException {

        ServiceResponsable us = new ServiceResponsable();

        Responsable u = new Responsable(txtUsername.getText(), txtPassword.getText());
        UserSession usr= UserSession.getInstace(us.geIdbyUsername(u.getEmail()));
        System.out.println(usr);
        System.out.println(u);

        if (event.getSource() == btnSignin) {
            //login here
            if (logIn().equals("Success")) {

                try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Home.fxml")));
                    stage.setTitle("Gestion User");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            } else  {
                lblErrors.setText("Vous etes un Membre Veuillez Connecter avec l'autre button!");

            }
        }
    }


    @FXML
    public void handleButtonSignup(MouseEvent event) {
        if (event.getSource() == btnSignup){
            try {
                //add you loading or delays - ;-)
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../Gui/test/Signup.fxml")));
                stage.setTitle("Add User");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex){
                System.err.println(ex.getMessage());

            }
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (con == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");
        }
    }



    //we gonna use string to check for status
    private String logIn() {
        String status = "Success";
        String email = txtUsername.getText();
        String password = txtPassword.getText();
        if(email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM responsable Where Email = ? and passwd = ? and status='Admin'  ";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                } else {
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }

        return status;
    }

    private String logIn1() {
        String status = "Success";
        String email = txtUsername.getText();
        String password = txtPassword.getText();
        if(email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            //query
          //  String sql = "SELECT * FROM responsable Where email = ? and mdp = ? and status='membre'  and etat=''";
            //for simple users they want to be as an admin, after they subscribe then super-user should accept their demands just with an ok in state coloumn
            String sql = "SELECT * FROM responsable Where email = ? and passwd = ? and status='membre' ";
            //   String sql1 = "SELECT * FROM `personne` WHERE `email` LIKE '?' AND `mdp` LIKE '?' AND `status` LIKE 'Membre' AND `etat`='NULL'  ";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();

                //   preparedStatement = con.prepareStatement(sql1);
                //   ResultSet rs = preparedStatement.executeQuery();

                if (!resultSet.next() ) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                }else {
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                }

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }

        }

        return status;
    }
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }




    /*******************************/


    public void login(ActionEvent event) throws SQLException{

        con = database.getConn();
      //  String sql = "SELECT * FROM responsable Where email = ? and mdp = ? and status='membre'  and etat='false'";
        // when you add state column in the db would change this sql line with the below requet
        String sql = "SELECT * FROM responsable Where email = ? and passwd = ? and status='membre' ";
        List<Responsable> ls = new ArrayList<Responsable>();
        ServiceResponsable sp = new ServiceResponsable();
        ls = sp.read();
        try {
//            Encryption a1 = new Encryption("cinaTu2020Esprit");
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, txtUsername.getText());
            preparedStatement.setString(2, txtPassword.getText());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                for (Responsable e : ls) {
                    if (e.getEmail().equals(txtUsername.getText())) {

                        // u should remeber image configuration in the code below when u add image column just look for the original code from helpd
                        ProfilController controller = new ProfilController();
                        Responsable adhh = new Responsable(e.getId(), e.getNom(), e.getPrenom(), e.getNumeroGSM(), e.getUsername(), e.getEmail(), e.getPasswd(), e.getStatus());
                        controller.getAdh(adhh.getId(), adhh.getNom(), adhh.getPrenom(), adhh.getNumeroGSM(), adhh.getUsername(), adhh.getPasswd(), adhh.getEmail(), adhh.getStatus());


                        reclamationController reclamationController1 = new reclamationController();
                        Responsable adhh1 = new Responsable(e.getId(), e.getNom(), e.getPrenom(), e.getNumeroGSM(), e.getUsername(), e.getPasswd(), e.getEmail(), e.getStatus());
                        reclamationController1.getAdh1(adhh1.getId(), adhh1.getNom(), adhh1.getPrenom(), adhh1.getNumeroGSM(), adhh1.getUsername(), adhh1.getEmail(), adhh1.getPasswd(), adhh1.getStatus());

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/Gui/Profil.fxml"));
                        // Set it in the FXMLLoader


                           /* FXMLLoader fx = new FXMLLoader();
                            fx.setLocation(getClass().getResource("/accueil/FXML.fxml"));
                            AcceuilController Ac = new AcceuilController();
                            Ac.getEmail(e.getEmail());*/
                    }
                }

                JOptionPane.showMessageDialog(null, "email and password is correct");
                connecter.getScene().getWindow().hide();
                //Parent root = FXMLLoader.load(getClass().getResource("/Gui/interfaceFormation.fxml"));
                Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));

                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();

            } else {
                JOptionPane.showMessageDialog(null, "incorrecte email or password");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /*************** Correcte-forgot ****************/
    public void forgot(MouseEvent event) {
        ServiceResponsable service = new ServiceResponsable();

        String input = JOptionPane.showInputDialog("Enter Input:");
        Responsable p =service.findByEmail(input);

        try {
            String Object = "Réinitialisation de mot de passe ";
            String Corps = "Bonjour Monsieur/Madame \n On Recevoir qui vous avez des probléme a propos le déconnexion \n vous pouvez Connecter avec votre ancien mot de passe \n "+p.getPasswd()+".\n N'oublier pas de Modifier le durant votre premier connection  ";
            javaMail.sendMail(p.getEmail(), Object, Corps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /****************   Correcte-forgot  ***************/

}
