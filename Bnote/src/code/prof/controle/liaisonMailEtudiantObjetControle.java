package code.prof.controle;

import javafx.beans.property.SimpleStringProperty;
import java.util.HashMap;
import java.util.Map;

public class liaisonMailEtudiantObjetControle {
    private SimpleStringProperty mailEtudiants;
    private Map<String, ControleData> controles;

    public liaisonMailEtudiantObjetControle(String mailEtudiants) {
        this.mailEtudiants = new SimpleStringProperty(mailEtudiants);
        this.controles = new HashMap<>();
    }
    public void addControle(String controleName, ControleData controleData) {
        this.controles.put(controleName, controleData);
    }

    public String getMailEtudiants() {
        return mailEtudiants.get();
    }

    public Map<String, ControleData> getControles() {
        return controles;
    }
}
