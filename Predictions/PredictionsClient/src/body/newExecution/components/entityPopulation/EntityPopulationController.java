package body.newExecution.components.entityPopulation;

import body.newExecution.components.environmentVariable.KeyValueProperty;
import body.newExecution.model.KeyToIntegerData;
import body.newExecution.newExecutionMain.NewExecutionController;
import enums.SetPropertyStatus;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EntityPopulationController extends KeyToIntegerData implements KeyValueProperty {
    private NewExecutionController newExecutionController;

    @FXML private Label entityNameLabel;
    @FXML private Label typeLabel;
    @FXML private CheckBox setCheckbox;
    @FXML private Label statusLabel;
    @FXML private Button setButton;
    @FXML private TextField valueTextfield;

    public EntityPopulationController() {
        super();
    }

    @FXML
    private void initialize() {
        setCheckbox.selectedProperty().bindBidirectional(this.checkboxProperty);
        entityNameLabel.textProperty().bindBidirectional(keyNameProperty);
        valueTextfield.textProperty().bindBidirectional(this.integerValuePropertyAsString);
        typeLabel.textProperty().bindBidirectional(this.typeProperty);
        statusLabel.textProperty().bindBidirectional(this.statusProperty);
    }
    @FXML
    void OnSetButtonClicked() {
        try {
            Integer population = Integer.parseInt(this.integerValuePropertyAsString.get());
            this.newExecutionController.setSingleEntityPopulation(keyNameProperty.get(), population);
        } catch (Exception e) {
            this.setStatus(SetPropertyStatus.FAILED);
        }
    }

    @FXML
    void onSetCheckbox() {
        boolean checked = this.checkboxProperty.get();
        if (!checked) {
            this.valueTextfield.setDisable(true);
            this.setButton.setDisable(true);
            this.newExecutionController.rollbackSingleEntityPopulation(this.keyNameProperty.get());
        } else {
            this.valueTextfield.setDisable(false);
            this.setButton.setDisable(false);
        }

    }

    @Override
    public void setStatus(SetPropertyStatus setPropertyStatus) {
        this.statusProperty.set(setPropertyStatus.toString());
    }

    @Override
    public void clearAndResetProperty() {
        this.checkboxProperty.set(false);
        this.onSetCheckbox();
    }

    @Override
    public <T> void setPropertyValueByManualParamProcedure(T value) {
        this.checkboxProperty.set(true);
        onSetCheckbox();
        this.integerValuePropertyAsString.set(value.toString());
    }

    public void initSetupForEntityPopulation(String entityName) {
        this.keyNameProperty.set(entityName);
        this.checkboxProperty.set(false);
        this.typeProperty.set("Integer");
        this.valueTextfield.setDisable(true);
        this.setButton.setDisable(true);
    }

    public void setNewExecutionController(NewExecutionController newExecutionController) {
        this.newExecutionController = newExecutionController;
    }

}
