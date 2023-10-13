package code.admin;
import code.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class adminAcceuil {

    @FXML
    private Button logout;
    @FXML
    private Button modifierProf;

    @FXML
    private Button  modifierEtudiant;
    @FXML
    public void userLogOut(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("connexion.fxml");
    }
    @FXML
    public void usermprof(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("admin/prof/modifierProf.fxml");
    }
    @FXML
    public void usermetudiant(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("admin/etudiant/modifierEtudiant.fxml");
    }

}
