package code.prof;

import code.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;




import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class profAcceuil {

    @FXML
    private Button logout;

    @FXML
    private Button note;

    @FXML
    private Button stat;

    @FXML
    private Button voir;

    @FXML
    public void userLogOut(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("connexion.fxml");
    }

    @FXML
    public void usernote(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("prof/controle/controle.fxml");
    }

    @FXML
    public void userstat(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("prof/stat/stat.fxml");
    }

    @FXML
    public void uservoir(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("prof/voir/voir.fxml");
    }



}


