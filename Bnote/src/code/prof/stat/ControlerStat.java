package code.prof.stat;

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
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.ResourceBundle;






import java.util.*;


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
                mail.setCellValueFactory(cellData -> cellData.getValue().mailEtudiantsIDProperty());
                moyenneGenerale.setCellValueFactory(cellData -> cellData.getValue().moyenneGeneraleProperty());
                mail.setCellFactory(TextFieldTableCell.forTableColumn());
                moyenneGenerale.setCellFactory(TextFieldTableCell.forTableColumn());



                // Read JSON file and populate TableView
                ObjectMapper mapper = new ObjectMapper();
                try {
                        Map<String, List<Map<String, String>>> usersMap = mapper.readValue( new File(    "C:/Users/bapto/OneDrive/Bureau/Gnotetizba/rest/src/main/resources/moyenne.json"), new TypeReference<Map<String, List<Map<String, String>>>>() {});
                        List<Map<String, String>> moyenneGeneraleMap = usersMap.get("moyenneGenerale");
                        ObservableList<liaisonMailEtudiantObjetControle> liaisonMailEtudiantObjetControles = FXCollections.observableArrayList();
                        for (Map<String, String> map : moyenneGeneraleMap) {
                                liaisonMailEtudiantObjetControle liaisonMailEtudiantObjetControle = new liaisonMailEtudiantObjetControle(
                                        map.get("mailEtudiantsID"),
                                        map.get("note")
                                );
                                liaisonMailEtudiantObjetControles.add(liaisonMailEtudiantObjetControle);
                        }
                        table.setItems(liaisonMailEtudiantObjetControles);
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




