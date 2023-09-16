package dto;

import enums.ActionType;

import java.util.HashMap;
import java.util.Map;

public class StringActionDto {
    private String type;
    private String mainEntity;
    private String secondEntityName;
    private String secondEntityType;
    private String secondEntityValue;
    private String propertyName;
    private String value;
    private String calculationType;
    private String numOfThenActions;
    private String numOfElseActions;
    private String propertyAction;
    private String operatorAction;
    private String logicAction;
    private String numOfConditionInLogicAction;
    private String sourceEntity;
    private String destinationEntity;
    private String depthCircle;
    private String firstArg;
    private String secondArg;
    private String comparedValue;

    public StringActionDto(String action) {
        setActionsPropertyFromString(action);
    }

    private void setActionsPropertyFromString(String action) {
        int openBraceIndex = action.indexOf("{");
        int closeBraceIndex = action.indexOf("}");
        this.type = action.substring(0, openBraceIndex).trim();
        String actionDetail = action.substring(openBraceIndex + 1, closeBraceIndex);
        String[] listOfDetails = actionDetail.split(",");
        Map<String,String> detailesMap = new HashMap<>();
        for(String detail:listOfDetails){
            String fixDetail = detail.replaceAll(" ", "");
            int equalsIndex = fixDetail.indexOf("=");
            detailesMap.put(fixDetail.substring(0,equalsIndex), fixDetail.substring(equalsIndex + 1,fixDetail.length()));
        }

        this.mainEntity = detailesMap.get("primaryEntityName");
        if(detailesMap.containsKey("actionSecondaryEntityDefinition")){
            this.secondEntityName = detailesMap.get("actionSecondaryEntityDefinition");
        }


        switch (ActionType.valueOf(type.toUpperCase())) {
            case INCREASE:
            case DECREASE:
            case SET:
                this.value = detailesMap.get("by");
            case REPLACE:
            case KILL:
                this.propertyName = detailesMap.get("propertyName");
                this.mainEntity = detailesMap.get("primaryEntityName");
                this.secondEntityName = detailesMap.get("actionSecondaryEntityDefinition");
                System.out.println("t");
                break;
            case CALCULATION:
            case DIVIDE:
            case MULTIPLY:
                this.calculationType = this.type;
                this.firstArg = detailesMap.get("");
                this.secondArg = detailesMap.get("");
                break;
            case CONDITION:
                if(detailesMap.containsKey("multi")) {
                    this.numOfThenActions = detailesMap.get("thenActions");
                    this.numOfElseActions = detailesMap.get("elseActions");
                }
                else{
                    this.propertyAction = detailesMap.get("property");
                    this.operatorAction = detailesMap.get("operator");
                    this.comparedValue = detailesMap.get("comparedValue");
                }
                break;
            case PROXIMITY:
                this.destinationEntity = detailesMap.get("targetEntityName");
                this.sourceEntity = detailesMap.get("sourceEntityName");
                this.numOfActionInProximity = detailesMap.get("actionsUnderProximity");
                this.depthCircle = detailesMap.get("envDepth");
                if(detailesMap.get("envDepth").indexOf("=") != -1) {
                    String envDepth = detailesMap.get("envDepth").substring(detailesMap.get("envDepth").indexOf("=") + 1);
                    envDepth = "Value of - " + envDepth;
                    this.depthCircle = envDepth;
                }
                break;
        }
    }

    public String getType() {
        return type;
    }

    public String getMainEntity() {
        return mainEntity;
    }

    public String getSecondEntityName() {
        return secondEntityName;
    }

    public String getSecondEntityType() {
        return secondEntityType;
    }

    public String getSecondEntityValue() {
        return secondEntityValue;
    }

    public String getCalculationType() {
        return calculationType;
    }

    public String getNumOfThenActions() {
        return numOfThenActions;
    }

    public String getNumOfElseActions() {
        return numOfElseActions;
    }

    public String getPropertyAction() {
        return propertyAction;
    }

    public String getOperatorAction() {
        return operatorAction;
    }

    public String getLogicAction() {
        return logicAction;
    }

    public String getNumOfConditionInLogicAction() {
        return numOfConditionInLogicAction;
    }

    public String getSourceEntity() {
        return sourceEntity;
    }

    public String getDestinationEntity() {
        return destinationEntity;
    }

    public String getDepthCircle() {
        return depthCircle;
    }

    public String getNumOfActionInProximity() {
        return numOfActionInProximity;
    }

    private String numOfActionInProximity;
}
