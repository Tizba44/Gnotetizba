package code.admin.matiere;

import javafx.beans.property.SimpleStringProperty;


public class MatiereData {
    private SimpleStringProperty Matiere;
    public MatiereData( String Matiere) {
        this.Matiere = new SimpleStringProperty(Matiere);
    }

}
