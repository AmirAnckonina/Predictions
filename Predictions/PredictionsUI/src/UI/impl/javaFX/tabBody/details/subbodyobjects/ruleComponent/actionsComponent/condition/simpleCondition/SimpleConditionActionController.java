package UI.impl.javaFX.tabBody.details.subbodyobjects.ruleComponent.actionsComponent.condition.simpleCondition;

import UI.impl.javaFX.tabBody.details.subbodyobjects.ruleComponent.actionsComponent.model.SimpleConditionModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
        conditionNumOfThanAction.textProperty().bind(numOfThan.asString());
        conditionNumOfElseAction.textProperty().bind(numOfElse.asString());
        propertyConditionAction.textProperty().bind(property);
        operatorConditionAction.textProperty().bind(operator);
        valueConditionAction.textProperty().bind(value);
    }

    public void setValues(SimpleIntegerProperty numOfThan, SimpleIntegerProperty numOfElse, SimpleStringProperty property,
                          SimpleStringProperty operator, SimpleStringProperty value){
        this.numOfThan = numOfThan;
        this.numOfElse = numOfElse;
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

}