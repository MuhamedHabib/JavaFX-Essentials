/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author intel
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private VBox vbox;

    @FXML
    private JFXButton close_signin;

    @FXML
    private JFXButton close_signup;

    @FXML
    private Label lbl_time;
    
    @FXML
    private Label lbl_time2;

    @FXML
    private Pane pane;

    private Parent fxml;
    double x, y = 0;
    
    @FXML
    private void openSignUp(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(2), vbox);
        t.setToX(10);
        t.play();
        close_signup.setVisible(false);
        close_signin.setVisible(true);
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("../Gui/test/Signup.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);

            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) vbox.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void openSIgnIn(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(2), vbox);
        t.setToX(vbox.getLayoutX() * 32);
        t.play();
        close_signup.setVisible(true);
        close_signin.setVisible(false);
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("Signin.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void updateTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Calendar time = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                lbl_time.setText(sdf.format(time.getTime()));
                lbl_time2.setText(sdf.format(time.getTime()));
            }
        }),new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTime();
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 32);
        t.play();
        close_signin.setVisible(false);
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("Signin.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);

            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        makeDraggable();
    }   
    
    public void makeDraggable() {
        pane.setOnMousePressed((event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        pane.setOnMouseDragged((event) -> {
            Stage stage = (Stage) vbox.getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8f);
        });
        pane.setOnDragDone((event) -> {
            Stage stage = (Stage) vbox.getScene().getWindow();
            stage.setOpacity(1f);
        });
        pane.setOnMouseReleased((event) -> {
            Stage stage = (Stage) vbox.getScene().getWindow();
            stage.setOpacity(1f);
        });
    }
    
}
