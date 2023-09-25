package body.details.subbodyobjects.ruleComponent.actionsComponent.condition.multipleCondition;

import body.details.subbodyobjects.ruleComponent.actionsComponent.model.MultipleModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MultipleConditionActionController extends MultipleModel {

    @FXML
    private Label conditionType;

    @FXML
    private Label numOfConditions;

    @FXML
    private Label numOfThen;

    @FXML
    private Label numOfElse;

    @FXML
    private void initialize() {
        conditionType.textProperty().bind(logicType);
        numOfConditions.textProperty().bind(numOfCondition);
        numOfThen.textProperty().bind(nThen);
        numOfElse.textProperty().bind(nElse);
    }

    public void setValues(String logicType, String numOfConditions, String numOfThen, String numOfElse){
        this.logicType.set(logicType);
        this.numOfCondition.set(numOfConditions);
        this.nThen.set(numOfThen);
        this.nElse.set(numOfElse);
        if(numOfConditions == null){
            try{
                numOfCondition.set(Integer.toString(Integer.parseInt(numOfThen) + Integer.parseInt(numOfElse)));
            }catch (Exception e){}
        }
    }

}
