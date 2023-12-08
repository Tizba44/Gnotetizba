package code.prof.stat;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;


import java.util.HashMap;
import java.util.Map;


class liaisonMailEtudiantObjetControle {
    private final StringProperty mailEtudiantsID;
    private final StringProperty moyenneGenerale;
    private Map<String, StringProperty> moyennesParMatiere;

    public liaisonMailEtudiantObjetControle(String mailEtudiantsID, String moyenneGenerale) {
        this.mailEtudiantsID = new SimpleStringProperty(mailEtudiantsID);
        this.moyenneGenerale = new SimpleStringProperty(moyenneGenerale);
        this.moyennesParMatiere = new HashMap<>();
    }

    public StringProperty getmailEtudiantsIDProperty() {
        return mailEtudiantsID;
    }

    public StringProperty getmoyenneGeneraleProperty() {
        return moyenneGenerale;
    }

    public StringProperty getmoyenneParMatiereProperty(String matiereID) {
        return moyennesParMatiere.get(matiereID);
    }

    public void setmoyenneParMatiere(String matiereID, String moyenneParMatiere) {
        this.moyennesParMatiere.put(matiereID, new SimpleStringProperty(moyenneParMatiere));
    }
}

