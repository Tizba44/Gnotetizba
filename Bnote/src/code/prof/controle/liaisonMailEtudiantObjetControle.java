package code.prof.controle;

import javafx.beans.property.SimpleStringProperty;
import java.util.HashMap;
import java.util.Map;

public class liaisonMailEtudiantObjetControle {
    private SimpleStringProperty mailEtudiantsID;
    private Map<String, ControleData> controles;

    public liaisonMailEtudiantObjetControle(String mailEtudiantsID) {
        this.mailEtudiantsID = new SimpleStringProperty(mailEtudiantsID);
        this.controles = new HashMap<>();
    }
    public void addControle(String controleName, ControleData controleData) {
        this.controles.put(controleName, controleData);
    }

    public String getMailEtudiantsID() {
        return mailEtudiantsID.get();
    }

    public Map<String, ControleData> getControles() {
        return controles;
    }
}
