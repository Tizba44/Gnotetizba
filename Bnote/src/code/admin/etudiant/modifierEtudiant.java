package code.admin.etudiant;

import code.Main;
import code.admin.etudiant.dataEtudiant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class modifierEtudiant implements Initializable {






        @FXML
        private Button button;

        @FXML
        public void userRetour(ActionEvent event) throws IOException {
                Main m = new Main();
                m.changeScene("admin/adminAcceuil.fxml");
        }

        @FXML
        private TableView<dataEtudiant> table;

        @FXML
        private TableColumn<dataEtudiant, String> mail;

        @FXML
        private TableColumn<dataEtudiant, String> nom;

        @FXML
        private TableColumn<dataEtudiant, String> nomUtilisateur;

        @FXML
        private TableColumn<dataEtudiant, String> prenom;

        @FXML
        private TableColumn<dataEtudiant, String> numero;

        @FXML
        private Label erreur;






        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                mail.setCellValueFactory(new PropertyValueFactory<dataEtudiant, String>("mail"));
                nom.setCellValueFactory(new PropertyValueFactory<dataEtudiant,String >("nom"));
                nomUtilisateur.setCellValueFactory(new PropertyValueFactory<dataEtudiant,String >("nomUtilisateur"));
                prenom.setCellValueFactory(new PropertyValueFactory<dataEtudiant,String >("prenom"));
                numero.setCellValueFactory(new PropertyValueFactory<dataEtudiant,String >("numero"));

                table.setEditable(true); // Ajoutez cette ligne pour rendre le TableView éditable

                mail.setCellFactory(TextFieldTableCell.forTableColumn());
                mail.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataEtudiant, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataEtudiant, String> t) {
                                        ((dataEtudiant) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setMail(t.getNewValue());
                                        enregistrer();
                                }
                        }
                );
                nom.setCellFactory(TextFieldTableCell.forTableColumn());
                nom.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataEtudiant, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataEtudiant, String> t) {
                                        ((dataEtudiant) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setNom(t.getNewValue());
                                        enregistrer();
                                }
                        }
                );
                nomUtilisateur.setCellFactory(TextFieldTableCell.forTableColumn());
                nomUtilisateur.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataEtudiant, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataEtudiant, String> t) {
                                        ((dataEtudiant) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setNomUtilisateur(t.getNewValue());
                                        enregistrer();
                                }
                        }
                );
                prenom.setCellFactory(TextFieldTableCell.forTableColumn());
                prenom.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataEtudiant, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataEtudiant, String> t) {
                                        ((dataEtudiant) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setPrenom(t.getNewValue());
                                        enregistrer();
                                }
                        }
                );
                numero.setCellFactory(TextFieldTableCell.forTableColumn());
                numero.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataEtudiant, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataEtudiant, String> t) {
                                        ((dataEtudiant) t.getTableView().getItems().get(
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
                        List<Map<String, String>> EtudiantsMap = usersMap.get("Etudiants");
                        ObservableList<dataEtudiant> dataEtudiants = FXCollections.observableArrayList();
                        for (Map<String, String> map : EtudiantsMap) {
                                dataEtudiant Etudiant = new dataEtudiant(
                                        map.get("mail"),
                                        map.get("nom"),
                                        map.get("nomUtilisateur"),
                                        map.get("prenom"),
                                        map.get("numero")
                                );
                                dataEtudiants.add(Etudiant);
                        }
                        table.setItems(dataEtudiants);
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
        private TextField numeroInput;


        public void enregistrer() {
                erreur.setText(""); // Effacez le message d'erreur si tous les champs sont valides
                // Créer un ObjectMapper
                ObjectMapper mapper = new ObjectMapper();
                try {
                        // Lire le fichier JSON existant
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>() {});

                        // Convertir la liste de dataEtudiant en liste de MapgetClass().getResource("data.json")
                        List<Map<String, String>> newEtudiantsMap = new ArrayList<>();
                        for (dataEtudiant Etudiant : table.getItems()) { // Use table.getItems() instead of Etudiants
                                Map<String, String> map = new HashMap<>();
                                map.put("mail", Etudiant.getMail());
                                map.put("nom", Etudiant.getNom());
                                map.put("nomUtilisateur", Etudiant.getNomUtilisateur());
                                map.put("prenom", Etudiant.getPrenom());
                                map.put("numero", Etudiant.getNumero());
                                newEtudiantsMap.add(map);
                        }

                        // Update the 'Etudiants' list in usersMap and write it back to the JSON file
                        usersMap.put("Etudiants", newEtudiantsMap);

                        mapper.writeValue(new File("src/code/data.json"), usersMap);


                } catch (IOException e) {
                        e.printStackTrace();
                }
        }




        @FXML
        void entrer(ActionEvent event) {
                if (mailInput.getText().isEmpty() || nomInput.getText().isEmpty() || nomInput.getText().isEmpty() || prenomInput.getText().isEmpty() ) {
                        erreur.setText("Veuillez remplir tous les champs.");

                } else {
                dataEtudiant dataEtudiant = new dataEtudiant(
                        mailInput.getText(),
                        nomInput.getText(),
                        nUInput.getText(),
                        prenomInput.getText(),
                        numeroInput.getText()

                );
                ObservableList<dataEtudiant> dataEtudiants = table.getItems();
                dataEtudiants.add(dataEtudiant);
                table.setItems(dataEtudiants);
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







