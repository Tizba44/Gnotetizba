package code.prof.stat;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;


class liaisonMailEtudiantObjetControle {
    private final StringProperty mailEtudiantsID;
    private final StringProperty moyenneGenerale;

    public liaisonMailEtudiantObjetControle(String mailEtudiantsID, String moyenneGenerale) {
        this.mailEtudiantsID = new SimpleStringProperty(mailEtudiantsID);
        this.moyenneGenerale = new SimpleStringProperty(moyenneGenerale);
    }

    public String getMailEtudiantsID() {
        return mailEtudiantsID.get();
    }

    public void setMailEtudiantsID(String mailEtudiantsID) {
        this.mailEtudiantsID.set(mailEtudiantsID);
    }

    public StringProperty mailEtudiantsIDProperty() {
        return mailEtudiantsID;
    }

    public String getMoyenneGenerale() {
        return moyenneGenerale.get();
    }

    public void setMoyenneGenerale(String moyenneGenerale) {
        this.moyenneGenerale.set(moyenneGenerale);
    }

    public StringProperty moyenneGeneraleProperty() {
        return moyenneGenerale;
    }
}