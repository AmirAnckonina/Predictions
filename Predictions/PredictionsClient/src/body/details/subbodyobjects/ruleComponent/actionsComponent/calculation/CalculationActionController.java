package body.details.subbodyobjects.ruleComponent.actionsComponent.calculation;

import body.details.subbodyobjects.ruleComponent.actionsComponent.model.CalculationModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CalculationActionController extends CalculationModel {
    @FXML
    private Label calculationActionType;

    @FXML
    private Label firstCalculationArg;

    @FXML
    private Label secondCalculationArg;

    @FXML
    private void initialize() {
        calculationActionType.textProperty().bind(type);
        firstCalculationArg.textProperty().bind(firstArgument);
        secondCalculationArg.textProperty().bind(secondArgument);
    }

    public void setValues(String type, String firstArgument, String secondArgument){
        this.type.set(type);
        this.firstArgument.set(firstArgument);
        this.secondArgument.set(secondArgument);
    }
}
