package code.prof.note;

import code.Main;
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
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static javafx.scene.control.cell.TextFieldTableCell.forTableColumn;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.cell.TextFieldTableCell;



public class note implements Initializable {


        @FXML
        private Button button;

        @FXML
        public void userRetour(ActionEvent event) throws IOException {
                Main m = new Main();
                m.changeScene("prof/profAcceuil.fxml");
        }

        @FXML
        private TableView<noteData> table;
        @FXML
        private Label erreur;

        @FXML
        private TableColumn<noteData, String> nomUtilisateur;

        @FXML
        private TableColumn<noteData, String> appreciation;

        @FXML
        private TableColumn<noteData, Integer> moyenne;

        @FXML
        private TableColumn<noteData, Integer> note;

        @FXML
        private TableColumn<noteData, Integer> coef;

        @FXML
        private TableColumn<noteData, String> controle;

        @FXML
        private TextField inputCoef;

        @FXML
        private TextField inputControle;

        @FXML
        private TextField inputNote;

        @FXML
        private TextField inputappreciation;


sls

        @FXML
        public void initialize(URL url, ResourceBundle resourceBundle) {
                // Configuration des colonnes du TableView en utilisant PropertyValueFactory
                nomUtilisateur.setCellValueFactory(new PropertyValueFactory<noteData, String>("nomUtilisateur"));
//                moyenne.setCellValueFactory(new PropertyValueFactory<noteData, Integer>("moyenne"));

                // Lecture du fichier JSON et peuplement du TableView
                ObjectMapper mapper = new ObjectMapper();
                try {
                        // Lecture des données du fichier JSON dans une structure de données Java
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>(){});

                        // Extraction des listes d'utilisateurs et de contrôles depuis les données JSON
                        List<Map<String, String>> EtudiantsMap = usersMap.get("Etudiants");
                        List<Map<String, String>> controlesMap = usersMap.get("controles");

                        // Création d'une liste observable pour stocker les données à afficher dans le TableView
                        ObservableList<noteData> notes = FXCollections.observableArrayList();

                        // Création d'une liste pour stocker dynamiquement les colonnes "controle"
                        ArrayList<TableColumn<noteData, ?>> controleColumns = new ArrayList<>();

                        for (Map<String, String> controle : controlesMap) {
                                // Vérification si une colonne "controle" existe déjà
                                boolean columnExists = false;
                                for (TableColumn<noteData, ?> column : controleColumns) {
                                        if (column.getText().equals(controle.get("controle"))) {
                                                columnExists = true;
                                                break;
                                        }
                                }

                                if (!columnExists) {
                                        // Création d'une nouvelle colonne "controle"
                                        TableColumn<noteData, String> newControleColumn = new TableColumn<>(controle.get("controle"));
                                        newControleColumn.setPrefWidth(217.5999755859375);

                                        // Ajout des sous-colonnes pour "coef," "note," et "appreciation"
                                        TableColumn<noteData, String> coefColumn = new TableColumn<>("coef");
                                        coefColumn.setPrefWidth(150.4000244140625);
                                        TableColumn<noteData, Integer> noteColumn = new TableColumn<>("note");
                                        noteColumn.setPrefWidth(75.0);
                                        TableColumn<noteData, String> appreciationColumn = new TableColumn<>("appréciation");
                                        appreciationColumn.setPrefWidth(75.0);

                                        // Configuration des factories de valeur des sous-colonnes
                                        coefColumn.setCellValueFactory(new PropertyValueFactory<>("coef"));
                                        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
                                        appreciationColumn.setCellValueFactory(new PropertyValueFactory<>("appreciation"));

                                        // Ajout des sous-colonnes à la nouvelle colonne "controle"
                                        newControleColumn.getColumns().addAll(coefColumn, noteColumn, appreciationColumn);

                                        // Ajout de la nouvelle colonne "controle" au TableView
                                        table.getColumns().add(newControleColumn);

                                        // Ajout de la nouvelle colonne "controle" à la liste pour référence
                                        controleColumns.add(newControleColumn);
                                }
                                for (Map<String, String> etudiant : EtudiantsMap) {
                                        // Vérification si le nom d'utilisateur de l'étudiant correspond au "nomUtilisateur" du contrôle
                                        if (etudiant.get("nomUtilisateur").equals(controle.get("nomUtilisateur"))) {
                                                noteData note = new noteData(
                                                        controle.get("nomUtilisateur"),
                                                        controle.get("note") != null ? Integer.parseInt(controle.get("note")) : 0,
                                                        controle.get("controle"),
                                                        controle.get("coef") != null ? Integer.parseInt(controle.get("coef")) : 0,
                                                        controle.get("appreciation")
//                                                        controle.get("moyenne") != null ? Integer.parseInt(controle.get("moyenne")) : 0
                                                );
                                                // Ajout de la note à la liste observable
                                                notes.add(note);
                                        }
                                }
                        }

                        // Définition des données du TableView
                        table.setItems(notes);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }



        public void enregistrer() {

//                // Créer un ObjectMapper
//                ObjectMapper mapper = new ObjectMapper();
//                try {
//                        // Lire le fichier JSON existant
//                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>() {});
//
//                        // Convertir la liste de dataEtudiant en liste de MapgetClass().getResource("data.json")
//                        List<Map<String, String>> newEtudiantsMap = new ArrayList<>();
//                        for (noteData Etudiant : table.getItems()) { // Use table.getItems() instead of Etudiants
//                                Map<String, String> map = new HashMap<>();
//                                map.put("mail", Etudiant.getMail());
//                                map.put("nom", Etudiant.getNom());
//                                map.put("nomUtilisateur", Etudiant.getNomUtilisateur());
//                                map.put("prenom", Etudiant.getPrenom());
//                                map.put("numero", Etudiant.getNumero());
//                                newEtudiantsMap.add(map);
//                        }
//
//                        // Update the 'Etudiants' list in usersMap and write it back to the JSON file
//                        usersMap.put("Etudiants", newEtudiantsMap);
//
//                        mapper.writeValue(new File("src/code/data.json"), usersMap);
//
//
//                } catch (IOException e) {
//                        e.printStackTrace();
//                }
        }




        @FXML
        void entrer(ActionEvent event) {
//                if (  inputCoef.getText().isEmpty() || inputControle.getText().isEmpty() || inputNote.getText().isEmpty() || inputappreciation.getText().isEmpty()) {
//                        erreur.setText("Veuillez remplir tous les champs.");
//
//                } else {
//                erreur.setText(""); // Effacez le message d'erreur si tous les champs sont valides
//                noteData note = new noteData(
//                        "nomUtilisateur"
//                        , Integer.parseInt(inputNote.getText())
//                        , inputControle.getText()
//                        , Integer.parseInt(inputCoef.getText())
//                        , inputappreciation.getText()
//                        , 0
//                );
//                ObservableList<noteData> notes = table.getItems();
//                notes.add(note);
//                table.setItems(notes);
//                enregistrer();
//                }
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







