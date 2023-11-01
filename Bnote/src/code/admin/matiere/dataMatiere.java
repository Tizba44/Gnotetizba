package code.admin.matiere;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class dataMatiere {
    private StringProperty mail;
    private StringProperty matiere;

    public dataMatiere(String mail, String matiere) {
        this.mail = new SimpleStringProperty(mail);
        this.matiere = new SimpleStringProperty(matiere);
    }
    public String getMail() {
        return mail.get();
    }
    public void setMail(String mail) {
        this.mail.set(mail);
    }

    public String getMatiere() {
        return matiere.get();
    }
    public void setMatiere(String matiere) {
        this.matiere.set(matiere);
    }

}

