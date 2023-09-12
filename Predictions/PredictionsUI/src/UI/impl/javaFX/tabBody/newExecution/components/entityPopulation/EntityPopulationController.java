package UI.impl.javaFX.tabBody.newExecution.components.entityPopulation;

import UI.impl.javaFX.mainScene.PredictionsMainController;
import UI.impl.javaFX.tabBody.newExecution.newExecutionMain.NewExecutionController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EntityPopulationController {

    private SimpleStringProperty entityNameProperty;
    private SimpleIntegerProperty populationProperty;
    private PredictionsMainController predictionsMainController;
    private NewExecutionController newExecutionController;

    @FXML private Label entityNameLabel;
    @FXML private TextField populationTextField;

    @FXML
    private void initialize() {
        entityNameLabel.textProperty().bind(entityNameProperty);
        populationTextField.textProperty().bind(populationProperty.asString());
    }


    @FXML
    void onPopulationTextFieldUpdate() {
        this.newExecutionController.setSingleEntityPopulation(entityNameProperty.get(), populationProperty.get());
    }

    public void initSetupForEntityPopulation(String entityName) {
        this.entityNameProperty.set(entityName);
        this.populationProperty.set(0);
    }

    public void setPredictionsMainController(PredictionsMainController predictionsMainController) {
        this.predictionsMainController = predictionsMainController;
    }

    public void setNewExecutionController(NewExecutionController newExecutionController) {
        this.newExecutionController = newExecutionController;
    }
}
