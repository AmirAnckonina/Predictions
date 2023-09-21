package UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.floats;

import UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.KeyValueProperty;
import UI.impl.javaFX.tabBody.newExecution.model.KeyToFloatData;
import UI.impl.javaFX.tabBody.newExecution.newExecutionMain.NewExecutionController;
import enums.SetPropertyStatus;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import structure.range.Range;

import java.util.Optional;

public class EnvironmentFloatVariableController extends KeyToFloatData implements KeyValueProperty {

    private NewExecutionController newExecutionController;


    @FXML private Label envVarLabel;
    @FXML private TextField envVarTextField;
    @FXML private CheckBox setCheckbox;
    @FXML private Label typeLabel;
    @FXML private Label statusLabel;
    @FXML private Button setButton;

    public EnvironmentFloatVariableController() {super();}

    @FXML
    private void initialize() {
        setCheckbox.selectedProperty().bindBidirectional(this.checkboxProperty);
        envVarLabel.textProperty().bindBidirectional(this.keyNameProperty);
        envVarTextField.textProperty().bindBidirectional(this.floatValuePropertyAsString);
        typeLabel.textProperty().bindBidirectional(this.typeProperty);
        statusLabel.textProperty().bindBidirectional(this.statusProperty);
    }

    @FXML
    void OnSetButtonClicked() {
        try {
            Float fVal = Float.parseFloat(this.floatValuePropertyAsString.get());
            this.newExecutionController.setEnvironmentProperty(this.keyNameProperty.get(), fVal);
        } catch (Exception e) {
            this.setStatus(SetPropertyStatus.FAILED);
        }
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
    public void initSetupForEnvFloatVariable(String envPropertyName, Optional<Range> range) {
        this.keyNameProperty.set(envPropertyName);
        this.checkboxProperty.set(false);
        this.typeProperty.set("Numeric");
        range.ifPresent((rng) -> {
            this.typeProperty.set(
                    this.typeProperty.getValue()
                    + String.format("\nRange: %.3f to %.3f", rng.getFrom(), rng.getTo())
            );
        });
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
        this.floatValuePropertyAsString.set(value.toString());
    }
}
