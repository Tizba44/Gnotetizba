package code.prof.note;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.HashMap;
import java.util.Map;


public class noteData {
        private SimpleStringProperty nomUtilisateur;
        private Map<String, ControleData> controles;


        public noteData(String nomUtilisateur) {
            this.nomUtilisateur = new SimpleStringProperty(nomUtilisateur);
            this.controles = new HashMap<>();
        }
        public void addControle(String controleName, ControleData controleData) {
            this.controles.put(controleName, controleData);
        }

        // getters and setters...

        public String getNomUtilisateur() {
            return nomUtilisateur.get();
        }

        public void setNomUtilisateur(String nomUtilisateur) {
            this.nomUtilisateur.set(nomUtilisateur);
        }

//        public StringProperty nomUtilisateurProperty() {
//            return nomUtilisateur;
//        }

        public Map<String, ControleData> getControles() {
            return controles;
        }

//        public void setControles(Map<String, ControleData> controles) {
//            this.controles = controles;
//        }


    }
