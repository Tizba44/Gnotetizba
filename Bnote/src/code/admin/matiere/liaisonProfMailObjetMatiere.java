package code.admin.matiere;

import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;
import java.util.Map;

public class liaisonProfMailObjetMatiere {
    private SimpleStringProperty mailProfsID;
    private Map<String, MatiereData> matieres;

    public liaisonProfMailObjetMatiere(String mailProfsID) {
        this.mailProfsID = new SimpleStringProperty(mailProfsID);
        this.matieres = new HashMap<>();
    }
    public void addMatiere(String matiereName, MatiereData matiereData) {
        this.matieres.put(matiereName, matiereData);
    }
    public String getMailProfsID() {
        return mailProfsID.get();
    }



    public Map<String, MatiereData> getMatieres() {
        return matieres;
    }

}
