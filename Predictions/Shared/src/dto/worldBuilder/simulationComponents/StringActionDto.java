package dto.worldBuilder.simulationComponents;

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
    private String multi;

    public StringActionDto(String action) {
        setActionsPropertyFromString(action);
    }
    public String getMulti() {
        return multi;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getValue() {
        return value;
    }

    public String getFirstArg() {
        return firstArg;
    }

    public String getSecondArg() {
        return secondArg;
    }

    public String getComparedValue() {
        return comparedValue;
    }

    private void setActionsPropertyFromString(String action) {
        int openBraceIndex = action.indexOf("{");
        int closeBraceIndex = action.lastIndexOf("}");
        this.type = action.substring(0, openBraceIndex).trim();
        String actionDetail = action.substring(openBraceIndex + 1, closeBraceIndex);
        String[] listOfDetails = actionDetail.split(",");
        Map<String,String> detailesMap = new HashMap<>();
        for(String detail:listOfDetails){
            String fixDetail = detail.replaceAll(" ", "").replaceAll("}", "");
            int equalsIndex = fixDetail.indexOf("=");
            detailesMap.put(fixDetail.substring(0,equalsIndex), fixDetail.substring(equalsIndex + 1,fixDetail.length()));
        }

        this.mainEntity = detailesMap.get("primaryEntityName");
        if(detailesMap.containsKey("actionSecondaryEntityDefinition")){
            this.secondEntityName = detailesMap.get("actionSecondaryEntityDefinition");
        }

        ////////////////////
        this.value = (detailesMap.get("by") == null)?detailesMap.get("value"):detailesMap.get("by");
        this.propertyName = detailesMap.get("propertyName");
        this.mainEntity = detailesMap.get("primaryEntityName");
        this.secondEntityName = detailesMap.get("actionSecondaryEntityDefinition");
        this.calculationType = this.type;
        this.firstArg = detailesMap.get("arg1");
        this.secondArg = detailesMap.get("arg2");
        if(detailesMap.containsKey("multi")) {
            this.numOfThenActions = detailesMap.get("thenActions");
            this.numOfElseActions = detailesMap.get("elseActions");
            this.numOfConditionInLogicAction = detailesMap.get("conditions");
        }
        else{
            this.propertyAction = detailesMap.get("property");
            this.operatorAction = detailesMap.get("operator");
            this.comparedValue = detailesMap.get("comparedValue");
        }
        this.destinationEntity = detailesMap.get("targetEntityName");
        this.sourceEntity = detailesMap.get("sourceEntityName");
        this.numOfActionInProximity = detailesMap.get("actionsUnderProximity");
        this.depthCircle = detailesMap.get("envDepth");

        ////////////////////

        switch (ActionType.valueOf(type.toUpperCase())) {
            case INCREASE:
            case DECREASE:
            case SET:
                this.value = (detailesMap.get("by") == null)?detailesMap.get("value"):detailesMap.get("by");
                this.value = (this.value.startsWith("environmentPropertyName"))?
                        this.value.replaceAll("environmentPropertyName", "Environment property")
                        :this.value;

            case REPLACE:
            case KILL:
                this.propertyName = detailesMap.get("propertyName");
                this.mainEntity = detailesMap.get("primaryEntityName");
                this.secondEntityName = detailesMap.get("actionSecondaryEntityDefinition");
                break;
            case CALCULATION:
            case DIVIDE:
            case MULTIPLY:
                this.calculationType = this.type;
                this.firstArg = (detailesMap.get("arg1").startsWith("environmentPropertyName"))?
                        detailesMap.get("arg1")
                                .replaceAll("environmentPropertyName", "Environment property").replaceAll("=", " - ")
                        :detailesMap.get("arg1");
                this.secondArg = (detailesMap.get("arg2").startsWith("environmentPropertyName"))?
                        detailesMap.get("arg2")
                                .replaceAll("environmentPropertyName", "Environment property").replaceAll("=", " - ")
                        :detailesMap.get("arg2");
                break;
            case CONDITION:
                this.numOfThenActions = detailesMap.get("thenActions");
                this.numOfElseActions = detailesMap.get("elseActions");
                if(detailesMap.containsKey("multi") || detailesMap.get("condition").indexOf("multi") != -1) {
//                    this.numOfThenActions = detailesMap.get("thenActions");
//                    this.numOfElseActions = detailesMap.get("elseActions");
                    this.numOfConditionInLogicAction = detailesMap.get("conditions");
                    multi = "multi";
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
