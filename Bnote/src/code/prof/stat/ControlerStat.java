package code.prof.stat;

import code.Main;
import code.admin.etudiant.dataEtudiant;
import code.admin.matiere.MatiereData;
import code.admin.matiere.liaisonProfMailObjetMatiere;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.ResourceBundle;






import java.util.*;
import java.util.stream.Collectors;


public class ControlerStat implements Initializable {

        @FXML
        private Button button;

        @FXML
        private TableView<liaisonMailEtudiantObjetControle> table;

        @FXML
        private TableColumn<liaisonMailEtudiantObjetControle, String> mail;

        @FXML
        private TableColumn<liaisonMailEtudiantObjetControle, String> moyenneGenerale;


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                mail.setCellValueFactory(cellData -> cellData.getValue().getmailEtudiantsIDProperty());
                moyenneGenerale.setCellValueFactory(cellData -> cellData.getValue().getmoyenneGeneraleProperty());
                mail.setCellFactory(TextFieldTableCell.forTableColumn());
                moyenneGenerale.setCellFactory(TextFieldTableCell.forTableColumn());

                ObjectMapper mapper = new ObjectMapper();
                try {
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("C:/Users/bapto/OneDrive/Bureau/Gnotetizba/rest/src/main/resources/moyenne.json"), new TypeReference<Map<String, List<Map<String, String>>>>() {});
                        List<Map<String, String>> moyenneParMatiereMap = usersMap.get("moyenneParMatiere");
                        List<Map<String, String>> moyenneGeneraleMap = usersMap.get("moyenneGenerale");

                        Map<String, liaisonMailEtudiantObjetControle> liaisonMailEtudiantObjetControlesMap = new HashMap<>();

                        for (Map<String, String> map : moyenneGeneraleMap) {
                                liaisonMailEtudiantObjetControle liaisonMailEtudiantObjetControle = new liaisonMailEtudiantObjetControle(
                                        map.get("mailEtudiantsID"),
                                        map.get("note")
                                );
                                liaisonMailEtudiantObjetControlesMap.put(map.get("mailEtudiantsID"), liaisonMailEtudiantObjetControle);
                        }

                        Map<String, TableColumn<liaisonMailEtudiantObjetControle, String>> matiereColumns = new HashMap<>();

                        for (Map<String, String> map : moyenneParMatiereMap) {
                                liaisonMailEtudiantObjetControle item = liaisonMailEtudiantObjetControlesMap.get(map.get("mailEtudiantsID"));
                                if (item != null) {
                                        item.setmoyenneParMatiere(map.get("matiereID"), map.get("note"));

                                        // Check if the TableColumn for this matiereID already exists
                                        if (!matiereColumns.containsKey(map.get("matiereID"))) {
                                                TableColumn<liaisonMailEtudiantObjetControle, String> moyenneParMatiereColumn = new TableColumn<>(map.get("matiereID"));
                                                moyenneParMatiereColumn.setCellValueFactory(cellData -> cellData.getValue().getmoyenneParMatiereProperty(map.get("matiereID")));
                                                moyenneParMatiereColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                                                matiereColumns.put(map.get("matiereID"), moyenneParMatiereColumn);
                                                table.getColumns().add(moyenneParMatiereColumn);
                                        }
                                }
                        }



                        table.setItems(FXCollections.observableArrayList(liaisonMailEtudiantObjetControlesMap.values()));

                } catch (IOException e) {
                        e.printStackTrace();
                }
        }


        @FXML
        public void userRetour(ActionEvent event) throws IOException {
                Main m = new Main();
                m.changeScene("prof/profAcceuil.fxml");
        }
}




