package UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.floats;

import UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.EnvironmentPropertyController;
import UI.impl.javaFX.tabBody.newExecution.model.KeyToFloatData;
import UI.impl.javaFX.tabBody.newExecution.newExecutionMain.NewExecutionController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EnvironmentFloatVariableController extends KeyToFloatData implements EnvironmentPropertyController {

    private NewExecutionController newExecutionController;

    @FXML
    private Label envVarNameLabel;

    @FXML
    private TextField envFloatValueTextField;

    @FXML
    private void initialize() {
        this.envVarNameLabel.textProperty().bind(this.keyNameProperty);
        this.envFloatValueTextField.textProperty().bind(this.floatValueProperty.asString());
    }

    @FXML
    void onEnvFloatValueTextFieldUpdate() {
        this.newExecutionController.setEnvironmentProperty(this.keyNameProperty.get(), this.floatValueProperty.getValue());
    }

    public void initSetupForEnvFloatVariable(String envPropertyName) {
        this.keyNameProperty.set(envPropertyName);
        this.floatValueProperty.set(0);
    }
    public void setNewExecutionController(NewExecutionController newExecutionController) {
        this.newExecutionController = newExecutionController;
    }

}
