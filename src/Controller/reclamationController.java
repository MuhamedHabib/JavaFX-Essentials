package Controller;

import Entity.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.Notifications;
import Service.ServiceReclamation;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class reclamationController implements Initializable {
    Notifications n;
    @FXML
    private TextField NomTXFLD;
    @FXML
    private TextField PrenomTXFLD;
    @FXML
    private TextField EmailTXFLD;
    @FXML
    private TextField TelTXFLD;

    @FXML
    private Button Buttonid;
    @FXML
    private Button Modifier;
    @FXML
    private TextArea tfréclamation;
    @FXML
    public TableView<Reclamation> Reclamations;
    @FXML
    private TableView<Reclamation> notif;
    @FXML
    private Label label;
    @FXML
    private Label alerte;
    @FXML
    private TextField recherche;
    @FXML
    private ComboBox type;
    @FXML
    private ImageView screenshotView;
    private String path2;
    @FXML
    private Button image;
    private Connection connection;
    private String statut;
    @FXML
    private TextField tfréclamation2;
    @FXML
    private TextField object;
    File selectedFile;
    static Reclamation selectionedReclamation;
    static Stage stageAffichageUnique;

    private String filename;

    final ObservableList<Reclamation> dataList = FXCollections.observableArrayList();

    @FXML
    private ImageView recaptchaCheckMark;
    int etatrecaptcha = 0;
    Stage window;
    WebView webView2;
    WebEngine webEngine;

    private static int id_user;
    private static String nom;
    private static String prenom;
    private static String email;
    private static String mdp;
    private static String date_naissance;
    private static String telephone;
    private static String image2;
    private static String status;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        stageAffichageUnique = new Stage();

        ServiceReclamation sr = new ServiceReclamation();

        type.getItems().add("Service technique");
        type.getItems().add("Contenu");
        type.setOnAction(event -> {
            System.out.println("Selected:" + type.getValue().toString());
            System.out.println("All:" + type.getItems().toString());
        });



        NomTXFLD.setText(nom);
        PrenomTXFLD.setText(prenom);
        EmailTXFLD.setText(email);
        TelTXFLD.setText(telephone);

        NomTXFLD.setDisable(true);
        PrenomTXFLD.setDisable(true);
        EmailTXFLD.setDisable(true);
        TelTXFLD.setDisable(true);

        TableColumn<Reclamation, String> idRec = new TableColumn<>("Id reclamation");
        idRec.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));

        TableColumn<Reclamation, String> idDate = new TableColumn<>("Date création");
        idDate.setCellValueFactory(new PropertyValueFactory<>("date_creation"));

        TableColumn<Reclamation, String> type = new TableColumn<>("Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Reclamation, String> text = new TableColumn<>("Reclamation");
        text.setCellValueFactory(new PropertyValueFactory<>("text"));

        TableColumn<Reclamation, String> sujet = new TableColumn<>("Sujet");
        sujet.setCellValueFactory(new PropertyValueFactory<>("object"));

        TableColumn<Reclamation, String> statut = new TableColumn<>("statut");
        statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        statut.setPrefWidth(150);

        TableColumn<Reclamation, String> screenshotColumn = new TableColumn<>("Image");
//        screenshotColumn.setMinWidth(100);
        screenshotColumn.setCellValueFactory(new PropertyValueFactory<>("screenshot"));
        screenshotColumn.setStyle("-fx-alignment: CENTER;");

        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFactoryImage
                = //
                new Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>>() {


                    @Override
                    public TableCell call(final TableColumn<Reclamation, String> param) {
                        final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {

                            ImageView image = new ImageView();

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {


                                    Reclamation Reclamation = getTableView().getItems().get(getIndex());
                                    String dir = System.getProperty("user.dir");//get project source path
                                    File dest = new File(dir + "\\images\\" + Reclamation.getScreenshot());//add the full path /ressources + file name
                                    System.out.println(dest.getAbsolutePath());
                                    try {
                                        image.setImage(new Image(dest.toURI().toURL().toString()));

                                        image.setFitWidth(40);
                                        image.setFitHeight(40);
                                        setGraphic(image);
                                        setText(null);
                                    } catch (MalformedURLException e) {
                                        Logger.getLogger(reclamationController.class.getName()).log(Level.SEVERE, null, e);
                                    }
                                }
                            }

                        };

                        cell.setOnMouseClicked((MouseEvent event2)
                                -> {
                            if (event2.getClickCount() == 1) {
                                if (Reclamations.getSelectionModel().getSelectedItem() != null && !Reclamations.getSelectionModel().getSelectedItem().getScreenshot().contains("null")) {
                                    Stage window = new Stage();

                                    window.setMinWidth(250);
                                    ImageView imagevPOPUP = new ImageView(new Image(Reclamations.getSelectionModel().getSelectedItem().getScreenshot()));
                                    imagevPOPUP.setFitHeight(576);
                                    imagevPOPUP.setFitWidth(1024);
                                    VBox layout = new VBox(10);
                                    layout.getChildren().addAll(imagevPOPUP);
                                    layout.setAlignment(Pos.CENTER);
                                    //Display window and wait for it to be closed before returning
                                    Scene scene = new Scene(layout);
                                    window.setScene(scene);
                                    window.show();

                                }
                            }

                        });

                        return cell;
                    }
                };

        screenshotColumn.setCellFactory(cellFactoryImage);

        TableColumn<Reclamation, Double> progressCol = new TableColumn("Avancement");
        progressCol.setCellValueFactory(new PropertyValueFactory<Reclamation, Double>(
                ""));
        progressCol.setCellFactory(ProgressBarTableCell.<Reclamation>forTableColumn());
        Callback<TableColumn<Reclamation, Double>, TableCell<Reclamation, Double>> cellFactoryProgress =
                new Callback<TableColumn<Reclamation, Double>, TableCell<Reclamation, Double>>() {
                    @Override
                    public TableCell<Reclamation, Double> call(TableColumn<Reclamation, Double> reclamationStringTableColumn) {
                        final TableCell<Reclamation, Double> cell = new TableCell<Reclamation, Double>() {
                            ProgressBar progress = new ProgressBar(0);

                            @Override
                            public void updateItem(Double item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    Reclamation Reclamation = getTableView().getItems().get(getIndex());
                                    String text = Reclamation.getStatut();
                                    if (text.equals("en cours de traitement")) {
                                        progress.setProgress(0.5);
                                    } else if (text.equals("validée")) {
                                        progress.setProgress(1);
                                    } else {
                                        progress.setProgress(0);
                                    }
                                    setGraphic(progress);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        progressCol.setCellFactory(cellFactoryProgress);


        TableColumn modCol = new TableColumn("Modifier");
        modCol.setCellValueFactory(new PropertyValueFactory<>(""));

        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFactoryModifier
                = //
                new Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Reclamation, String> param) {
                        final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
                            final Button modifier = new Button("Modifier");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    modifier.setDisable(false);
                                    Reclamation Reclamation = getTableView().getItems().get(getIndex());
                                    LocalDate d1 = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ISO_LOCAL_DATE);
                                    LocalDate d2 = LocalDate.parse(Reclamation.getDate_creation().toString(), DateTimeFormatter.ISO_LOCAL_DATE);
                                    Duration diff = Duration.between(d2.atStartOfDay(), d1.atStartOfDay());
                                    long diffDays = diff.toDays();

                                    if (diffDays > 0.1) {
                                        modifier.setDisable(true);
                                        if (!Reclamation.getStatut().equals("validée")) {
                                            Reclamation.setStatut("en cours de traitement");
                                            sr.UpdateRecStatut(Reclamation);
                                        }
                                    } else {
                                        if (Reclamation.getStatut().equals("validée")) {
                                            modifier.setDisable(true);
                                        }

                                        modifier.setOnAction(event -> {
                                            if (Reclamations.getSelectionModel().getSelectedItem().getStatut().equals("en attente")) {
                                                selectionedReclamation = Reclamations.getSelectionModel().getSelectedItem();
                                                Parent root;
                                                try {
                                                    root = FXMLLoader.load(getClass().getResource("../GUI/ModifierRec.fxml"));
                                                    Scene scene = new Scene(root);
                                                    stageAffichageUnique.setScene(scene);
                                                    stageAffichageUnique.show();

                                                } catch (IOException ex) {
                                                    Logger.getLogger(reclamationController.class.getName()).log(Level.SEVERE, null, ex);
                                                }

                                            }
                                        });

                                        setGraphic(modifier);
                                        setText(null);


                                    }
                                }
                            }
                        };
                        return cell;
                    }
                };

        Reclamations.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 2) {
                if (Reclamations.getSelectionModel().getSelectedItem().getStatut().equals("en attente")) {
                    selectionedReclamation = Reclamations.getSelectionModel().getSelectedItem();

                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("../GUI/ModifierRec.fxml"));
                        Scene scene = new Scene(root);
                        stageAffichageUnique.setScene(scene);
                        stageAffichageUnique.show();

                    } catch (IOException ex) {
                        Logger.getLogger(reclamationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
        modCol.setCellFactory(cellFactoryModifier);


        TableColumn delCol = new TableColumn("Suppression");
        delCol.setCellValueFactory(new PropertyValueFactory<>(""));
        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFactoryDelete
                = //
                new Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Reclamation, String> param) {
                        final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
                            final Button delete = new Button("delete");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    Reclamation Reclamation = getTableView().getItems().get(getIndex());
                                    LocalDate d1 = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ISO_LOCAL_DATE);
                                    LocalDate d2 = LocalDate.parse(Reclamation.getDate_creation().toString(), DateTimeFormatter.ISO_LOCAL_DATE);
                                    Duration diff = Duration.between(d2.atStartOfDay(), d1.atStartOfDay());
                                    long diffDays = diff.toDays();

                                    if (diffDays > 0.1) {
                                        delete.setDisable(true);
                                        if (!Reclamation.getStatut().equals("validée")) {
                                            Reclamation.setStatut("en cours de traitement");
                                            sr.UpdateRecStatut(Reclamation);
                                            delete.setOnAction(event -> {
                                                Notifications n = Notifications.create()
                                                        .title("Erreur")
                                                        .text("Suppression impossible: Reclamation a depassée 24h depuis sa creation")
                                                        .graphic(null)
                                                        .position(Pos.TOP_CENTER);
                                                //.hideAfter(Duration.seconds(5));
                                                n.showWarning();
                                            });
                                        }

                                    } else {
                                        delete.setDisable(false);
                                        if (Reclamation.getStatut().equals("validée")) {
                                            delete.setDisable(true);
                                        } else
                                            delete.setOnAction(event -> {
                                                Reclamation meet = Reclamations.getItems().get(getIndex());
                                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                                alert.setTitle("Confirmation ");
                                                alert.setHeaderText(null);
                                                alert.setContentText("Vous voulez vraiment supprimer cette réclamation ?");
                                                Optional<ButtonType> action = alert.showAndWait();
                                                if (action.get() == ButtonType.OK) {
                                                    sr.DeleteRec(meet);
                                                    n = Notifications.create()
                                                            .title("Succes")
                                                            .text("Reclamation supprimée avec succes")
                                                            .graphic(null)
                                                            .position(Pos.TOP_CENTER);
                                                    //  .hideAfter(Duration.seconds(3));
                                                    n.showInformation();
                                                }
                                                dataList.clear();
                                                try {
                                                    dataList.addAll(sr.AfficherRecUser());
                                                } catch (SQLException e) {
                                                    e.printStackTrace();
                                                }
                                                System.out.println(meet);
                                            });
                                        setGraphic(delete);
                                        setText(null);
                                    }
                                }
                            }
                        };
                        return cell;
                    }
                };

        delCol.setCellFactory(cellFactoryDelete);
        //  Operation.getColumns().addAll(modCol, delCol);
        Reclamations.getColumns().add(idRec);
        Reclamations.getColumns().add(idDate);
        Reclamations.getColumns().add(type);
        Reclamations.getColumns().add(text);
        Reclamations.getColumns().add(sujet);
        Reclamations.getColumns().add(screenshotColumn);
        Reclamations.getColumns().add(statut);
        Reclamations.getColumns().add(progressCol);
        Reclamations.getColumns().add(modCol);
        Reclamations.getColumns().add(delCol);

        List<Reclamation> list = null;
        try {
            list = sr.AfficherRecUser();
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Reclamation Reclamation : list) {
            Reclamations.getItems().add(Reclamation);
        }


        try {
            dataList.addAll(sr.AfficherRecUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Reclamations.getItems().addAll(dataList);
        FilteredList<Reclamation> filteredData = new FilteredList<>(dataList, b -> true);
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(rec -> {
                System.out.println(rec);
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (rec.getText().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(Reclamations.comparatorProperty());
        Reclamations.setItems(sortedData);


        TableColumn<Reclamation, String> idDate_validation = new TableColumn<>("date");
        idDate_validation.setCellValueFactory(new PropertyValueFactory<>("date_validation"));

        TableColumn<Reclamation, String> notification = new TableColumn<>("Mise à jour");
        notification.setCellValueFactory(new PropertyValueFactory<Reclamation, String>(""));

        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFactoryNotif
                = //
                new Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>>() {

                    @Override
                    public TableCell<Reclamation, String> call(TableColumn<Reclamation, String> reclamationStringTableColumn) {
                        final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
                            Label note = new Label();

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    Reclamation Reclamation = Reclamations.getItems().get(getIndex());
                                    if (Reclamation.getStatut().equals("validée")) {
                                        note.setText("Votre réclamation numéro " + Reclamation.getId_reclamation() + " a été validée par l'administrateur");
                                        note.setTextFill(Color.web("#006400"));
                                    }
                                    setGraphic(note);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };


        notification.setCellFactory(cellFactoryNotif);

        notif.getColumns().add(idDate_validation);
        notif.getColumns().add(notification);


        List<Reclamation> list2 = null;
        try {
            list2 = sr.AfficherNotif();
            System.out.println(list2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Reclamation Message : list2) {
            notif.getItems().add(Message);
        }

        screenshotView.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });
        screenshotView.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    path2 = null;
                    for (File file : db.getFiles()) {
                        path2 = file.getName();
                        selectedFile = new File(file.getAbsolutePath());
                        System.out.println("Drag and drop file done and path=" + file.getAbsolutePath());//file.getAbsolutePath()="C:\Users\X\Desktop\ScreenShot.6.png"
                        screenshotView.setImage(new Image("file:" + file.getAbsolutePath()));
                        screenshotView.setFitHeight(150);
                        screenshotView.setFitWidth(250);
                        image.setText(path2);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        screenshotView.setImage(new Image("file:C:\\Users\\Mariem.DESKTOP-L3DPUNQ\\IdeaProjects\\untitled\\src\\images\\drag-drop-upload-1.gif"));

         Tooltip t2 = new Tooltip("Modifier la réclamation en cliquant 2 fois");
                Reclamations.setTooltip(t2);


    }


    public void getAdh1(int id_user1, String nom1, String prenom1, String numGsm1, String username1, String passwd1, String email1, String status1){//String image1)
        try {
            id_user =id_user1;
            nom = nom1;
            prenom = prenom1;
            email = numGsm1;
            mdp = username1;
            date_naissance = passwd1;
            telephone = email1;
            status = status1;
          //  image2 = image1;

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    private void image(ActionEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");


        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            filename=copy(selectedFile);
//                path = selectedFile.toURI().toURL().toExternalForm();
            screenshotView.setImage(new Image(selectedFile.toURI().toURL().toString()));
            screenshotView.setFitHeight(150);
            screenshotView.setFitWidth(250);
            image.setText(selectedFile.getName());

        }

    }


    private String copy(File from) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

        String dir = System.getProperty("user.dir");//get project source path
        File dest = new File(dir + "\\images\\" + sdf1.format(timestamp) + from.getName());//add the full path /ressources + file name

//check if folder ressources is created, sinon create it
        File file = new File(dir + "\\images");
        file.mkdirs();
        /////
        //COPY FILE OPERATION
        InputStream is = null;
        OutputStream os = null;
        try {
            try {
                is = new FileInputStream(from);
                os = new FileOutputStream(dest);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(reclamationController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(reclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(reclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            /////////////////
        }
        return dest.getName();
    }

    @FXML
    public void AjouterRec(ActionEvent event) throws SQLException {
        ServiceReclamation sr = new ServiceReclamation();
        Reclamation r = new Reclamation();

        String erreur="";
        boolean isMyComboBoxEmpty = (type.getSelectionModel().isEmpty());
        if (  isMyComboBoxEmpty && tfréclamation.getText().isEmpty())
        {
            alerte.setText("Vous devez choisir le type et ajouter votre réclamation");
            tfréclamation.requestFocus();
            type.requestFocus();
            erreur = erreur +("Vous devez choisir le type et ajouter votre réclamation");
                    alerte.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent e) {
                    alerte.setScaleX(1.2);
                    alerte.setScaleY(1.2);
                }
            });
            alerte.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent e) {
                    alerte.setScaleX(1);
                    alerte.setScaleY(1);
                }
            });


        }
        else if ( isMyComboBoxEmpty )
        {   alerte.setText("vous devez choisir un type de réclamation");
            System.out.println( "aucun type sélectionné");
            type.requestFocus();
            erreur = erreur +("vous devez choisir un type de réclamation");

        }
        else if (tfréclamation.getText().isEmpty())
        {
            alerte.setText("vous devez ajouter votre réclamation");
            tfréclamation.requestFocus();
            erreur = erreur +("vous devez ajouter votre réclamation");

        }
        else if (object.getText().isEmpty())
        {
            alerte.setText("vous devez ajouter un sujet de réclamation");
            object.requestFocus();
            erreur = erreur +("vous devez ajouter un sujet de réclamation");

        }

        if (isMyComboBoxEmpty && tfréclamation.getText().isEmpty()|| isMyComboBoxEmpty || tfréclamation.getText().isEmpty() ||object.getText().isEmpty() ) {
            n = Notifications.create()
                    .title("Erreur de format")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER);
                   // .hideAfter(Duration.seconds(6));
            n.showInformation();
        }

        else {
            r.getId_user(id_user);
            r.setObject(object.getText());
           r.setText(tfréclamation.getText());
           r.setType(type.getValue().toString());
           r.setScreenshot(filename);
           sr.AddRec(r);

alerte.setVisible(false);
            Notifications n = Notifications.create()
                    .title("Succès")
                    .text("Reclamation ajoutée avec succès")
                    .graphic(null)
                    .position(Pos.TOP_CENTER);
                   // .hideAfter(Duration.ofSeconds(3));
            n.showInformation();
           init();
           dataList.clear();
           try {
               dataList.addAll(sr.AfficherRec());
           } catch (SQLException e) {
               e.printStackTrace();
           }

       }
    }

    public void init() throws SQLException { tfréclamation.setText("");object.setText(""); screenshotView.setImage(new Image("file:C:\\Users\\Mariem.DESKTOP-L3DPUNQ\\IdeaProjects\\untitled\\src\\images\\drag-drop-upload-1.gif"));
        ; }

    public void annuler(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "../GUI/interfaceFormation.fxml"
                    )
            );

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //this accesses the window.
            stage.setScene(
                    new Scene(loader.load())
            );

         //   InterfaceFormation controller = loader.getController();

            stage.show();
        } catch (IOException ex) {
        }
    }
}