package code.admin.prof;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class dataProf {
    private StringProperty mailID;
    private StringProperty nom;
    private StringProperty prenom;
    private StringProperty motDePasse;
    private StringProperty telephone;

    public dataProf(String mailID, String nom, String prenom, String motDePasse, String telephone) {
        this.mailID = new SimpleStringProperty(mailID);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.motDePasse = new SimpleStringProperty(motDePasse);
        this.telephone = new SimpleStringProperty(telephone);
    }

    public String getMailID() {
        return mailID.get();
    }
    public void setMailID(String mail) {
        this.mailID.set(mail);
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

    public String getMotDePasse() {
        return motDePasse.get();
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse.set(motDePasse);
    }

    public String getTelephone() {
        return telephone.get();
    }
    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }



}

