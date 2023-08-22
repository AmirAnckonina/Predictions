package simulator.definition.rule.action.impl;

import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.api.abstracts.AbstractPropertyAction;
import simulator.definition.rule.action.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.utils.enums.eActionType;
import simulator.execution.context.api.ExecutionContext;
import simulator.runner.utils.exceptions.SimulatorRunnerException;


public class IncreaseAction extends AbstractPropertyAction {
    ArgumentExpression by;
    public IncreaseAction(eActionType type, String entityName, String propertyName, ArgumentExpression by) {
        super(type, entityName, propertyName);
        this.by = by;
    }

    @Override
    public void invoke(ExecutionContext executionContext) {
        Object propertyValue = executionContext.getPrimaryEntityInstance().getPropertyByName(this.propertyName).getValue();
        AbstractPropertyDefinition propDefinition =
                executionContext
                .getPrimaryEntityInstance()
                .getPropertyByName(this.propertyName)
                .getPropertyDefinition();

        ePropertyType propertyType = propDefinition.getType();

        switch (propertyType) {

            case DECIMAL:

                Integer integerValue = (Integer) ePropertyType.DECIMAL.convert(propertyValue);
                Double doubledByExpValue =  Double.valueOf(by.getValue(executionContext).toString());
                Double rawSumResultForDecimal = integerValue + doubledByExpValue;
                executionContext
                        .getPrimaryEntityInstance()
                        .getPropertyByName(this.propertyName)
                        .updateValue(rawSumResultForDecimal.intValue());
                break;

            case FLOAT:
                Float floatValue = (Float) ePropertyType.FLOAT.convert(propertyValue);
                Double doubleByExpValue = Double.valueOf(by.getValue(executionContext).toString());
                Double rawSumResultForFloat = floatValue + doubleByExpValue;
                executionContext
                        .getPrimaryEntityInstance()
                        .getPropertyByName(this.propertyName)
                        .updateValue(rawSumResultForFloat.floatValue());
                break;

            case BOOLEAN:
            case STRING:
                throw new SimulatorRunnerException("Different arguments types - Condition test is not available");
        }
    }
}
