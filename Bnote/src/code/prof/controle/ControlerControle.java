package code.prof.controle;

import code.Main;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;



import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.scene.control.TextField;


import javafx.util.converter.LocalDateStringConverter;
import javafx.scene.control.ChoiceBox;


public class ControlerControle implements Initializable {


        @FXML
        private Button button;

        @FXML
        public void userRetour(ActionEvent event) throws IOException {
                Main m = new Main();
                m.changeScene("prof/profAcceuil.fxml");
        }

        @FXML
        private TableView<liaisonMailEtudiantObjetControle> table;
        @FXML
        private Label erreur;

        @FXML
        private TableColumn<liaisonMailEtudiantObjetControle, String> mail;

        @FXML
        private TextField inputCoef;

        @FXML
        private TextField inputControle;

        @FXML
        private TextField inputsuppr;


        @FXML
        private ChoiceBox<String> matiere;

        @FXML
        private Label labelMatiere;







        @FXML
        private ChoiceBox<String> choiceBoxMatiere;









        @FXML
        public void initialize(URL url, ResourceBundle resourceBundle) {
                // Remplir la ChoiceBox avec les matières du professeur
                if (choiceBoxMatiere.getItems().isEmpty()) {
                        choiceBoxMatiere.getItems().addAll(Main.matieresProf);
                }
                // Sélectionner la première matière par défaut si aucune matiere na deja été selectionnée
                if (Main.matiereProf == null) {
                        choiceBoxMatiere.getSelectionModel().selectFirst();
                        tableaux();
                } else {
                        // Sélectionner la matière précédemment sélectionnée
                        choiceBoxMatiere.getSelectionModel().select(Main.matiereProf);
                        tableaux();
                }

                // Ajouter un écouteur pour mettre à jour la matière sélectionnée
                choiceBoxMatiere.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                        // newValue est la nouvelle matière sélectionnée
                        // Vous pouvez l'utiliser pour mettre à jour votre variable
                        Main.matiereProf = newValue;

                        // vider les colonnes de controle pour mettre à jour les controles de la nouvelle matière ne pas vider la colonne mail
                        for (TableColumn<liaisonMailEtudiantObjetControle, ?> column : new ArrayList<>(table.getColumns())) {
                                if (!column.getText().equals("mailEtudiants")) {
                                        table.getColumns().remove(column);
                                }
                        }
                        tableaux();

                });

                //changer le label de la matière
                labelMatiere.setText("Matière : " + choiceBoxMatiere.getValue());
        }





        public void tableaux() {
                mail.setCellValueFactory(new PropertyValueFactory<liaisonMailEtudiantObjetControle, String>("mailEtudiants"));
                ObjectMapper mapper = new ObjectMapper();
                table.setEditable(true);
                try {
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>(){});
                        List<Map<String, String>> EtudiantsMap = usersMap.get("etudiants");
                        List<Map<String, String>> controlesMap = usersMap.get("controles");


//                        supprimer les valeurs ObservableList existantes
                        table.getItems().clear();

                        ObservableList<liaisonMailEtudiantObjetControle> notes = FXCollections.observableArrayList();

                        ArrayList<TableColumn<liaisonMailEtudiantObjetControle, ?>> controleColumns = new ArrayList<>();

                        for (Map<String, String> controle : controlesMap) {
                                if (choiceBoxMatiere.getValue() == null || !choiceBoxMatiere.getValue().equals(controle.get("matiereID"))) {
                                        continue;
                                }
                                // Obtenez la liste des colonnes existantes
                                ObservableList<TableColumn<liaisonMailEtudiantObjetControle, ?>> existingColumns = table.getColumns();
                                // Vérifiez si la colonne existe déjà
                                boolean columnExists = false;
                                for (TableColumn<liaisonMailEtudiantObjetControle, ?> column : existingColumns) {
                                        if (column.getText().equals(controle.get("intituleID"))) {
                                                columnExists = true;
                                                break;
                                        }
                                }
                                if (!columnExists) {
                                        TableColumn<liaisonMailEtudiantObjetControle, String> newControleColumn = new TableColumn<>(controle.get("intituleID"));
                                        newControleColumn.setPrefWidth(375);
                                        TableColumn<liaisonMailEtudiantObjetControle, String> noteColumn = new TableColumn<>("note");
                                        noteColumn.setPrefWidth(75.0);
                                        TableColumn<liaisonMailEtudiantObjetControle, String> coefColumn = new TableColumn<>("coef");
                                        coefColumn.setPrefWidth(75);
                                        TableColumn<liaisonMailEtudiantObjetControle, String> appreciationColumn = new TableColumn<>("appréciation");
                                        appreciationColumn.setPrefWidth(150.0);
                                        TableColumn<liaisonMailEtudiantObjetControle, LocalDate> dateColumn = new TableColumn<>("date");
                                        dateColumn.setPrefWidth(75.0);

                                        noteColumn.setCellValueFactory(cellData -> {
                                                liaisonMailEtudiantObjetControle note = cellData.getValue();
                                                ControleData controleData = note.getControles().get(controle.get("intituleID"));
                                                return new SimpleStringProperty (controleData != null ? String.valueOf(controleData.getNote()) : "N/A");
                                        });

                                        coefColumn.setCellValueFactory(cellData -> {
                                                liaisonMailEtudiantObjetControle note = cellData.getValue();
                                                ControleData controleData = note.getControles().get(controle.get("intituleID"));
                                                return new SimpleStringProperty(controleData != null ? String.valueOf(controleData.getCoef()) : "N/A");
                                        });

                                        appreciationColumn.setCellValueFactory(cellData -> {
                                                liaisonMailEtudiantObjetControle note = cellData.getValue();
                                                ControleData controleData = note.getControles().get(controle.get("intituleID"));
                                                return new SimpleStringProperty(controleData != null ? controleData.getAppreciation() : "N/A");
                                        });
                                        dateColumn.setCellValueFactory(cellData -> {
                                                liaisonMailEtudiantObjetControle note = cellData.getValue();
                                                ControleData controleData = note.getControles().get(controle.get("intituleID"));
                                                return new SimpleObjectProperty<>(controleData != null ? controleData.getDate() : null);
                                        });

                                        table.setEditable(true);

                                        noteColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                                        coefColumn.setCellFactory(TextFieldTableCell.forTableColumn());

                                        appreciationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                                        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));

                                        // Regex pour valider la note entre 0 et 20
                                        String regexNote = "([0-9]|1[0-9]|20)";

                                        // Regex pour valider le coefficient
                                        String regexCoef = "[1-9]|10";
                                        //      Regex pour valider la date
                                        String regexDate = "([0-9]{4})-([0-9]{2})-([0-9]{2})";
                                        // Regex pour valider l'appréciation
                                        String regexAppreciation = "[A-Za-z0-9 ]*";


                                        noteColumn.setOnEditCommit(event -> {
                                                liaisonMailEtudiantObjetControle note = event.getRowValue();
                                                ControleData controleData = note.getControles().get(controle.get("intituleID"));


                                          if (event.getNewValue().matches(regexNote)) {
                                                  controleData.setNote(Integer.parseInt(event.getNewValue()));
                                                  enregistrer();
                                          } else {
                                                  erreur.setText("La note doit être un entier entre 0 et 20.");
                                                  initialize(null, null);
                                          }
                                        });

                                        coefColumn.setOnEditCommit(event -> {
                                                liaisonMailEtudiantObjetControle note = event.getRowValue();
                                                ControleData controleData = note.getControles().get(controle.get("intituleID"));
                                                if (event.getNewValue().matches(regexCoef)) {
                                                        controleData.setCoef(Integer.parseInt(event.getNewValue()));
                                                        enregistrer();
                                                } else {
                                                        erreur.setText("Le coefficient doit être un entier entre 1 et 10.");
                                                        initialize(null, null);
                                                }
                                        });

                                        appreciationColumn.setOnEditCommit(event -> {
                                                liaisonMailEtudiantObjetControle note = event.getRowValue();
                                                ControleData controleData = note.getControles().get(controle.get("intituleID"));
                                                if (event.getNewValue().matches(regexAppreciation)) {
                                                        controleData.setAppreciation(event.getNewValue());
                                                        enregistrer();
                                                } else {
                                                        erreur.setText("L'appréciation ne doit contenir que des lettres, des chiffres et des espaces.");
                                                        initialize(null, null);
                                                }
                                        });

                                        dateColumn.setOnEditCommit(event -> {
                                                liaisonMailEtudiantObjetControle note = event.getRowValue();
                                                ControleData controleData = note.getControles().get(controle.get("intituleID"));
                                                if (event.getNewValue().toString().matches(regexDate)) {
                                                        controleData.setDate(event.getNewValue());
                                                        enregistrer();
                                                } else {
                                                        erreur.setText("La date doit être au format AAAA-MM-JJ.");
                                                        initialize(null, null);
                                                }
                                        });

                                        newControleColumn.getColumns().addAll(noteColumn, coefColumn,  appreciationColumn , dateColumn);
                                        table.getColumns().add(newControleColumn);
                                        controleColumns.add(newControleColumn);
                                }
                        }



                        for (Map<String, String> etudiant : EtudiantsMap) {
                                liaisonMailEtudiantObjetControle newEtudiant = new liaisonMailEtudiantObjetControle(etudiant.get("mailID"));
                                for (Map<String, String> controle : controlesMap) {
                                        if (controle.get("mailEtudiants").equals(etudiant.get("mailID")) ) {

                                                ControleData newControle = new ControleData(Integer.parseInt(controle.get("note")), Integer.parseInt(controle.get("coef")), controle.get("appreciation"), LocalDate.parse(controle.get("date")) , controle.get("matiereID") );
                                                newEtudiant.addControle(controle.get("intituleID"), newControle);
                                        }
                                }
                                notes.add(newEtudiant);
                        }

                        table.setItems(notes);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public void enregistrer() {
                ObjectMapper mapper = new ObjectMapper();
                try {
                        // Lire les données existantes à partir du fichier JSON
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>(){});

                        // Obtenir les données actuelles de la table
                        ObservableList<liaisonMailEtudiantObjetControle> notes = table.getItems();

//                        //print the data
//                        for (noteData note : notes) {
//                                System.out.println(note.getMail());
//                                Map<String, ControleData> controles = note.getControles();
//                                for (Map.Entry<String, ControleData> entry : controles.entrySet()) {
//                                        System.out.println(entry.getKey() + " " + entry.getValue().getCoef() + " " + entry.getValue().getNote() + " " + entry.getValue().getAppreciation() + " " + entry.getValue().getDate());
//                                }
//                        }

                        // Préparer les listes pour 'Etudiants' et 'controles'
                        List<Map<String, String>> EtudiantsMap = new ArrayList<>();
                        List<Map<String, String>> controlesMap = new ArrayList<>(usersMap.get("controles")); // copier les contrôles existants

                        for (liaisonMailEtudiantObjetControle note : notes) {
                                // Ajouter chaque étudiant à 'EtudiantsMap'
                                Map<String, String> etudiant = new HashMap<>();
                                etudiant.put("mailEtudiants", note.getMailEtudiants());
                                EtudiantsMap.add(etudiant);
                                Map<String, ControleData> controles = note.getControles();
                                for (Map.Entry<String, ControleData> entry : controles.entrySet()) {
                                        // Vérifiez si la matière du contrôle correspond à la matière actuelle
                                        if (entry.getValue().getMatiere().equals(choiceBoxMatiere.getValue())) {
                                                Map<String, String> controle = new HashMap<>();
                                                controle.put("mailEtudiants", note.getMailEtudiants());
                                                controle.put("date", entry.getValue().getDate().toString());
                                                controle.put("intituleID", entry.getKey());
                                                controle.put("coef", String.valueOf(entry.getValue().getCoef()));
                                                controle.put("note", String.valueOf(entry.getValue().getNote()));
                                                controle.put("appreciation", entry.getValue().getAppreciation());
                                                controle.put("matiereID", choiceBoxMatiere.getValue()); // ajoute la matière actuelle
                                                // Supprimez le contrôle existant pour l'étudiant
                                                controlesMap.removeIf(existingControle -> existingControle.get("mailEtudiants").equals(note.getMailEtudiants()) && existingControle.get("intituleID").equals(entry.getKey()) && existingControle.get("matiereID").equals(choiceBoxMatiere.getValue()));
                                                // Ajoutez le nouveau contrôle
                                                controlesMap.add(controle);
                                        }
                                }
                        }

                        // Mettre à jour 'Etudiants' et 'controles' dans 'usersMap'
                        usersMap.put("controles", controlesMap);

                        // Écrire toutes les données dans le fichier JSON
                        mapper.writeValue(new File("src/code/data.json"), usersMap);




                } catch (IOException e) {
                        e.printStackTrace();
                }
        }







        @FXML
        void entrer(ActionEvent event) {
                if (inputControle.getText().isEmpty() || inputCoef.getText().isEmpty())  {
                        erreur.setText("Veuillez remplir tous les champs.");
                } else if  (!inputControle.getText().matches("[A-Za-z0-9 ]*")) {
                        erreur.setText("Le nom du contrôle ne doit contenir que des lettres, des chiffres et des espaces.");
                } else if (!inputCoef.getText().matches("[0-9]+") || Integer.parseInt(inputCoef.getText()) < 1 || Integer.parseInt(inputCoef.getText()) > 10) {
                        erreur.setText("Le coefficient doit être un entier entre 1 et 10.");
                } else if (table.getColumns().stream().anyMatch(column -> column.getText().equals(inputControle.getText()))) {
                        erreur.setText("Il y a déjà un contrôle avec ce nom.");
                } else {
                        erreur.setText(""); // Clear the error message if all fields are valid
                        // Create a new control
                        LocalDate date = LocalDate.now(); // get the current date

                        ControleData newControle = new ControleData(0, Integer.parseInt(inputCoef.getText()), "N/A", date, choiceBoxMatiere.getValue());

                        // Add the new control to the list of controls
                        for (liaisonMailEtudiantObjetControle note : table.getItems()) {
                                note.addControle(inputControle.getText(), newControle);
                        }

                        enregistrer();

                        tableaux();
                }
        }



        @FXML
        void supprimer(ActionEvent event) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                        // Read the existing data from the JSON file
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>(){});

                        // Get the control to be deleted from 'inputsuppr'
                        String controleToDelete = inputsuppr.getText();

                        // Get the list of controls
                        List<Map<String, String>> controlesMap = usersMap.get("controles");

                        // Check if the control exists
                        boolean controlExists = controlesMap.stream().anyMatch(controle -> controle.get("intituleID").equals(controleToDelete) && controle.get("matiereID").equals(choiceBoxMatiere.getValue()));

                        if (!controlExists) {
                                erreur.setText("Il n'y a pas de contrôle avec ce nom à supprimer.");
                                return;
                        }

                        // Remove the controls that match 'controleToDelete' and 'choiceBoxMatiere.getValue()'
                        controlesMap.removeIf(controle -> controle.get("intituleID").equals(controleToDelete) && controle.get("matiereID").equals(choiceBoxMatiere.getValue()));

                        // Update 'controles' in 'usersMap'
                        usersMap.put("controles", controlesMap);

                        // Write all data back to the JSON file
                        mapper.writeValue(new File("src/code/data.json"), usersMap);

                        // Remove the corresponding column from the table
                        for (TableColumn<liaisonMailEtudiantObjetControle, ?> column : new ArrayList<>(table.getColumns())) {
                                if (column.getText().equals(controleToDelete)) {
                                        table.getColumns().remove(column);
                                }
                        }


                        // jouer initialize
                        tableaux();

                        erreur.setText(""); // Clear the error message if all fields are valid
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}







