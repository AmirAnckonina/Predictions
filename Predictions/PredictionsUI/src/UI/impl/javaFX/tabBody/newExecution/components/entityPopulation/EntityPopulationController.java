package UI.impl.javaFX.tabBody.newExecution.components.entityPopulation;

import UI.impl.javaFX.tabBody.newExecution.model.KeyToIntegerData;
import UI.impl.javaFX.tabBody.newExecution.newExecutionMain.NewExecutionController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

import java.awt.event.InputMethodEvent;

public class EntityPopulationController extends KeyToIntegerData {
    private NewExecutionController newExecutionController;

    @FXML private Label entityNameLabel;
    @FXML private TextField populationTextField;
    @FXML private Label typeLabel;
    @FXML private CheckBox setCheckbox;
    @FXML private Label statusLabel;
    @FXML private Button setButton;


    public EntityPopulationController() {
        super();
    }

    @FXML
    private void initialize() {
        setCheckbox.selectedProperty().bindBidirectional(this.checkboxProperty);
        entityNameLabel.textProperty().bindBidirectional(keyNameProperty);
        populationTextField.textProperty().bindBidirectional(this.integerValueProperty, new NumberStringConverter());
        typeLabel.textProperty().bindBidirectional(this.typeProperty);
        statusLabel.textProperty().bindBidirectional(this.statusProperty);
    }
    @FXML
    void OnSetButtonClicked() {
        this.newExecutionController.setSingleEntityPopulation(keyNameProperty.get(), integerValueProperty.getValue());
    }

    @FXML
    void onSetCheckbox() {
        boolean checked = this.checkboxProperty.get();
        if (!checked) {
            this.populationTextField.setDisable(true);
            this.setButton.setDisable(true);
            this.newExecutionController.rollbackSingleEntityPopulation(this.keyNameProperty.get());
        } else {
            this.populationTextField.setDisable(false);
            this.setButton.setDisable(false);
        }

    }

    public void initSetupForEntityPopulation(String entityName) {
        this.keyNameProperty.set(entityName);
        this.checkboxProperty.set(false);
        this.populationTextField.setDisable(true);
        this.setButton.setDisable(true);
    }

    public void setNewExecutionController(NewExecutionController newExecutionController) {
        this.newExecutionController = newExecutionController;
    }
}
