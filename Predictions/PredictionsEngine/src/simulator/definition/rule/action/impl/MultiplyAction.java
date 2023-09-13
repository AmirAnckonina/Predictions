package simulator.definition.rule.action.impl;

import enums.PropertyType;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.utils.enums.ActionType;
import simulator.definition.rule.action.api.abstracts.AbstractCalculationAction;
import simulator.execution.context.api.ExecutionContext;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;

public class MultiplyAction extends AbstractCalculationAction {

    public MultiplyAction(ActionType type, String entityName, String propertyName, ArgumentExpression argumentExpression1, ArgumentExpression argumentExpression2) {
        super(type, entityName, propertyName, argumentExpression1, argumentExpression2);
    }


    @Override
    public void invoke(ExecutionContext context) {

        EntityInstance primaryEntityInstance = context.getEntityInstanceByName(this.primaryEntityName);
        PropertyInstance primaryEntityPropertyInstance = primaryEntityInstance.getPropertyInstanceByName(this.propertyName);
        PropertyType resultPropertyType = primaryEntityPropertyInstance.getPropertyDefinition().getType();
        Object arg1Value = arg1.getValue(context);
        Object arg2Value = arg2.getValue(context);

        Double multiplyResult =  (Double.valueOf(arg1Value.toString()))  *  (Double.valueOf(arg2Value.toString()));

        if (resultPropertyType == PropertyType.DECIMAL) {

            primaryEntityPropertyInstance.updateValue(new Integer(multiplyResult.intValue()));

        } else if (resultPropertyType == PropertyType.FLOAT) {

            primaryEntityPropertyInstance.updateValue(new Float(multiplyResult.floatValue()));
        }

    }
}
