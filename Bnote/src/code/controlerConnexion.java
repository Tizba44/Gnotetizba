package code;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;



public class controlerConnexion {

    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField mail;
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
                if (mail.getText().equals(user.get("adminMailID")) && password.getText().equals(user.get("adminPassword"))) {
                    wrongLogIn.setText("Bienvenue Chef!");
                    m.changeScene("admin/adminAcceuil.fxml");
                    return;
                }
            }

            // Vérifier les informations d'identification des professeurs
            for (Map<String, String> user : getProfs(mapper)) {
                if (mail.getText().equals(user.get("mailID")) && password.getText().equals(user.get("motDePasse"))) {
                    // Stocker la matière du professeur
                    List<String> matieresProf = new ArrayList<>();
                    for (Map<String, String> matiere : getMatieres(mapper)) {
                        if (user.get("mailID").equals(matiere.get("mailProfs"))) {
                            matieresProf.add(matiere.get("nomMatiereID"));
                        }
                    }
                    // Si aucune matière n'est associée au professeur, bloquer la connexion
                    if (matieresProf.isEmpty()) {
                        wrongLogIn.setText("Aucune matière n'est associée à votre compte. Veuillez contacter l'administrateur.");
                        return;
                    }
                    Main.matieresProf = matieresProf.toArray(new String[0]);
                    wrongLogIn.setText("Bienvenue Professeur!");
                    m.changeScene("prof/profAcceuil.fxml");
                    return;
                }
            }


            // Si aucune correspondance n'a été trouvée
            if (mail.getText().isEmpty() && password.getText().isEmpty()) {
                wrongLogIn.setText("Veuillez entrer vos identifiants");
            } else {
                wrongLogIn.setText("mauvais mail ou mot de passe");
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
        return getUsersMap(mapper).get("matieres");
    }

    private Map<String, List<Map<String, String>>> getUsersMap(ObjectMapper mapper) throws IOException {
        // Lire le fichier JSON à chaque fois pour obtenir les données les plus récentes
        return mapper.readValue(getClass().getResource("data.json"), new TypeReference<Map<String, List<Map<String, String>>>>() {});
    }
}
