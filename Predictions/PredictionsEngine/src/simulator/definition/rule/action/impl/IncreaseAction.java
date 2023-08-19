package simulator.definition.rule.action.impl;

import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.api.abstracts.AbstractPropertyAction;
import simulator.definition.rule.action.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.utils.enums.eActionType;
import simulator.execution.context.api.ExecutionContext;


public class IncreaseAction extends AbstractPropertyAction {
    ArgumentExpression by;
    public IncreaseAction(eActionType type, String entityName, String propertyName, ArgumentExpression by) {
        super(type, entityName, propertyName);
        this.by = by;
    }

    @Override
    public void invoke(ExecutionContext context) {
        Integer intPropertyValue;
        Object propertyValue = context.getPropertyValueByName(this.propertyName);
        if(propertyValue instanceof Integer){
            intPropertyValue = ePropertyType.INTEGER.convert(propertyValue);
            intPropertyValue += (Integer) ePropertyType.INTEGER.convert(by.getValue(context));
        } else if (propertyValue instanceof Double) {
            intPropertyValue = ePropertyType.DECIMAL.convert(propertyValue);
            intPropertyValue += ePropertyType.DECIMAL.convert(by.getValue(context));
        }
        context.setPropertyInstanceValue(this.propertyName, Double.parseDouble() + by.getValue(context));


        context.getPrimaryEntityInstance().getPropertyByName(propertyName).getValue();
        context.getPrimaryEntityInstance().getPropertyByName(propertyName).getPropertyDefinition().getType();
    }
}
