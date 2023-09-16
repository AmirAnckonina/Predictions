package UI.impl.javaFX.tabBody.details.subbodyobjects.ruleComponent.actionsComponent.condition.multipleCondition;

import UI.impl.javaFX.tabBody.details.subbodyobjects.ruleComponent.actionsComponent.model.MultipleModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MultipleConditionActionController extends MultipleModel {

    @FXML
    private Label conditionType;

    @FXML
    private Label numOfConditions;

    @FXML
    private void initialize() {
        conditionType.textProperty().bind(logicType);
        numOfConditions.textProperty().bind(numOfCondition);
    }

    public void setValues(String logicType, String numOfConditions){
        this.logicType.set(logicType);
        this.numOfCondition.set(numOfConditions);
    }

}
