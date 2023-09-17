package simulator.definition.rule.action.impl;

import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import enums.PropertyType;
import simulator.definition.rule.action.api.abstracts.AbstractPropertyAction;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import enums.ActionType;
import simulator.execution.context.api.ExecutionContext;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.runner.utils.exceptions.SimulatorRunnerException;


public class IncreaseAction extends AbstractPropertyAction {
    ArgumentExpression by;
    public IncreaseAction(ActionType type, String entityName, String propertyName, ArgumentExpression by) {
        super(type, entityName, propertyName);
        this.by = by;
    }

    @Override
    public String toString() {
        String resVal = new String();

        if(actionSecondaryEntityDefinition==null){
            resVal = "Increase{" +
                    "by=" + by +
                    ", propertyName='" + propertyName + '\'' +
                    ", type=" + type +
                    ", primaryEntityName='" + primaryEntityName + '\'' +
                    ", actionSecondaryEntityDefinition=" + " - " +
                    '}' + System.lineSeparator();
        }else {
            resVal = "Increase{" +
                    "by=" + by +
                    ", propertyName='" + propertyName + '\'' +
                    ", type=" + type +
                    ", primaryEntityName='" + primaryEntityName + '\'' +
                    ", actionSecondaryEntityDefinition=" + actionSecondaryEntityDefinition +
                    '}' + System.lineSeparator();
        }
        return resVal;
    }

    @Override
    public void invoke(ExecutionContext executionContext) {

        EntityInstance primaryEntityInstance = executionContext.getEntityInstanceByName(this.primaryEntityName);
        PropertyInstance primaryEntityPropInstance = primaryEntityInstance.getPropertyInstanceByName(this.propertyName);
        Object propertyValue = primaryEntityPropInstance.getValue();
        AbstractPropertyDefinition propDefinition = primaryEntityPropInstance.getPropertyDefinition();
        PropertyType propertyType = propDefinition.getType();

        switch (propertyType) {

            case DECIMAL:

                Integer integerValue = (Integer) PropertyType.DECIMAL.convert(propertyValue);
                Double doubledByExpValue =  Double.valueOf(by.getValue(executionContext).toString());
                Double rawSumResultForDecimal = integerValue + doubledByExpValue;
                primaryEntityPropInstance.updateValue(rawSumResultForDecimal.intValue(), executionContext.getTickDocument().getTickNumber());
                break;

            case FLOAT:
                Float floatValue = (Float) PropertyType.FLOAT.convert(propertyValue);
                Double doubleByExpValue = Double.valueOf(by.getValue(executionContext).toString());
                Double rawSumResultForFloat = floatValue + doubleByExpValue;
                primaryEntityPropInstance.updateValue(rawSumResultForFloat.floatValue(), executionContext.getTickDocument().getTickNumber());
                break;

            case BOOLEAN:
            case STRING:
                throw new SimulatorRunnerException("Different arguments types - Condition test is not available");
        }
    }
}
