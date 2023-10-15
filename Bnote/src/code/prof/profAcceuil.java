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
    public void userLogOut(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("connexion.fxml");
    }

    @FXML
    void usernote(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("prof/note/note.fxml");
    }

    @FXML
    void userstat(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("prof/stat/stat.fxml");
    }


}


