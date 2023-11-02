package code.admin.matiere;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class MatiereData {

    private SimpleStringProperty Matiere;


    public MatiereData( String Matiere) {
        this.Matiere = new SimpleStringProperty(Matiere);
    }

    public String getMatiere() {
        return Matiere.get();
    }

    public void setMatiere(String Matiere) {
        this.Matiere.set(Matiere);
    }



}
