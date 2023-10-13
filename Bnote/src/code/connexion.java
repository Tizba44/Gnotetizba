package code;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.Map;

public class connexion {


    @FXML
    private Button button;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        Main m = new Main();
        // Créer un ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Lire le fichier JSON
            Map<String, List<Map<String, String>>> usersMap = mapper.readValue(getClass().getResource("data.json"), new TypeReference<Map<String, List<Map<String, String>>>>() {});

            // Obtenir les listes d'utilisateurs
            List<Map<String, String>> admins = usersMap.get("admins");
            List<Map<String, String>> profs = usersMap.get("profs");

            // Vérifier les informations d'identification de l'administrateur
            for (Map<String, String> user : admins) {
                if (username.getText().equals(user.get("username")) && password.getText().equals(user.get("password"))) {
                    wrongLogIn.setText("Bienvenue Chef!");
                    m.changeScene("adminAcceuil.fxml");
                    return;
                }
            }

            // Vérifier les informations d'identification des professeurs
            for (Map<String, String> user : profs) {
                if (username.getText().equals(user.get("nomUtilisateur")) && password.getText().equals(user.get("mot"))) {
                    wrongLogIn.setText("Bienvenue Professeur!");
                    m.changeScene("profAcceuil.fxml");
                    return;
                }
            }

            // Si aucune correspondance n'a été trouvée
            if (username.getText().isEmpty() && password.getText().isEmpty()) {
                wrongLogIn.setText("Veuillez entrer vos identifiants");
            } else {
                wrongLogIn.setText("mauvais nom d'utilisateur ou mot de passe");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}