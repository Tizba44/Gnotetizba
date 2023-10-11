package code;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class mprof {

    @FXML
    private Button retour;

    @FXML
    private TextField nameField, prenomField, usernameField, passwordField, emailField;

    @FXML
    public void userRetour(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("admin.fxml");
    }

    @FXML
    public void addProf(ActionEvent event) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Read the existing data
        Map<String, List<Map<String, String>>> data = mapper.readValue(Files.readAllBytes(Paths.get("data.json")), Map.class);

        // Create a new prof
        Map<String, String> newProf = Map.of(
                "name", nameField.getText(),
                "prenom", prenomField.getText(),
                "username", usernameField.getText(),
                "password", passwordField.getText(),
                "email", emailField.getText()
        );

        // Add the new prof to the list
        data.get("profs").add(newProf);

        // Write the updated data back to the file
        Files.write(Paths.get("data.json"), mapper.writeValueAsBytes(data));


    }
}
