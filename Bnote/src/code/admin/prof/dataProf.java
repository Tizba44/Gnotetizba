package code.admin.prof;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class dataProf {
    private StringProperty mail;
    private StringProperty nom;
    private StringProperty prenom;
    private StringProperty motDePasse;
    private StringProperty numero;




    public dataProf(String mail, String nom, String prenom, String motDePasse , String numero ) {
        this.mail = new SimpleStringProperty(mail);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this .motDePasse = new SimpleStringProperty(motDePasse);
        this .numero = new SimpleStringProperty(numero);


    }

    public String getMail() {
        return mail.get();
    }
    public void setMail(String mail) {
        this.mail.set(mail);
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

    public String getNumero() {
        return numero.get();
    }
    public void setNumero(String numero) {
        this.numero.set(numero);
    }



}

