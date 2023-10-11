package code;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class admin {

    @FXML
    private Button logout;
    @FXML
    private Button mprof;
    @FXML
    private Button  metudiant;
    @FXML
    private Button  mmatiere;
    @FXML
    public void userLogOut(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("connexion.fxml");
    }
    @FXML
    public void usermprof(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("mprof.fxml");
    }
    @FXML
    public void usermetudiant(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("metudiant.fxml"); // changed from "connexion.fxml"
    }
    @FXML
    public void usermatiere(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("mmatiere.fxml"); // changed from "connexion.fxml"
    }
}
