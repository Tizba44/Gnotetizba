package code.prof.note;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class noteData {


    private SimpleStringProperty controle;
    private SimpleIntegerProperty coef;
    private SimpleStringProperty nomUtilisateur;
    private SimpleIntegerProperty note;
    private SimpleStringProperty appreciation;

    public noteData(String nomUtilisateur, Integer note, String controle, Integer coef , String appreciation) {
        this.nomUtilisateur = new SimpleStringProperty(nomUtilisateur);
        this.note = new SimpleIntegerProperty(note);
        this.controle = new SimpleStringProperty(controle);
        this.coef = new SimpleIntegerProperty(coef);
        this.appreciation = new SimpleStringProperty(appreciation);
//        this.moyenne = new SimpleIntegerProperty(moyenne);
    }


    public String getNomUtilisateur() {
        return nomUtilisateur.get();
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur.set(nomUtilisateur);
    }

    public Integer getNote() {
        return note.get();
    }

    public void setNote(Integer note) {
        this.note.set(note);
    }

    public String getControle() {
        return controle.get();
    }

    public void setControle(String controle) {
        this.controle.set(controle);
    }

    public Integer getCoef() {
        return coef.get();
    }

    public void setCoef(Integer coef) {
        this.coef.set(coef);
    }

    public String getAppreciation() {
        return appreciation.get();
    }

    public void setAppreciation(String appreciation) {
        this.appreciation.set(appreciation);
    }


}
