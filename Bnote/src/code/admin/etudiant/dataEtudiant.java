package code.admin.etudiant;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class dataEtudiant {
    private StringProperty mailID;
    private StringProperty nom;
    private StringProperty prenom;
    private StringProperty telephone;


    public dataEtudiant(String mailID, String nom, String prenom, String telephone) {
        this.mailID = new SimpleStringProperty(mailID);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.telephone = new SimpleStringProperty(telephone);
    }


    public String getMailID() {
        return mailID.get();
    }
    public void setMailID(String mailID) {
        this.mailID.set(mailID);
    }

    public String getNom() {
        return nom.get();
    }
    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }
    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public String getTelephone() {
        return telephone.get();
    }
    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

}

