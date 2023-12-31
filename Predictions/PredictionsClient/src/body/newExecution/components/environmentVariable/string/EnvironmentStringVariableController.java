package body.newExecution.components.environmentVariable.string;

import body.newExecution.components.environmentVariable.KeyValueProperty;
import body.newExecution.model.KeyToStringData;
import body.newExecution.newExecutionMain.NewExecutionController;
import enums.SetPropertyStatus;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Optional;

public class EnvironmentStringVariableController extends KeyToStringData implements KeyValueProperty {

    private NewExecutionController newExecutionController;

    @FXML private Label envVarLabel;
    @FXML private TextField envVarTextField;
    @FXML private CheckBox setCheckbox;
    @FXML private Button setButton;
    @FXML private Label typeLabel;
    @FXML private Label statusLabel;
    public EnvironmentStringVariableController() { super();}

    @FXML
    private void initialize() {
        setCheckbox.selectedProperty().bindBidirectional(this.checkboxProperty);
        envVarLabel.textProperty().bindBidirectional(this.keyNameProperty);
        envVarTextField.textProperty().bindBidirectional(this.stringValueProperty);
        typeLabel.textProperty().bindBidirectional(this.typeProperty);
        statusLabel.textProperty().bindBidirectional(this.statusProperty);
    }

    @FXML
    void OnSetButtonClicked() {
        this.newExecutionController.setEnvironmentProperty(keyNameProperty.get(), stringValueProperty.getValue());
    }

    @FXML
    void onSetCheckbox() {
        boolean checked = this.checkboxProperty.get();
        if (!checked) {
            this.envVarTextField.setDisable(true);
            this.setButton.setDisable(true);
            this.newExecutionController.rollbackSingleEnvironmentVariable(this.keyNameProperty.get());
        } else {
            this.envVarTextField.setDisable(false);
            this.setButton.setDisable(false);
        }

    }

    public void initSetupForEnvStringVariable(String envPropertyName) {
        this.keyNameProperty.set(envPropertyName);
        this.checkboxProperty.set(false);
        this.typeProperty.set("String");
        this.envVarTextField.setDisable(true);
        this.setButton.setDisable(true);
    }

    public void setNewExecutionController(NewExecutionController newExecutionController) {
        this.newExecutionController = newExecutionController;
    }

    @Override
    public void setStatus(SetPropertyStatus setPropertyStatus) {
        this.statusProperty.set(setPropertyStatus.toString());
    }

    @Override
    public void clearAndResetProperty() {
        this.checkboxProperty.set(false);
        onSetCheckbox();
    }

    @Override
    public <T> void setPropertyValueByManualParamProcedure(T value) {
        this.checkboxProperty.set(true);
        onSetCheckbox();
        this.stringValueProperty.set((String) value);
    }

    @Override
    public Optional<String> getValueAsString() {
        return Optional.empty();
    }
}
