package code.admin.prof;

import code.Main;
import code.admin.etudiant.dataEtudiant;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;




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
        void entrer(ActionEvent event) throws IOException, NoSuchAlgorithmException {
                ObjectMapper mapper = new ObjectMapper();
                // Regex for validating the email
                String regexMail = "^[A-Za-z0-9+_.-]+@(.+)$";
                // Regex for validating the name and surname (letters and spaces only)
                String regexNomPrenom = "^[a-zA-Z\\s]+";
                // Regex for validating the phone number
                String regexNumero = "^[0-9]{10}$";
                // Regex for validating the password (at least 8 characters, at least one uppercase letter, one lowercase letter, one number and one special character)
                String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
                // comme par exemple : 12345678aA@

                if (mailInput.getText().isEmpty() || nomInput.getText().isEmpty() || prenomInput.getText().isEmpty() || numeroInput.getText().isEmpty() || motDePasseInput.getText().isEmpty()) {
                        erreur.setText("Veuillez remplir tous les champs.");
                } else if (!mailInput.getText().matches(regexMail)) {
                        erreur.setText("Veuillez entrer un email valide.");
                } else if (!nomInput.getText().matches(regexNomPrenom)) {
                        erreur.setText("Veuillez entrer un nom valide.");
                } else if (!prenomInput.getText().matches(regexNomPrenom)) {
                        erreur.setText("Veuillez entrer un prénom valide.");
                } else if (!numeroInput.getText().matches(regexNumero)) {
                        erreur.setText("Veuillez entrer un numéro valide");
                } else if (!motDePasseInput.getText().matches(regexPassword)) {
                        erreur.setText("Veuillez entrer un mot de passe valide avec au moins 8 caractères, une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial.");
                } else {
                        // Read the existing JSON file
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>() {});
                        List<Map<String, String>> profsMap = usersMap.get("profs");
                        boolean emailExists = false;
                        for (Map<String, String> map : profsMap) {
                                if (map.get("mail").equals(mailInput.getText())) {
                                        emailExists = true;
                                        break;
                                }
                        }

                        if (emailExists) {
                                erreur.setText("Cet email existe déjà.");
                        } else {
                                erreur.setText(""); // Clear the error message if all fields are valid
                                // Encrypt the password
                                MessageDigest md = MessageDigest.getInstance("SHA-256");
                                byte[] hash = md.digest(motDePasseInput.getText().getBytes(StandardCharsets.UTF_8));
                                StringBuilder sb = new StringBuilder();
                                for (byte b : hash) {
                                        sb.append(String.format("%02x", b));
                                }
                                String encryptedPassword = sb.toString();

                                dataProf dataProf = new dataProf(
                                        mailInput.getText(),
                                        nomInput.getText(),
                                        prenomInput.getText(),
                                        encryptedPassword,
                                        numeroInput.getText()
                                );
                                ObservableList<dataProf> dataProfs = table.getItems();
                                dataProfs.add(dataProf);
                                table.setItems(dataProfs);

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







