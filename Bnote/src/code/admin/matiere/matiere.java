package code.admin.matiere;

import code.Main;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.LocalDateStringConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import java.util.stream.Collectors;


public class matiere implements Initializable {

        @FXML
        private Button button;

        @FXML
        public void userRetour(ActionEvent event) throws IOException {
                Main m = new Main();
                m.changeScene("admin/adminAcceuil.fxml");
        }
        @FXML
        private TableView<ProfData> table;
        @FXML
        private Label erreur;

        @FXML
        private TableColumn<ProfData, String> mail;

        @FXML
        private TextField inputCoef;

        @FXML
        private TextField inputMatiere;

        @FXML
        private TextField inputsuppr;



        public void initialize(URL url, ResourceBundle resourceBundle) {
                mail.setCellValueFactory(new PropertyValueFactory<ProfData, String>("mail"));
                ObjectMapper mapper = new ObjectMapper();
                table.setEditable(true);
                try {
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>(){});
                        List<Map<String, String>> profsMap = usersMap.get("profs");
                        List<Map<String, String>> MatieresMap = usersMap.get("Matieres");
                        ObservableList<ProfData> notes = FXCollections.observableArrayList();

                        // Créer une liste de toutes les matières uniques
                        Set<String> allMatieres = MatieresMap.stream().map(m -> m.get("matiere")).collect(Collectors.toSet());

                        for (String matiere : allMatieres) {
                                TableColumn<ProfData, String> newMatiereColumn = new TableColumn<>(matiere);
                                newMatiereColumn.setPrefWidth(217.5999755859375);
                                newMatiereColumn.setCellValueFactory(cellData -> {
                                        ProfData note = cellData.getValue();
                                        Map<String, MatiereData> Matieres = note.getmatieres();
                                        MatiereData MatiereData = Matieres.get(matiere);
                                        return new SimpleObjectProperty<>(MatiereData != null ? "oui" : "non");
                                });


                                newMatiereColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                                newMatiereColumn.setOnEditCommit(event -> {
                                        ProfData note = event.getRowValue();
                                        Map<String, MatiereData> Matieres = note.getmatieres();
                                        if ("oui".equals(event.getNewValue())) {
                                                Matieres.put(matiere, new MatiereData(matiere));
                                        } else {
                                                Matieres.put(matiere, null);
                                        }
                                        enregistrer();
                                });
                                table.getColumns().add(newMatiereColumn);
                        }

                        for (Map<String, String> prof : profsMap) {
                                ProfData newprof = new ProfData(prof.get("mail"));
                                // Ajouter une entrée pour chaque matière dans le map 'matieres' du professeur
                                for (String matiere : allMatieres) {
                                        Map<String, String> matiereDetails = MatieresMap.stream()
                                                .filter(m -> m.get("mail").equals(prof.get("mail")) && m.get("matiere").equals(matiere))
                                                .findFirst().orElse(null);
                                        if (matiereDetails != null) {
                                                newprof.addControle(matiere, new MatiereData(matiereDetails.get("matiere")));
                                        } else {
                                                newprof.addControle(matiere, null);
                                        }
                                }
                                notes.add(newprof);
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

                        // Obtenir les données actuelles du tableau
                        ObservableList<ProfData> profs = table.getItems();

                        // Préparer la liste pour 'Matieres'
                        List<Map<String, String>> MatieresMap = new ArrayList<>();

                        // Itérer sur les profs
                        for (ProfData prof : profs) {
                                // Ajouter chaque matière à 'MatieresMap'
                                Map<String, MatiereData> Matieres = prof.getmatieres();
                                for (Map.Entry<String, MatiereData> entry : Matieres.entrySet()) {
                                        if (entry.getValue() != null) { // Vérifier si le prof enseigne la matière
                                                Map<String, String> Matiere = new HashMap<>();
                                                Matiere.put("mail", prof.getMail());
                                                Matiere.put("matiere", entry.getKey());
                                                MatieresMap.add(Matiere);
                                        }
                                }
                        }

                        // Supprimer les données existantes de 'Matieres' dans le json
                        usersMap.remove("Matieres");

                        // ajouer les nouvelles données à 'Matieres' dans le json
                        usersMap.put("Matieres", MatieresMap);

                        // Écrire toutes les données dans le fichier JSON
                        mapper.writeValue(new File("src/code/data.json"), usersMap);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }











        @FXML
        void entrer(ActionEvent event) {
                if (inputMatiere.getText().isEmpty())  {
                        erreur.setText("Veuillez remplir tous les champs.");
                } else {
                        erreur.setText(""); // Effacer le message d'erreur si tous les champs sont valides
                        // Créer une nouvelle matière
                        MatiereData newMatiere = new MatiereData(inputMatiere.getText());
                        // Ajouter la nouvelle matière aux professeurs sélectionnés
                        for (ProfData prof : table.getSelectionModel().getSelectedItems()) {
                                prof.addControle(inputMatiere.getText(), newMatiere);
                        }
                        // Créer une nouvelle colonne pour la matière
                        TableColumn<ProfData, String> newMatiereColumn = new TableColumn<>(inputMatiere.getText());
                        newMatiereColumn.setPrefWidth(217.5999755859375);
                        newMatiereColumn.setCellValueFactory(cellData -> {
                                ProfData note = cellData.getValue();
                                Map<String, MatiereData> Matieres = note.getmatieres();
                                MatiereData MatiereData = Matieres.get(inputMatiere.getText());
                                return new SimpleObjectProperty<>(MatiereData != null ? "oui" : "non");
                        });

                        newMatiereColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                        newMatiereColumn.setOnEditCommit(event1 -> {
                                ProfData note = event1.getRowValue();
                                Map<String, MatiereData> Matieres = note.getmatieres();
                                Matieres.put(inputMatiere.getText(), new MatiereData(event1.getNewValue()));
                                enregistrer();
                        });
                        table.getColumns().add(newMatiereColumn);




                        enregistrer();

                }
        }





        @FXML
        void supprimer(ActionEvent event) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                        // Lire les données existantes à partir du fichier JSON
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>(){});

                        // Obtenir la matière à supprimer de 'inputsuppr'
                        String matiereToDelete = inputsuppr.getText();

                        // Obtenir la liste des matières
                        List<Map<String, String>> MatieresMap = usersMap.get("Matieres");

                        // Vérifier si la matière existe
                        boolean matiereExists = MatieresMap.stream().anyMatch(matiere -> matiere.get("matiere").equals(matiereToDelete));

                        if (!matiereExists) {
                                erreur.setText("Il n'y a pas de matière avec ce nom à supprimer.");
                                return;
                        }

                        // Supprimer les matières qui correspondent à 'matiereToDelete'
                        MatieresMap.removeIf(matiere -> matiere.get("matiere").equals(matiereToDelete));

                        // Mettre à jour 'Matieres' dans 'usersMap'
                        usersMap.put("Matieres", MatieresMap);

                        // Écrire toutes les données dans le fichier JSON
                        mapper.writeValue(new File("src/code/data.json"), usersMap);

                        // Supprimer la colonne correspondante du tableau
                        for (TableColumn<ProfData, ?> column : new ArrayList<>(table.getColumns())) {
                                if (column.getText().equals(matiereToDelete)) {
                                        table.getColumns().remove(column);
                                }
                        }



                        erreur.setText(""); // Effacer le message d'erreur si tous les champs sont valides
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

}







