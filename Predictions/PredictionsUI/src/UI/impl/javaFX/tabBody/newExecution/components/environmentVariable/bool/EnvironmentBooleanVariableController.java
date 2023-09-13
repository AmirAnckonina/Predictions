package UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.bool;

import UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.EnvironmentPropertyController;
import UI.impl.javaFX.tabBody.newExecution.model.KeyToBooleanData;
import UI.impl.javaFX.tabBody.newExecution.newExecutionMain.NewExecutionController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
public class EnvironmentBooleanVariableController extends KeyToBooleanData implements EnvironmentPropertyController {

    private NewExecutionController newExecutionController;

    @FXML
    private Label envVarLabel;

    @FXML
    private CheckBox envBooleanValueCheckbox;

    @FXML
    private void initialize() {
        this.envVarLabel.textProperty().bind(this.keyNameProperty);
        this.envBooleanValueCheckbox.selectedProperty().bindBidirectional(this.booleanValueProperty);
    }

    @FXML
    void onEnvBooleanValueCheckedChanged() {
        this.newExecutionController.setEnvironmentProperty(this.keyNameProperty.get(), this.booleanValueProperty.getValue());
    }

    public void initSetupForEnvBooleanVariable(String envPropertyName) {
        this.keyNameProperty.set(envPropertyName);
        this.booleanValueProperty.set(false);
    }

    public void setNewExecutionController(NewExecutionController newExecutionController) {
        this.newExecutionController = newExecutionController;
    }

}
