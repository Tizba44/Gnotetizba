package code.admin.matiere;

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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class modifierMatiere implements Initializable {

        @FXML
        private Button button;
        @FXML
        public void userRetour(ActionEvent event) throws IOException {
                Main m = new Main();
                m.changeScene("admin/adminAcceuil.fxml");
        }
        @FXML
        private TableView<dataMatiere> table;


        @FXML
        private TableColumn<dataMatiere, String> mail;

        @FXML
        private TableColumn<dataMatiere, String> matiere;




        @FXML
        private Label erreur;


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                mail.setCellValueFactory(new PropertyValueFactory<dataMatiere,String >("mail"));
                matiere.setCellValueFactory(new PropertyValueFactory<dataMatiere,String >("matiere"));


                table.setEditable(true); // Ajoutez cette ligne pour rendre le TableView éditable


                mail.setCellFactory(TextFieldTableCell.forTableColumn());
                mail.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataMatiere, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataMatiere, String> t) {
                                        ((dataMatiere) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setMail(t.getNewValue());
                                        enregistrer();
                                }
                        }
                );

                matiere.setCellFactory(TextFieldTableCell.forTableColumn());
                matiere.setOnEditCommit(
                        new EventHandler<CellEditEvent<dataMatiere, String>>() {
                                @Override
                                public void handle(CellEditEvent<dataMatiere, String> t) {
                                        ((dataMatiere) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setMatiere(t.getNewValue());
                                        enregistrer();
                                }
                        }
                );


                // Read JSON file and populate TableView
                ObjectMapper mapper = new ObjectMapper();
                try {
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue( new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>() {});
                        List<Map<String, String>> MatieresMap = usersMap.get("Matieres");
                        ObservableList<dataMatiere> dataMatieres = FXCollections.observableArrayList();
                        for (Map<String, String> map : MatieresMap) {
                                dataMatiere Matiere = new dataMatiere(
                                        map.get("mail"),
                                        map.get("matiere")
                                );
                                dataMatieres.add(Matiere);
                        }
                        table.setItems(dataMatieres);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }


        @FXML
        private TextField mailInput;

        @FXML
        private TextField matiereInput;

        public void enregistrer() {
                // Créer un ObjectMapper
                ObjectMapper mapper = new ObjectMapper();
                try {
                        // Lire le fichier JSON existant
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>() {});

                        // Convertir la liste de dataMatiere en liste de MapgetClass().getResource("data.json")
                        List<Map<String, String>> newMatieresMap = new ArrayList<>();
                        for (dataMatiere Matiere : table.getItems()) { // Use table.getItems() instead of Matieres
                                Map<String, String> map = new HashMap<>();
                                map.put("mail", Matiere.getMail());
                                map.put("matiere", Matiere.getMatiere());
                                newMatieresMap.add(map);
                        }

                        // Update the 'Matieres' list in usersMap and write it back to the JSON file
                        usersMap.put("Matieres", newMatieresMap);

                        mapper.writeValue(new File("src/code/data.json"), usersMap);


                } catch (IOException e) {
                        e.printStackTrace();
                }
        }




        @FXML
        void entrer(ActionEvent event) {
                if (matiereInput.getText().isEmpty() ||  mailInput.getText().isEmpty())  {
                        erreur.setText("Veuillez remplir tous les champs.");

                } else {
                erreur.setText("");
                dataMatiere dataMatiere = new dataMatiere(
                        mailInput.getText(),
                        matiereInput.getText()
                );
                ObservableList<dataMatiere> dataMatieres = table.getItems();
                dataMatieres.add(dataMatiere);
                table.setItems(dataMatieres);
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







