package code.admin.prof;

import code.Main;
import code.admin.prof.dataProf;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.event.EventHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;


public class modifierProf implements Initializable {






        @FXML
        private Button button;

        @FXML
        public void userRetour(ActionEvent event) throws IOException {
                Main m = new Main();
                m.changeScene("admin/adminAcceuil.fxml");
        }

        @FXML
        private TableView<dataProf> table;

        @FXML
        private TableColumn<dataProf, String> mail;

        @FXML
        private TableColumn<dataProf, String> nom;

        @FXML
        private TableColumn<dataProf, String> prenom;

        @FXML
        private TableColumn<dataProf, String> motDePasse;

        @FXML
        private TableColumn<dataProf, String> numero;



        @FXML
        private Label erreur;


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                mail.setCellValueFactory(new PropertyValueFactory<dataProf, String>("mail"));
                nom.setCellValueFactory(new PropertyValueFactory<dataProf,String >("nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<dataProf,String >("prenom"));
                motDePasse.setCellValueFactory(new PropertyValueFactory<dataProf,String >("motDePasse"));
                numero.setCellValueFactory(new PropertyValueFactory<dataProf,String >("numero"));


                table.setEditable(true); // Ajoutez cette ligne pour rendre le TableView éditable

                mail.setCellFactory(TextFieldTableCell.forTableColumn());
                mail.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataProf, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataProf, String> t) {
                                        ((dataProf) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setMail(t.getNewValue());
                                        enregistrer();
                                }
                        }
                );
                nom.setCellFactory(TextFieldTableCell.forTableColumn());
                nom.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataProf, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataProf, String> t) {
                                        ((dataProf) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setNom(t.getNewValue());
                                        enregistrer();
                                }
                        }
                );
                prenom.setCellFactory(TextFieldTableCell.forTableColumn());
                prenom.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataProf, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataProf, String> t) {
                                        ((dataProf) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setPrenom(t.getNewValue());
                                        enregistrer();
                                }
                        }
                );
                motDePasse.setCellFactory(TextFieldTableCell.forTableColumn());
                motDePasse.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataProf, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataProf, String> t) {
                                        ((dataProf) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setMotDePasse(t.getNewValue());
                                        enregistrer();
                                }
                        }
                );
                numero.setCellFactory(TextFieldTableCell.forTableColumn());
                numero.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataProf, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataProf, String> t) {
                                        ((dataProf) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setNumero(t.getNewValue());
                                        enregistrer();
                                }
                        }
                );

                // Read JSON file and populate TableView
                ObjectMapper mapper = new ObjectMapper();
                try {
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue( new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>() {});
                        List<Map<String, String>> profsMap = usersMap.get("profs");
                        ObservableList<dataProf> dataProfs = FXCollections.observableArrayList();
                        for (Map<String, String> map : profsMap) {
                                dataProf prof = new dataProf(
                                        map.get("mail"),
                                        map.get("nom"),
                                        map.get("prenom"),
                                        map.get("motDePasse"),
                                        map.get("numero")
                                );
                                dataProfs.add(prof);
                        }
                        table.setItems(dataProfs);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }


        @FXML
        private TextField mailInput;

        @FXML
        private TextField nomInput;

        @FXML
        private TextField prenomInput;

        @FXML
        private PasswordField motDePasseInput;

        @FXML
        private TextField numeroInput;




        public void enregistrer() {
                // Créer un ObjectMapper
                ObjectMapper mapper = new ObjectMapper();
                try {
                        // Lire le fichier JSON existant
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>() {});

                        // Convertir la liste de dataProf en liste de MapgetClass().getResource("data.json")
                        List<Map<String, String>> newProfsMap = new ArrayList<>();
                        for (dataProf prof : table.getItems()) { // Use table.getItems() instead of profs
                                Map<String, String> map = new HashMap<>();
                                map.put("mail", prof.getMail());
                                map.put("nom", prof.getNom());
                                map.put("prenom", prof.getPrenom());
                                map.put("motDePasse", prof.getMotDePasse());
                                map.put("numero", prof.getNumero());
                                newProfsMap.add(map);
                        }

                        // Update the 'profs' list in usersMap and write it back to the JSON file
                        usersMap.put("profs", newProfsMap);

                        mapper.writeValue(new File("src/code/data.json"), usersMap);


                } catch (IOException e) {
                        e.printStackTrace();
                }
        }




        @FXML
        void entrer(ActionEvent event) {
                if (mailInput.getText().isEmpty() || nomInput.getText().isEmpty()  || prenomInput.getText().isEmpty() || motDePasseInput.getText().isEmpty() || numeroInput.getText().isEmpty() )  {
                        erreur.setText("Veuillez remplir tous les champs.");

                } else {
                erreur.setText(""); // Effacez le message d'erreur si tous les champs sont valides
                dataProf dataProf = new dataProf(
                        mailInput.getText(),
                        nomInput.getText(),
                        prenomInput.getText(),
                        motDePasseInput.getText(),
                        numeroInput.getText()

                );
                ObservableList<dataProf> dataProfs = table.getItems();
                dataProfs.add(dataProf);
                table.setItems(dataProfs);

                enregistrer();
                }
        }

        @FXML
        void supprimer(ActionEvent event) {
                int selectedID = table.getSelectionModel().getSelectedIndex();
                if (selectedID >= 0) { // Check if an item is selected
                        table.getItems().remove(selectedID);
                        enregistrer();
                }
        }
}







