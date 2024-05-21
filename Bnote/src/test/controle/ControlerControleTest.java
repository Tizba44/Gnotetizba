package test.controle;



import static org.mockito.Mockito.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import  code.prof.controle.ControlerControle;
import code.prof.controle.liaisonMailEtudiantObjetControle;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;


import org.junit.jupiter.api.BeforeAll;

import javafx.embed.swing.JFXPanel;
import org.mockito.MockitoAnnotations;

class ControlerControleTest {

    private ControlerControle controller;
    private TextField inputControle;
    private TextField inputCoef;
    private Label erreur;
    private ChoiceBox<String> choiceBoxMatiere;
    private TableView<liaisonMailEtudiantObjetControle> table;

    @BeforeAll
    static void setupClass() {
        // This line initializes the JavaFX toolkit.
        new JFXPanel();
    }
    @BeforeEach
    void setUp() {
        // Initialize mocks if using annotations
        MockitoAnnotations.openMocks(this);

        controller = new ControlerControle();
        inputControle = mock(TextField.class);
        inputCoef = mock(TextField.class);
        erreur = mock(Label.class);
        choiceBoxMatiere = mock(ChoiceBox.class);
        table = mock(TableView.class);

        // Correctly stub methods on mocks
        when(table.getColumns()).thenReturn(mock(ObservableList.class));
        // Add other stubbings here

        // Setup other parts of the test environment
        controller.inputControle = inputControle;
        controller.inputCoef = inputCoef;
        controller.erreur = erreur;
        controller.choiceBoxMatiere = choiceBoxMatiere;
        controller.table = table;

        when(table.getColumns()).thenReturn(mock(ObservableList.class));
    }

    @Test
    void testEntrerValidData() {
        // Set up valid test data
        when(inputControle.getText()).thenReturn("");
        when(inputCoef.getText()).thenReturn("5");
        when(choiceBoxMatiere.getValue()).thenReturn("ValidMatiere");


        // Call the method under test
        controller.entrer(null);

        // Verify the expected results
        verify(erreur).setText(""); // Expect no error message for valid input
        // Add more verifications as needed
    }

    // Add more test methods for different scenarios (e.g., invalid inputs, existing control name, etc.)
}