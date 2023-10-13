package code.admin.prof;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class dataProf {
    private StringProperty mail;
    private StringProperty nom;
    private StringProperty nomUtilisateur;
    private StringProperty prenom;
    private StringProperty mot;
    private StringProperty numero;
    private StringProperty matiere;



    public dataProf(String mail, String nom, String nomUtilisateur, String prenom, String mot , String numero , String matiere) {
        this.mail = new SimpleStringProperty(mail);
        this.nom = new SimpleStringProperty(nom);
        this.nomUtilisateur = new SimpleStringProperty(nomUtilisateur);
        this.prenom = new SimpleStringProperty(prenom);
        this .mot = new SimpleStringProperty(mot);
        this .numero = new SimpleStringProperty(numero);
        this .matiere = new SimpleStringProperty(matiere);

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

    public String getNomUtilisateur() {
        return nomUtilisateur.get();
    }
    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur.set(nomUtilisateur);
    }

    public String getPrenom() {
        return prenom.get();
    }
    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public String getMot() {
        return mot.get();
    }
    public void setMot(String mot) {
        this.mot.set(mot);
    }

    public String getNumero() {
        return numero.get();
    }
    public void setNumero(String numero) {
        this.numero.set(numero);
    }

    public String getMatiere() {
        return matiere.get();
    }
    public void setMatiere(String matiere) {
        this.matiere.set(matiere);
    }

}

