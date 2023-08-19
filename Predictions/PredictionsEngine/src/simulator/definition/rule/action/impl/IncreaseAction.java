package simulator.definition.rule.action.impl;

import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.api.abstracts.AbstractPropertyAction;
import simulator.definition.rule.action.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.utils.enums.eActionType;
import simulator.execution.context.api.ExecutionContext;
import simulator.execution.runner.utils.exceptions.RunnerException;


public class IncreaseAction extends AbstractPropertyAction {
    ArgumentExpression by;
    public IncreaseAction(eActionType type, String entityName, String propertyName, ArgumentExpression by) {
        super(type, entityName, propertyName);
        this.by = by;
    }

    @Override
    public void invoke(ExecutionContext context) {
        Double intPropertyValue;
        Object propertyValue = context.getPrimaryEntityInstance().getPropertyByName(this.propertyName).getValue();
        ePropertyType propertyType =
                context.getPrimaryEntityInstance()
                .getPropertyByName(this.propertyName)
                .getPropertyDefinition().getType();

        switch (propertyType) {
            case DECIMAL:
                intPropertyValue = ePropertyType.DECIMAL.convert((Double) propertyValue);
                intPropertyValue += ePropertyType.DECIMAL.convert((Double)by.getValue(context));
                context.getPrimaryEntityInstance().getPropertyByName(this.propertyName).updateValue(intPropertyValue.intValue());
                break;
            case BOOLEAN:
            case STRING:
                throw new RunnerException("Different arguments types - Condition test is not available");
            case FLOAT:
                intPropertyValue = ePropertyType.FLOAT.convert((Double) propertyValue);
                intPropertyValue += ePropertyType.FLOAT.convert((Double)by.getValue(context));
                context.getPrimaryEntityInstance().getPropertyByName(this.propertyName).updateValue(intPropertyValue);
                break;
        }

        context.getPrimaryEntityInstance().getPropertyByName(propertyName).getValue();
        context.getPrimaryEntityInstance().getPropertyByName(propertyName).getPropertyDefinition().getType();
    }
}
