package code.admin.matiere;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class dataMatiere {
    private StringProperty nomUtilisateur;
    private StringProperty matiere;

    public dataMatiere(String nomUtilisateur, String matiere) {
        this.nomUtilisateur = new SimpleStringProperty(nomUtilisateur);
        this.matiere = new SimpleStringProperty(matiere);
    }
    public String getNomUtilisateur() {
        return nomUtilisateur.get();
    }
    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur.set(nomUtilisateur);
    }

    public String getMatiere() {
        return matiere.get();
    }
    public void setMatiere(String matiere) {
        this.matiere.set(matiere);
    }

}

