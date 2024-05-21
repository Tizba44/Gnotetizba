package code.prof.voir;

import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;
import java.util.Map;

public class liaisonMailEtudiantObjetVoir {
    private SimpleStringProperty mailEtudiantsID;
    private Map<String, voirData> controles;

    public liaisonMailEtudiantObjetVoir(String mailEtudiantsID) {
        this.mailEtudiantsID = new SimpleStringProperty(mailEtudiantsID);
        this.controles = new HashMap<>();
    }
    public void addControle(String controleName, voirData controleData) {
        this.controles.put(controleName, controleData);
    }

    public String getMailEtudiantsID() {
        return mailEtudiantsID.get();
    }

    public Map<String, voirData> getControles() {
        return controles;
    }
}
