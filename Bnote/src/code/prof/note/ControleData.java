package code.prof.note;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;

public class ControleData {
    private SimpleIntegerProperty coef;
    private SimpleIntegerProperty note;
    private SimpleStringProperty appreciation;
    private SimpleObjectProperty<LocalDate> date;

    private SimpleStringProperty matiere;

    public ControleData(Integer coef, Integer note, String appreciation, LocalDate date, String matiere ) {
        this.coef = new SimpleIntegerProperty(coef);
        this.note = new SimpleIntegerProperty(note);
        this.appreciation = new SimpleStringProperty(appreciation);
        this.date = new SimpleObjectProperty<>(date);
        this.matiere = new SimpleStringProperty(matiere);
    }

    // getters and setters...

    public String getMatiere() {
        return matiere.get();
    }

    public void setMatiere(String matiere) {
        this.matiere.set(matiere);
    }


    public Integer getCoef() {
        return coef.get();
    }

    public void setCoef(Integer coef) {
        this.coef.set(coef);
    }

    public IntegerProperty coefProperty() {
        return coef;
    }

    public Integer getNote() {
        return note.get();
    }

    public void setNote(Integer note) {
        this.note.set(note);
    }

    public IntegerProperty noteProperty() {
        return note;
    }

    public String getAppreciation() {
        return appreciation.get();
    }

    public void setAppreciation(String appreciation) {
        this.appreciation.set(appreciation);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

}
