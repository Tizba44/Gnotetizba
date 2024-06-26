package code.admin;
import code.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class adminAcceuil {

    @FXML
    private Button logout;
    @FXML
    private Button modifierProf;
    @FXML
    private Button  modifierEtudiant;
    @FXML
    private Button  modifierMatiere;

    // jouer la class public RequeteAPIrest


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

    @FXML
    public void usermmatiere(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("admin/matiere/matiere.fxml");
    }

}
