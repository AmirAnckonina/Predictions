package UI.impl.javaFX.tabBody.details.subbodyobjects.ruleComponent;

import UI.impl.javaFX.tabBody.details.subbodyobjects.ruleComponent.actionsComponent.calculation.CalculationActionController;
import UI.impl.javaFX.tabBody.details.subbodyobjects.ruleComponent.actionsComponent.condition.multipleCondition.MultipleConditionActionController;
import UI.impl.javaFX.tabBody.details.subbodyobjects.ruleComponent.actionsComponent.condition.simpleCondition.SimpleConditionActionController;
import UI.impl.javaFX.tabBody.details.subbodyobjects.ruleComponent.actionsComponent.proximity.ProximityActionController;
import dto.StringActionDto;
import enums.ActionType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.net.URL;

import static UI.impl.javaFX.common.CommonResourcesPaths.*;

public class MainActionController extends RuleModel {

    @FXML
    private Label ruleName;

    @FXML
    private Label mainEntity;

    @FXML
    private Label secondaryEntity;

    @FXML
    private FlowPane externComponent;

    @FXML
    private Label actionType;

    private StringActionDto actions;


    @FXML
    private void initialize() {
        ruleName.textProperty().bind(name);
        mainEntity.textProperty().bind(main);
        secondaryEntity.textProperty().bind(secondary);
        actionType.textProperty().bind(type);
    }

    public void setValues(String name, StringActionDto action){
        this.name.set(name);
        this.main.set(action.getMainEntity());
        this.secondary.set(action.getSecondEntityValue());
        this.type.set(action.getType());
        this.actions = action;
        createActionComponent(action);

    }

    private void createActionComponent(StringActionDto action) {
        ActionType actionType = ActionType.valueOf(action.getType().toUpperCase());
        
        switch (actionType) {
            case INCREASE:
            case DECREASE:
            case SET:
            case REPLACE:
            case KILL:
                break;
            case CALCULATION:
            case DIVIDE:
            case MULTIPLY:
                createCalculationActionComponent(action);
                break;
            case CONDITION:
                createConditionActionComponent(action);
                break;
            case PROXIMITY:
                createProximityActionComponent(action);
                break;
        }

    }

    private void createProximityActionComponent(StringActionDto action) {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(RULE_PROXIMITY_DETAILS_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            GridPane gpComponent = loader.load();

            ProximityActionController controller = loader.getController();
            controller.setValues(action.getSourceEntity(), action.getDestinationEntity(), action.getDepthCircle(),
                    action.getNumOfActionInProximity());
            externComponent.getChildren().add(gpComponent);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
        }
    }

    private void createConditionActionComponent(StringActionDto action) {
        if(action.getNumOfThenActions() != null){
            try
            {
                FXMLLoader loader = new FXMLLoader();
                URL fxmlUrl = getClass().getResource(RULE_CONDITION_MULT_DETAILS_FXML_RESOURCE);
                loader.setLocation(fxmlUrl);
                GridPane gpComponent = loader.load();

                MultipleConditionActionController controller = loader.getController();
                controller.setValues(action.getOperatorAction(), action.getNumOfConditionInLogicAction());
                externComponent.getChildren().add(gpComponent);
            } catch (Exception e) {
                e.getMessage();
                e.printStackTrace(System.out);
            }
        }else {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                URL fxmlUrl = getClass().getResource(RULE_CONDITION_SMPL_DETAILS_FXML_RESOURCE);
                loader.setLocation(fxmlUrl);
                GridPane gpComponent = loader.load();

                SimpleConditionActionController controller = loader.getController();
                controller.setValues(action.getNumOfThenActions(), action.getNumOfElseActions(), action.getPropertyAction(),
                        action.getOperatorAction(), action.getComparedValue());
                externComponent.getChildren().add(gpComponent);
            } catch (Exception e) {
                e.getMessage();
                e.printStackTrace(System.out);
            }
        }

    }

    private void createCalculationActionComponent(StringActionDto action) {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(RULE_CALC_DETAILS_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            GridPane gpComponent = loader.load();

            CalculationActionController controller = loader.getController();
            controller.setValues(action.getType(), action.getFirstArg(), action.getSecondArg());
            externComponent.getChildren().add(gpComponent);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
        }
    }

}
