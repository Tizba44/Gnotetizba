package code.prof.note;

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
        private TableColumn<noteData, String> mail;

        @FXML
        private TextField inputCoef;

        @FXML
        private TextField inputControle;

        @FXML
        private TextField inputsuppr;







        @FXML
        public void initialize(URL url, ResourceBundle resourceBundle) {
                mail.setCellValueFactory(new PropertyValueFactory<noteData, String>("mail"));
                ObjectMapper mapper = new ObjectMapper();
                table.setEditable(true);
                try {
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>(){});
                        List<Map<String, String>> EtudiantsMap = usersMap.get("Etudiants");
                        List<Map<String, String>> controlesMap = usersMap.get("controles");
                        ObservableList<noteData> notes = FXCollections.observableArrayList();
                        ArrayList<TableColumn<noteData, ?>> controleColumns = new ArrayList<>();

                        for (Map<String, String> controle : controlesMap) {
                                if (Main.matiereProf == null || !Main.matiereProf.equals(controle.get("matiere"))) {
                                        continue;
                                }

                                // Obtenez la liste des colonnes existantes
                                ObservableList<TableColumn<noteData, ?>> existingColumns = table.getColumns();

                                // Vérifiez si la colonne existe déjà
                                boolean columnExists = false;
                                for (TableColumn<noteData, ?> column : existingColumns) {
                                        if (column.getText().equals(controle.get("controle"))) {
                                                columnExists = true;
                                                break;
                                        }
                                }

                                if (!columnExists) {
                                        TableColumn<noteData, String> newControleColumn = new TableColumn<>(controle.get("controle"));
                                        newControleColumn.setPrefWidth(217.5999755859375);
                                        TableColumn<noteData, String> coefColumn = new TableColumn<>("coef");
                                        coefColumn.setPrefWidth(150.4000244140625);
                                        TableColumn<noteData, String> noteColumn = new TableColumn<>("note");
                                        noteColumn.setPrefWidth(75.0);
                                        TableColumn<noteData, String> appreciationColumn = new TableColumn<>("appréciation");
                                        appreciationColumn.setPrefWidth(75.0);
                                        TableColumn<noteData, LocalDate> dateColumn = new TableColumn<>("date");
                                        dateColumn.setPrefWidth(75.0);

                                        coefColumn.setCellValueFactory(cellData -> {
                                                noteData note = cellData.getValue();
                                                ControleData controleData = note.getControles().get(controle.get("controle"));
                                                return new SimpleStringProperty(controleData != null ? String.valueOf(controleData.getCoef()) : "N/A");
                                        });
                                        noteColumn.setCellValueFactory(cellData -> {
                                                noteData note = cellData.getValue();
                                                ControleData controleData = note.getControles().get(controle.get("controle"));
                                                return new SimpleStringProperty (controleData != null ? String.valueOf(controleData.getNote()) : "N/A");
                                        });
                                        appreciationColumn.setCellValueFactory(cellData -> {
                                                noteData note = cellData.getValue();
                                                ControleData controleData = note.getControles().get(controle.get("controle"));
                                                return new SimpleStringProperty(controleData != null ? controleData.getAppreciation() : "N/A");
                                        });
                                        dateColumn.setCellValueFactory(cellData -> {
                                                noteData note = cellData.getValue();
                                                ControleData controleData = note.getControles().get(controle.get("controle"));
                                                return new SimpleObjectProperty<>(controleData != null ? controleData.getDate() : null);
                                        });

                                        table.setEditable(true);

                                        coefColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                                        noteColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                                        appreciationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                                        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));

                                noteColumn.setOnEditCommit(event -> {
                                                noteData note = event.getRowValue();
                                                ControleData controleData = note.getControles().get(controle.get("controle"));
                                                controleData.setNote(Integer.parseInt(event.getNewValue()));
                                                enregistrer();
                                        });
                                        coefColumn.setOnEditCommit(event -> {
                                                noteData note = event.getRowValue();
                                                ControleData controleData = note.getControles().get(controle.get("controle"));
                                                controleData.setCoef(Integer.parseInt(event.getNewValue()));
                                                enregistrer();
                                        });
                                        appreciationColumn.setOnEditCommit(event -> {
                                                noteData note = event.getRowValue();
                                                ControleData controleData = note.getControles().get(controle.get("controle"));
                                                controleData.setAppreciation(event.getNewValue());
                                                enregistrer();
                                        });

                                        dateColumn.setOnEditCommit(event -> {
                                                noteData note = event.getRowValue();
                                                ControleData controleData = note.getControles().get(controle.get("controle"));
                                                controleData.setDate(event.getNewValue());
                                                enregistrer();
                                        });


                                        newControleColumn.getColumns().addAll(coefColumn, noteColumn, appreciationColumn , dateColumn);
                                        table.getColumns().add(newControleColumn);
                                        controleColumns.add(newControleColumn);
                                }
                        }
                        for (Map<String, String> etudiant : EtudiantsMap) {
                                noteData newEtudiant = new noteData(etudiant.get("mail"));
                                for (Map<String, String> controle : controlesMap) {
                                        if (controle.get("mail").equals(etudiant.get("mail")) ) {

                                                ControleData newControle = new ControleData(Integer.parseInt(controle.get("coef")), Integer.parseInt(controle.get("note")), controle.get("appreciation"), LocalDate.parse(controle.get("date")) );
                                                newEtudiant.addControle(controle.get("controle"), newControle);
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
                        // Read the existing data from the JSON file
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>(){});

                        // Get the current data from the table
                        ObservableList<noteData> notes = table.getItems();

                        // Prepare the lists for 'Etudiants' and 'controles'
                        List<Map<String, String>> EtudiantsMap = new ArrayList<>();
                        List<Map<String, String>> controlesMap = new ArrayList<>(usersMap.get("controles")); // copy existing controls

                        // Iterate over the notes
                        for (noteData note : notes) {
                                // Add each student to 'EtudiantsMap'
                                Map<String, String> etudiant = new HashMap<>();
                                etudiant.put("mail", note.getMail());
                                EtudiantsMap.add(etudiant);

                                // Add or update each control in 'controlesMap'
                                Map<String, ControleData> controles = note.getControles();
                                for (Map.Entry<String, ControleData> entry : controles.entrySet()) {
                                        Map<String, String> controle = new HashMap<>();
                                        controle.put("mail", note.getMail());
                                        controle.put("date", entry.getValue().getDate().toString());
                                        controle.put("controle", entry.getKey());
                                        controle.put("coef", String.valueOf(entry.getValue().getCoef()));
                                        controle.put("note", String.valueOf(entry.getValue().getNote()));
                                        controle.put("appreciation", entry.getValue().getAppreciation());
                                        controle.put("matiere", Main.matiereProf); // add the current subject

                                        // Find and remove the existing control if it exists
                                        controlesMap.removeIf(existingControle -> existingControle.get("mail").equals(note.getMail()) && existingControle.get("controle").equals(entry.getKey()) && existingControle.get("matiere").equals(Main.matiereProf));

                                        // Add the updated control
                                        controlesMap.add(controle);
                                }

                        }

                        // Update 'Etudiants' and 'controles' in 'usersMap'

                        usersMap.put("controles", controlesMap);

                        // Write all data back to the JSON file
                        mapper.writeValue(new File("src/code/data.json"), usersMap);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }





        @FXML
        void entrer(ActionEvent event) {
                if (inputControle.getText().isEmpty() || inputCoef.getText().isEmpty())  {
                        erreur.setText("Veuillez remplir tous les champs.");
                } else {
                        erreur.setText(""); // Clear the error message if all fields are valid
                        // Create a new control
                        LocalDate date = LocalDate.now(); // get the current date
                        ControleData newControle = new ControleData(Integer.parseInt(inputCoef.getText()), 0, "N/A", date);
                        // Add the new control to the list of controls
                        for (noteData note : table.getItems()) {
                                note.addControle(inputControle.getText(), newControle);
                        }
                        enregistrer();
                        // jouer initialize
                        initialize(null, null);
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
                        boolean controlExists = controlesMap.stream().anyMatch(controle -> controle.get("controle").equals(controleToDelete) && controle.get("matiere").equals(Main.matiereProf));

                        if (!controlExists) {
                                erreur.setText("Il n'y a pas de contrôle avec ce nom à supprimer.");
                                return;
                        }

                        // Remove the controls that match 'controleToDelete' and 'Main.matiereProf'
                        controlesMap.removeIf(controle -> controle.get("controle").equals(controleToDelete) && controle.get("matiere").equals(Main.matiereProf));

                        // Update 'controles' in 'usersMap'
                        usersMap.put("controles", controlesMap);

                        // Write all data back to the JSON file
                        mapper.writeValue(new File("src/code/data.json"), usersMap);

                        // Remove the corresponding column from the table
                        for (TableColumn<noteData, ?> column : new ArrayList<>(table.getColumns())) {
                                if (column.getText().equals(controleToDelete)) {
                                        table.getColumns().remove(column);
                                }
                        }


                        // jouer initialize
                        initialize(null, null);

                        erreur.setText(""); // Clear the error message if all fields are valid
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}







