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
        private TableColumn<dataEtudiant, String> prenom;

        @FXML
        private TableColumn<dataEtudiant, String> numero;

        @FXML
        private Label erreur;






        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                mail.setCellValueFactory(new PropertyValueFactory<dataEtudiant, String>("mail"));
                nom.setCellValueFactory(new PropertyValueFactory<dataEtudiant,String >("nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<dataEtudiant,String >("prenom"));
                numero.setCellValueFactory(new PropertyValueFactory<dataEtudiant,String >("numero"));

                table.setEditable(true); // Ajoutez cette ligne pour rendre le TableView éditable

                // Regex pour valider l'email
                        String regexMail = "^[A-Za-z0-9+_.-]+@(.+)$";
// Regex pour valider le nom et le prénom (lettres et espaces uniquement)
                String regexNomPrenom = "^[a-zA-Z\\s]+";
// Regex pour valider le numéro de téléphone
                String regexNumero = "^[0-9]{10}$";

                mail.setCellFactory(TextFieldTableCell.forTableColumn());
                mail.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataEtudiant, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataEtudiant, String> t) {
                                        String newValue = t.getNewValue();
                                        if (!newValue.matches(regexMail)) {
                                                erreur.setText("Veuillez entrer un email valide.");
                                                initialize(null, null);
                                        } else {
                                                ((dataEtudiant) t.getTableView().getItems().get(
                                                        t.getTablePosition().getRow())
                                                ).setMail(newValue);
                                                enregistrer();
                                        }
                                }
                        }
                );

                nom.setCellFactory(TextFieldTableCell.forTableColumn());
                nom.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataEtudiant, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataEtudiant, String> t) {
                                        String newValue = t.getNewValue();
                                        if (!newValue.matches(regexNomPrenom)) {
                                                erreur.setText("Veuillez entrer un nom valide.");
                                                initialize(null, null);
                                        } else {
                                                ((dataEtudiant) t.getTableView().getItems().get(
                                                        t.getTablePosition().getRow())
                                                ).setNom(newValue);
                                                enregistrer();
                                        }
                                }
                        }
                );

                prenom.setCellFactory(TextFieldTableCell.forTableColumn());
                prenom.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataEtudiant, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataEtudiant, String> t) {
                                        String newValue = t.getNewValue();
                                        if (!newValue.matches(regexNomPrenom)) {
                                                erreur.setText("Veuillez entrer un prénom valide.");
                                                initialize(null, null);
                                        } else {
                                                ((dataEtudiant) t.getTableView().getItems().get(
                                                        t.getTablePosition().getRow())
                                                ).setPrenom(newValue);
                                                enregistrer();
                                        }
                                }
                        }
                );

                numero.setCellFactory(TextFieldTableCell.forTableColumn());
                numero.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataEtudiant, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataEtudiant, String> t) {
                                        String newValue = t.getNewValue();
                                        if (!newValue.matches(regexNumero)) {
                                                erreur.setText("Veuillez entrer un numéro valide.");
                                                initialize(null, null);
                                        } else {
                                                ((dataEtudiant) t.getTableView().getItems().get(
                                                        t.getTablePosition().getRow())
                                                ).setNumero(newValue);
                                                enregistrer();
                                        }
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
        private TextField nomInput;

        @FXML
        private TextField prenomInput;

        @FXML
        private TextField numeroInput;


        public void enregistrer() {
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
        void entrer(ActionEvent event) throws IOException {
                ObjectMapper mapper = new ObjectMapper();
                // Regex pour valider l'email
                String regexMail = "^[A-Za-z0-9+_.-]+@(.+)$";
                // Regex pour valider le nom et le prénom (lettres et espaces uniquement)
                String regexNomPrenom = "^[a-zA-Z\\s]+";
                // Regex pour valider le numéro de téléphone
                String regexNumero = "^[0-9]{10}$";

                if (mailInput.getText().isEmpty() || nomInput.getText().isEmpty() || prenomInput.getText().isEmpty() || numeroInput.getText().isEmpty()) {
                        erreur.setText("Veuillez remplir tous les champs.");
                } else if (!mailInput.getText().matches(regexMail)) {
                        erreur.setText("Veuillez entrer un email valide.");
                } else if (!nomInput.getText().matches(regexNomPrenom)) {
                        erreur.setText("Veuillez entrer un nom valide.");
                } else if (!prenomInput.getText().matches(regexNomPrenom)) {
                        erreur.setText("Veuillez entrer un prénom valide.");
                } else if (!numeroInput.getText().matches(regexNumero)) {
                        erreur.setText("Veuillez entrer un numéro valide.");
                } else {
                        // Lire le fichier JSON existant
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>() {});
                        List<Map<String, String>> EtudiantsMap = usersMap.get("Etudiants");
                        boolean emailExists = false;
                        for (Map<String, String> map : EtudiantsMap) {
                                if (map.get("mail").equals(mailInput.getText())) {
                                        emailExists = true;
                                        break;
                                }
                        }

                        if (emailExists) {
                                erreur.setText("Cet email existe déjà.");
                        } else {
                                erreur.setText(""); // Effacez le message d'erreur si tous les champs sont valides
                                dataEtudiant dataEtudiant = new dataEtudiant(
                                        mailInput.getText(),
                                        nomInput.getText(),
                                        prenomInput.getText(),
                                        numeroInput.getText()
                                );
                                ObservableList<dataEtudiant> dataEtudiants = table.getItems();
                                dataEtudiants.add(dataEtudiant);
                                table.setItems(dataEtudiants);
                                enregistrer();
                        }
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







