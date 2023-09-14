package UI.impl.javaFX.tabBody.newExecution.components.entityPopulation;

import UI.impl.javaFX.mainScene.PredictionsMainController;
import UI.impl.javaFX.tabBody.newExecution.model.KeyToIntegerData;
import UI.impl.javaFX.tabBody.newExecution.newExecutionMain.NewExecutionController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class EntityPopulationController extends KeyToIntegerData {
    private NewExecutionController newExecutionController;

    @FXML private Label entityNameLabel;
    @FXML private TextField populationTextField;

    public EntityPopulationController() {
        super();
    }

    @FXML
    private void initialize() {
        entityNameLabel.textProperty().bind(keyNameProperty);
        populationTextField.textProperty().bindBidirectional(this.integerValueProperty, new NumberStringConverter());
    }


    @FXML
    void onPopulationTextFieldUpdate() {
        this.newExecutionController.setSingleEntityPopulation(keyNameProperty.get(), integerValueProperty.get());
    }

    public void initSetupForEntityPopulation(String entityName) {
        this.keyNameProperty.set(entityName);
    }

    public void setNewExecutionController(NewExecutionController newExecutionController) {
        this.newExecutionController = newExecutionController;
    }
}
