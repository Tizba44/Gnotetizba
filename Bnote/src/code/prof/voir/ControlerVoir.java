package code.prof.voir;

import code.Main;
import code.prof.voir.voirData;
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




public class ControlerVoir implements Initializable {


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
        private TableColumn<liaisonMailEtudiantObjetControle, String> mail;


        @FXML
        private Label labelMatiere;


        
        @FXML
        private ChoiceBox<String> choiceBoxMatiere;


        
        @FXML
        public void initialize(URL url, ResourceBundle resourceBundle) {

                // Remplir la ChoiceBox avec toutes les matières
                if (choiceBoxMatiere.getItems().isEmpty()) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                                Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>(){});
                                List<Map<String, String>> EtudiantsMap = usersMap.get("matieres");
                                List<String> matieres = new ArrayList<>();
                                // recup toute les nomMatiereID
                                for (Map<String, String> matiere : EtudiantsMap) {
                                        matieres.add(matiere.get("nomMatiereID"));

                                }




                                choiceBoxMatiere.getItems().addAll(matieres);

                        } catch (IOException e) {
                                e.printStackTrace();
                        }
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
                                if (!column.getText().equals("mail")) {
                                        table.getColumns().remove(column);
                                }
                        }
                        tableaux();
                        //changer le label de la matière
                        labelMatiere.setText("Matière : " + choiceBoxMatiere.getValue());
                });
                labelMatiere.setText("Matière : " + choiceBoxMatiere.getValue());
        }





        public void tableaux() {
                mail.setCellValueFactory(new PropertyValueFactory<liaisonMailEtudiantObjetControle, String>("mailEtudiantsID"));
                ObjectMapper mapper = new ObjectMapper();

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
                                        newControleColumn.getColumns().addAll(noteColumn, coefColumn,  appreciationColumn , dateColumn);
                                        table.getColumns().add(newControleColumn);
                                        controleColumns.add(newControleColumn);

                                        noteColumn.setCellValueFactory(cellData -> {
                                                code.prof.voir.liaisonMailEtudiantObjetControle note = cellData.getValue();
                                                voirData voirData = note.getControles().get(controle.get("intituleID"));
                                                return new SimpleStringProperty (voirData != null ? String.valueOf(voirData.getNote()) : "N/A");
                                        });

                                        coefColumn.setCellValueFactory(cellData -> {
                                                code.prof.voir.liaisonMailEtudiantObjetControle note = cellData.getValue();
                                                voirData voirData = note.getControles().get(controle.get("intituleID"));
                                                return new SimpleStringProperty(voirData != null ? String.valueOf(voirData.getCoef()) : "N/A");
                                        });

                                        appreciationColumn.setCellValueFactory(cellData -> {
                                                code.prof.voir.liaisonMailEtudiantObjetControle note = cellData.getValue();
                                                voirData voirData = note.getControles().get(controle.get("intituleID"));
                                                return new SimpleStringProperty(voirData != null ? voirData.getAppreciation() : "N/A");
                                        });
                                        dateColumn.setCellValueFactory(cellData -> {
                                                code.prof.voir.liaisonMailEtudiantObjetControle note = cellData.getValue();
                                                voirData voirData = note.getControles().get(controle.get("intituleID"));
                                                return new SimpleObjectProperty<>(voirData != null ? voirData.getDate() : null);
                                        });
                                }
                        }



                        for (Map<String, String> etudiant : EtudiantsMap) {
                                liaisonMailEtudiantObjetControle newEtudiant = new liaisonMailEtudiantObjetControle(etudiant.get("mailID"));
                                for (Map<String, String> controle : controlesMap) {
                                        if (controle.get("mailEtudiantsID").equals(etudiant.get("mailID")) ) {

                                                voirData newControle = new voirData(Integer.parseInt(controle.get("note")), Integer.parseInt(controle.get("coef")), controle.get("appreciation"), LocalDate.parse(controle.get("date")) , controle.get("matiereID") );
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


}










