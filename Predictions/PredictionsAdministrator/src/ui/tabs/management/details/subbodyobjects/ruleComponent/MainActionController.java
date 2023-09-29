package ui.tabs.management.details.subbodyobjects.ruleComponent;

import dto.StringActionDto;
import enums.ActionType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import ui.tabs.management.details.subbodyobjects.ruleComponent.actionsComponent.calculation.CalculationActionController;
import ui.tabs.management.details.subbodyobjects.ruleComponent.actionsComponent.condition.multipleCondition.MultipleConditionActionController;
import ui.tabs.management.details.subbodyobjects.ruleComponent.actionsComponent.condition.simpleCondition.SimpleConditionActionController;
import ui.tabs.management.details.subbodyobjects.ruleComponent.actionsComponent.generic.GenericActionController;
import ui.tabs.management.details.subbodyobjects.ruleComponent.actionsComponent.proximity.ProximityActionController;

import java.net.URL;

import static common.CommonResourcesPaths.*;



public class MainActionController extends RuleModel {


    @FXML
    private Label mainEntity;

    @FXML
    private Label secondaryEntity;

    @FXML
    private FlowPane externComponent;

    @FXML
    private Label actionType;

    @FXML
    private Label tikInterval;

    @FXML
    private Label probability;

    private StringActionDto actions;


    @FXML
    private void initialize() {
        mainEntity.textProperty().bind(main);
        secondaryEntity.textProperty().bind(secondary);
        actionType.textProperty().bind(type);
        tikInterval.textProperty().bind(tikInterv);
        probability.textProperty().bind(prob);
    }

    public void setValues(String activationTickInterval, String activationProbability, StringActionDto action){
        this.tikInterv.set(activationTickInterval);
        this.prob.set(activationProbability);
        this.main.set(action.getMainEntity());
        this.secondary.set(action.getSecondEntityValue());
        this.type.set(action.getType());
        this.actions = action;
        createActionComponent(action);
        //activationTickInterval, activationProbability, action

    }

    private void createActionComponent(StringActionDto action) {
        ActionType actionType = ActionType.valueOf(action.getType().toUpperCase());
        
        switch (actionType) {
            case INCREASE:
            case DECREASE:
            case SET:
            case REPLACE:
            case KILL:
                createGenericActionComponent(action);
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

    private void createGenericActionComponent(StringActionDto action) {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(RULE_GENERIC_DETAILS_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            GridPane gpComponent = loader.load();

            GenericActionController controller = loader.getController();
            controller.setValues(action.getValue());
            externComponent.getChildren().add(gpComponent);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
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
        if(action.getMulti() != null){
            try
            {
                FXMLLoader loader = new FXMLLoader();
                URL fxmlUrl = getClass().getResource(RULE_CONDITION_MULT_DETAILS_FXML_RESOURCE);
                loader.setLocation(fxmlUrl);
                GridPane gpComponent = loader.load();

                MultipleConditionActionController controller = loader.getController();
                controller.setValues(action.getOperatorAction(), action.getNumOfConditionInLogicAction(), action.getNumOfThenActions(), action.getNumOfElseActions());
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
