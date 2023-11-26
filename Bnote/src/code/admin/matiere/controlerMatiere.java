package code.admin.matiere;

import code.Main;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import java.util.stream.Collectors;


public class controlerMatiere implements Initializable {

        @FXML
        private Button button;

        @FXML
        public void userRetour(ActionEvent event) throws IOException {
                Main m = new Main();
                m.changeScene("admin/adminAcceuil.fxml");
        }
        @FXML
        private TableView<liaisonProfMailObjetMatiere> table;
        @FXML
        private Label erreur;

        @FXML
        private TableColumn<liaisonProfMailObjetMatiere, String> mail;

        @FXML
        private TextField inputMatiere;

        @FXML
        private TextField inputsuppr;



        public void initialize(URL url, ResourceBundle resourceBundle) {
                mail.setCellValueFactory(new PropertyValueFactory<liaisonProfMailObjetMatiere, String>("mailProfsID"));
                ObjectMapper mapper = new ObjectMapper();
                table.setEditable(true);
                try {
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>(){});
                        List<Map<String, String>> profsMap = usersMap.get("profs");
                        List<Map<String, String>> MatieresMap = usersMap.get("matieres");
                        ObservableList<liaisonProfMailObjetMatiere> notes = FXCollections.observableArrayList();

                        // Créer une liste de toutes les matières uniques
                        Set<String> allMatieres = MatieresMap.stream().map(m -> m.get("nomMatiereID")).collect(Collectors.toSet());

                        for (String matiere : allMatieres) {
                                TableColumn<liaisonProfMailObjetMatiere, String> newMatiereColumn = new TableColumn<>(matiere);
                                newMatiereColumn.setPrefWidth(217.5999755859375);
                                newMatiereColumn.setCellValueFactory(cellData -> {
                                        liaisonProfMailObjetMatiere note = cellData.getValue();
                                        Map<String, MatiereData> Matieres = note.getMatieres();
                                        MatiereData MatiereData = Matieres.get(matiere);
                                        return new SimpleObjectProperty<>(MatiereData != null ? "oui" : "non");
                                });


                                newMatiereColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                                newMatiereColumn.setOnEditCommit(event -> {
                                        liaisonProfMailObjetMatiere note = event.getRowValue();
                                        Map<String, MatiereData> Matieres = note.getMatieres();
                                        if ("oui".equals(event.getNewValue())) {
                                                Matieres.put(matiere, new MatiereData(matiere));
                                        } else {
                                                Matieres.put(matiere, null);
                                        }
                                        enregistrer();
                                });

                                newMatiereColumn.setOnEditCommit(event -> {
                                        liaisonProfMailObjetMatiere note = event.getRowValue();
                                        Map<String, MatiereData> Matieres = note.getMatieres();

                                        String newValue = event.getNewValue();


                                        // Vérifier si la nouvelle valeur est "oui" ou "non"
                                        if ("oui".equals(newValue) || "non".equals(newValue)) {
                                                if ("oui".equals(newValue)) {
                                                        Matieres.put(matiere, new MatiereData(matiere));
                                                } else {
                                                        Matieres.put(matiere, null);
                                                }
                                                enregistrer();
                                        } else {
                                                // Afficher un message d'erreur si la nouvelle valeur n'est ni "oui" ni "non"
                                                erreur.setText("Veuillez entrer oui ou non.");
                                                initialize(null, null);
                                        }
                                });



                                table.getColumns().add(newMatiereColumn);
                        }

                        for (Map<String, String> prof : profsMap) {
                                liaisonProfMailObjetMatiere newprof = new liaisonProfMailObjetMatiere(prof.get("mailID"));
                                // Ajouter une entrée pour chaque matière dans le map 'matieres' du professeur
                                for (String matiere : allMatieres) {
                                        Map<String, String> matiereDetails = MatieresMap.stream()
                                                // Trouver la matière qui correspond au professeur et à la matière actuelle
                                                .filter(m -> m.get("mailProfsID").equals(prof.get("mailID")) && m.get("nomMatiereID").equals(matiere))
                                                .findFirst().orElse(null);
                                        if (matiereDetails != null) {
                                                newprof.addMatiere(matiere, new MatiereData(matiereDetails.get("nomMatiereID")));
                                        } else {
                                                newprof.addMatiere(matiere, null);
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
                        ObservableList<liaisonProfMailObjetMatiere> profs = table.getItems();

                        // Préparer la liste pour 'Matieres'
                        List<Map<String, String>> MatieresMap = new ArrayList<>();

                        // Itérer sur les profs
                        for (liaisonProfMailObjetMatiere prof : profs) {
                                // Ajouter chaque matière à 'MatieresMap'
                                Map<String, MatiereData> Matieres = prof.getMatieres();
                                for (Map.Entry<String, MatiereData> entry : Matieres.entrySet()) {
                                        if (entry.getValue() != null) { // Vérifier si le prof enseigne la matière
                                                Map<String, String> Matiere = new HashMap<>();
                                                Matiere.put("mailProfsID", prof.getMailProfsID());
                                                Matiere.put("nomMatiereID", entry.getKey());
                                                MatieresMap.add(Matiere);
                                        }
                                }
                        }

                        // Supprimer les données existantes de 'Matieres' dans le json
                        usersMap.remove("matieres");

                        // ajouer les nouvelles données à 'Matieres' dans le json
                        usersMap.put("matieres", MatieresMap);

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
                   // regex nom matière
                } else if (!inputMatiere.getText().matches("^[a-zA-Z0-9]*$")) {
                        erreur.setText("Veuillez entrer un nom de matière valide.");
                } else if (table.getSelectionModel().getSelectedItems().isEmpty()) {
                        erreur.setText("Veuillez sélectionner au moins un professeur.");
                } else if (table.getColumns().stream().anyMatch(column -> column.getText().equals(inputMatiere.getText()))) {
                        erreur.setText("Il y a déjà une matière avec ce nom.");
                } else {
                        erreur.setText(""); // Effacer le message d'erreur si tous les champs sont valides
                        // Créer une nouvelle matière
                        MatiereData newMatiere = new MatiereData(inputMatiere.getText());
                        // Ajouter la nouvelle matière aux professeurs sélectionnés
                        for (liaisonProfMailObjetMatiere prof : table.getSelectionModel().getSelectedItems()) {
                                prof.addMatiere(inputMatiere.getText(), newMatiere);
                        }
                        // Créer une nouvelle colonne pour la matière
                        TableColumn<liaisonProfMailObjetMatiere, String> newMatiereColumn = new TableColumn<>(inputMatiere.getText());
                        newMatiereColumn.setPrefWidth(217.5999755859375);
                        newMatiereColumn.setCellValueFactory(cellData -> {
                                liaisonProfMailObjetMatiere note = cellData.getValue();
                                Map<String, MatiereData> Matieres = note.getMatieres();
                                MatiereData MatiereData = Matieres.get(inputMatiere.getText());
                                return new SimpleObjectProperty<>(MatiereData != null ? "oui" : "non");
                        });

                        newMatiereColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                        newMatiereColumn.setOnEditCommit(event1 -> {
                                liaisonProfMailObjetMatiere note = event1.getRowValue();
                                Map<String, MatiereData> Matieres = note.getMatieres();

                                String inputMatiereText = inputMatiere.getText();
                                String newValue = event1.getNewValue();

                                // Vérifier si la nouvelle valeur est "oui" ou "non"
                                if ("oui".equals(newValue) || "non".equals(newValue)) {
                                        if ("oui".equals(newValue)) {
                                                Matieres.put(inputMatiereText, new MatiereData(inputMatiereText));
                                        } else {
                                                Matieres.put(inputMatiereText, null);
                                        }
                                        enregistrer();
                                } else {
                                        // Afficher un message d'erreur si la nouvelle valeur n'est ni "oui" ni "non"
                                        erreur.setText("Veuillez entrer oui ou non.");
                                        initialize(null, null);
                                }
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
                        List<Map<String, String>> MatieresMap = usersMap.get("matieres");

                        // Vérifier si la matière existe
                        boolean matiereExists = MatieresMap.stream().anyMatch(matiere -> matiere.get("nomMatiereID").equals(matiereToDelete));

                        if (!matiereExists) {
                                erreur.setText("Il n'y a pas de matière avec ce nom à supprimer.");
                                return;
                        }

                        // Supprimer les matières qui correspondent à 'matiereToDelete'
                        MatieresMap.removeIf(matiere -> matiere.get("nomMatiereID").equals(matiereToDelete));

                        // Mettre à jour 'Matieres' dans 'usersMap'
                        usersMap.put("matieres", MatieresMap);

                        // Écrire toutes les données dans le fichier JSON
                        mapper.writeValue(new File("src/code/data.json"), usersMap);

                        // Supprimer la colonne correspondante du tableau
                        for (TableColumn<liaisonProfMailObjetMatiere, ?> column : new ArrayList<>(table.getColumns())) {
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







