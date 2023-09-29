package ui.tabs.management.details.subbodyobjects.ruleComponent.actionsComponent.condition.simpleCondition;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.tabs.management.details.subbodyobjects.ruleComponent.actionsComponent.model.SimpleConditionModel;

public class SimpleConditionActionController extends SimpleConditionModel {

    @FXML
    private Label conditionNumOfThanAction;

    @FXML
    private Label conditionNumOfElseAction;

    @FXML
    private Label propertyConditionAction;

    @FXML
    private Label operatorConditionAction;

    @FXML
    private Label valueConditionAction;

    @FXML
    private void initialize() {
        conditionNumOfThanAction.textProperty().bind(numOfThan);
        conditionNumOfElseAction.textProperty().bind(numOfElse);
        propertyConditionAction.textProperty().bind(property);
        operatorConditionAction.textProperty().bind(operator);
        valueConditionAction.textProperty().bind(value);
    }

    public void setValues(String numOfThan, String numOfElse, String property,
                          String operator, String value){
        this.numOfThan.set(numOfThan);
        this.numOfElse.set(numOfElse);
        this.property.set(property);
        this.operator.set(operator);
        this.value.set(value);
    }

}