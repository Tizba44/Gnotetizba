package code.admin.matiere;

import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;
import java.util.Map;

public class liaisonProfMailObjetMatiere {
    private SimpleStringProperty mailProfs;
    private Map<String, MatiereData> matieres;

    public liaisonProfMailObjetMatiere(String mailProfs) {
        this.mailProfs = new SimpleStringProperty(mailProfs);
        this.matieres = new HashMap<>();
    }
    public void addMatiere(String matiereName, MatiereData matiereData) {
        this.matieres.put(matiereName, matiereData);
    }
    public String getMailProfs() {
        return mailProfs.get();
    }



    public Map<String, MatiereData> getMatieres() {
        return matieres;
    }

}
