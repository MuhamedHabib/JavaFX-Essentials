package sample;

import Entity.Responsable;
import Service.ServiceResponsable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Gui/Profil.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 720 , 740));
        primaryStage.show();


     /******************       TEST-CRUD         ***************************/
        Responsable p1=new Responsable("test","test","test","test","test","test","membre");
        Responsable p2=new Responsable("test","test","test","test","test","test","membre");
        Responsable p3=new Responsable("test","test","test","test","test","test","admin");

        ServiceResponsable s = new ServiceResponsable();
        List<Responsable> a = s.read();
        System.out.println(s.read());
        System.out.println(a);

        //s.add(p2);

        try {
            //    s.Supp(p2);
         a= (List<Responsable>) s.findById(1);
         System.out.println(s.findById(1));
        }catch (Exception ex){System.out.println(ex);}
        System.out.println(s.findById(1));

        /******************       TEST-CRUD         ***************************/
    }


    public static void main(String[] args) {
        launch(args);
    }
}
