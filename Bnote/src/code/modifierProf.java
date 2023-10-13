package code;

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


public class modifierProf implements Initializable {

        @FXML
        private Button button;

        @FXML
        public void userRetour(ActionEvent event) throws IOException {
                Main m = new Main();
                m.changeScene("adminAcceuil.fxml");
        }



        @FXML
        private TableView<dataProf> table;

        @FXML
        private TableColumn<dataProf, String> mail;

        @FXML
        private TableColumn<dataProf, String> nom;

        @FXML
        private TableColumn<dataProf, String> nomUtilisateur;

        @FXML
        private TableColumn<dataProf, String> prenom;

        @FXML
        private TableColumn<dataProf, String> mot;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                mail.setCellValueFactory(new PropertyValueFactory<dataProf, String>("mail"));
                nom.setCellValueFactory(new PropertyValueFactory<dataProf,String >("nom"));
                nomUtilisateur.setCellValueFactory(new PropertyValueFactory<dataProf,String >("nomUtilisateur"));
                prenom.setCellValueFactory(new PropertyValueFactory<dataProf,String >("prenom"));
                mot.setCellValueFactory(new PropertyValueFactory<dataProf,String >("mot"));

                table.setEditable(true); // Ajoutez cette ligne pour rendre le TableView éditable

                mail.setCellFactory(TextFieldTableCell.forTableColumn());
                mail.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataProf, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataProf, String> t) {
                                        ((dataProf) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setMail(t.getNewValue());
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
                                }
                        }
                );
                nomUtilisateur.setCellFactory(TextFieldTableCell.forTableColumn());
                nomUtilisateur.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataProf, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataProf, String> t) {
                                        ((dataProf) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setNomUtilisateur(t.getNewValue());
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
                                }
                        }
                );
                mot.setCellFactory(TextFieldTableCell.forTableColumn());
                mot.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataProf, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataProf, String> t) {
                                        ((dataProf) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setMot(t.getNewValue());
                                }
                        }
                );

                // Read JSON file and populate TableView
                ObjectMapper mapper = new ObjectMapper();
                try {
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("C:\\Users\\bapto\\OneDrive\\Bureau\\Gnotetizba\\Bnote\\src\\code\\data.json"), new TypeReference<Map<String, List<Map<String, String>>>>() {});
                        List<Map<String, String>> profsMap = usersMap.get("profs");
                        ObservableList<dataProf> dataProfs = FXCollections.observableArrayList();
                        for (Map<String, String> map : profsMap) {
                                dataProf prof = new dataProf(
                                        map.get("mail"),
                                        map.get("nom"),
                                        map.get("nomUtilisateur"),
                                        map.get("prenom"),
                                        map.get("mot")
                                );
                                dataProfs.add(prof);
                        }
                        table.setItems(dataProfs);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }



        public void enregistrer() {
                // Créer un ObjectMapper
                ObjectMapper mapper = new ObjectMapper();
                try {
                        // Lire le fichier JSON existant
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(getClass().getResource("data.json"), new TypeReference<Map<String, List<Map<String, String>>>>() {});

                        // Convertir la liste de dataProf en liste de Map
                        List<Map<String, String>> newProfsMap = new ArrayList<>();
                        for (dataProf prof : table.getItems()) { // Use table.getItems() instead of profs
                                Map<String, String> map = new HashMap<>();
                                map.put("mail", prof.getMail());
                                map.put("nom", prof.getNom());
                                map.put("nomUtilisateur", prof.getNomUtilisateur());
                                map.put("prenom", prof.getPrenom());
                                map.put("mot", prof.getMot());
                                newProfsMap.add(map);
                        }

                        // Update the 'profs' list in usersMap and write it back to the JSON file
                        usersMap.put("profs", newProfsMap);
                        mapper.writeValue(new File("C:\\Users\\bapto\\OneDrive\\Bureau\\Gnotetizba\\Bnote\\src\\code\\data.json"), usersMap);

                } catch (IOException e) {
                        e.printStackTrace();
                }
        }


        @FXML
        private TextField mailInput;

        @FXML
        private TextField nUInput;

        @FXML
        private TextField nomInput;

        @FXML
        private TextField prenomInput;

        @FXML
        private TextField motInput;

        @FXML
        void entrer(ActionEvent event) {

                dataProf dataProf = new dataProf(
                        mailInput.getText(),
                        nomInput.getText(),
                        nUInput.getText(),
                        prenomInput.getText(),
                        motInput.getText()
                );
                ObservableList<dataProf> dataProfs = table.getItems();
                dataProfs.add(dataProf);
                table.setItems(dataProfs);
                enregistrer();
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







