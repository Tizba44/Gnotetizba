package code.admin.etudiant;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class dataEtudiant {
    private StringProperty mail;
    private StringProperty nom;
    private StringProperty nomUtilisateur;
    private StringProperty prenom;
    private StringProperty numero;


    public dataEtudiant(String mail, String nom, String nomUtilisateur, String prenom, String numero) {
        this.mail = new SimpleStringProperty(mail);
        this.nom = new SimpleStringProperty(nom);
        this.nomUtilisateur = new SimpleStringProperty(nomUtilisateur);
        this.prenom = new SimpleStringProperty(prenom);
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

    public String getNumero() {
        return numero.get();
    }
    public void setNumero(String numero) {
        this.numero.set(numero);
    }

}

