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

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;


public class connexion  {


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
            // Vérifier les informations d'identification de l'administrateur
            for (Map<String, String> user : getAdmins(mapper)) {
                if (username.getText().equals(user.get("username")) && password.getText().equals(user.get("password"))) {
                    wrongLogIn.setText("Bienvenue Chef!");
                    m.changeScene("admin/adminAcceuil.fxml");
                    return;
                }
            }

            // Vérifier les informations d'identification des professeurs
            for (Map<String, String> user : getProfs(mapper)) {
                if (username.getText().equals(user.get("nomUtilisateur")) && password.getText().equals(user.get("motDePasse"))) {
                    wrongLogIn.setText("Bienvenue Professeur!");
                    // Stocker la matière du professeur
                    for (Map<String, String> matiere : getMatieres(mapper)) {
                        if (user.get("nomUtilisateur").equals(matiere.get("nomUtilisateur"))) {
                            Main.matiereProf = matiere.get("matiere");
                            break;
                        }
                    }
                    m.changeScene("prof/profAcceuil.fxml");
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

    private List<Map<String, String>> getAdmins(ObjectMapper mapper) throws IOException {
        return getUsersMap(mapper).get("admins");
    }

    private List<Map<String, String>> getProfs(ObjectMapper mapper) throws IOException {
        return getUsersMap(mapper).get("profs");
    }

    private List<Map<String, String>> getMatieres(ObjectMapper mapper) throws IOException {
        return getUsersMap(mapper).get("Matieres");
    }

    private Map<String, List<Map<String, String>>> getUsersMap(ObjectMapper mapper) throws IOException {
        // Lire le fichier JSON
        return mapper.readValue(getClass().getResource("data.json"), new TypeReference<Map<String, List<Map<String, String>>>>() {});
    }
}