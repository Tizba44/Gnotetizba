package code.prof.note;

import javafx.beans.property.SimpleStringProperty;
import java.util.HashMap;
import java.util.Map;

public class noteData {
    private SimpleStringProperty mail;
    private Map<String, ControleData> controles;

    public noteData(String mail) {
            this.mail = new SimpleStringProperty(mail);
        this.controles = new HashMap<>();
    }
    public void addControle(String controleName, ControleData controleData) {
        this.controles.put(controleName, controleData);
    }

    public String getMail() {
        return mail.get();
    }

    public void setMail(String mail) {
        this.mail.set(mail);
    }

    public Map<String, ControleData> getControles() {
        return controles;
    }
}
