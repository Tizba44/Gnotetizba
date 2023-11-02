package code.admin.matiere;

import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;
import java.util.Map;

public class ProfData {
    private SimpleStringProperty mail;
    private Map<String, MatiereData> matieres;

    public ProfData(String mail) {
            this.mail = new SimpleStringProperty(mail);
        this.matieres = new HashMap<>();
    }
    public void addControle(String controleName, MatiereData controleData) {
        this.matieres.put(controleName, controleData);
    }

    public String getMail() {
        return mail.get();
    }

    public void setMail(String mail) {
        this.mail.set(mail);
    }

    public Map<String, MatiereData> getmatieres() {
        return matieres;
    }
}
