package UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.string;

import UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.EnvironmentPropertyController;
import UI.impl.javaFX.tabBody.newExecution.model.KeyToStringData;
import UI.impl.javaFX.tabBody.newExecution.newExecutionMain.NewExecutionController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EnvironmentStringVariableController extends KeyToStringData implements EnvironmentPropertyController {

    private NewExecutionController newExecutionController;

    @FXML
    private Label envVarLabel;

    @FXML
    private TextField envVarTextField;

    @FXML
    private void initialize() {
        envVarLabel.textProperty().bind(keyNameProperty);
        envVarTextField.textProperty().bind(stringValueProperty);
    }
    @FXML
    void onEnvVarTextFieldUpdate() {
        this.newExecutionController.setEnvironmentProperty(keyNameProperty.get(), stringValueProperty.get());
    }

    public void initSetupForEnvStringVariable(String envPropertyName) {
        this.keyNameProperty.set(envPropertyName);
    }

    public void setNewExecutionController(NewExecutionController newExecutionController) {
        this.newExecutionController = newExecutionController;
    }

}
