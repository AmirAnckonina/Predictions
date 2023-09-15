package UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.bool;

import UI.impl.javaFX.tabBody.newExecution.model.KeyToBooleanData;
import UI.impl.javaFX.tabBody.newExecution.newExecutionMain.NewExecutionController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
public class EnvironmentBooleanVariableController extends KeyToBooleanData {

    private NewExecutionController newExecutionController;
    @FXML private Label envVarLabel;
    @FXML private CheckBox setCheckbox;
    @FXML private Button setButton;
    @FXML private Label typeLabel;
    @FXML private Label statusLabel;
    @FXML private CheckBox valueCheckbox;
    public EnvironmentBooleanVariableController() { super(); }

    @FXML
    private void initialize() {
        setCheckbox.selectedProperty().bindBidirectional(this.checkboxProperty);
        envVarLabel.textProperty().bind(this.keyNameProperty);
        valueCheckbox.selectedProperty().bindBidirectional(this.booleanValueProperty);
        typeLabel.textProperty().bindBidirectional(this.typeProperty);
        statusLabel.textProperty().bindBidirectional(this.statusProperty);
    }

    @FXML
    void onValueCheckbox() {

    }

    @FXML
    void OnSetButtonClicked() {
        this.newExecutionController.setEnvironmentProperty(this.keyNameProperty.get(), this.booleanValueProperty.getValue());
    }

    @FXML
    void onSetCheckbox() {
        boolean checked = this.checkboxProperty.get();
        if (!checked) {
            this.valueCheckbox.setDisable(true);
            this.setButton.setDisable(true);
            this.newExecutionController.rollbackSingleEnvironmentVariable(this.keyNameProperty.get());
        } else {
            this.valueCheckbox.setDisable(false);
            this.setButton.setDisable(false);
        }
    }

    public void initSetupForEnvBooleanVariable(String envPropertyName) {
        this.keyNameProperty.set(envPropertyName);
        this.booleanValueProperty.set(false);
        this.checkboxProperty.set(false);
        this.valueCheckbox.setDisable(true);
        this.setButton.setDisable(true);
    }

    public void setNewExecutionController(NewExecutionController newExecutionController) {
        this.newExecutionController = newExecutionController;
    }
}
