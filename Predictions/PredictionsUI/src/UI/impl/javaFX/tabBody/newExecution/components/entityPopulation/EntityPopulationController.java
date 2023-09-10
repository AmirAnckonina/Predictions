package UI.impl.javaFX.tabBody.newExecution.components.entityPopulation;

import UI.impl.javaFX.mainScene.PredictionsMainController;
import UI.impl.javaFX.model.PredictionsMainModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EntityPopulationController {
    private PredictionsMainController predictionsMainController;
    private PredictionsMainModel predictionsMainModel;

    public void setPredictionsMainController(PredictionsMainController predictionsMainController) {
        this.predictionsMainController = predictionsMainController;
    }

    public void setPredictionsMainModel(PredictionsMainModel predictionsMainModel) {
        this.predictionsMainModel = predictionsMainModel;
    }

    @FXML
    private Label entityNameLabel;

    @FXML
    private TextField populationTextField;

    @FXML
    void onPopulationTextFieldUpdate() {
        predictionsMainModel.setEntityPopulation(entityNameLabel.getText(), populationTextField.getText());
    }

}
