package simulator.definition.rule.action.expression.impl;

import simulator.builder.world.utils.enums.eExpressionMethod;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.rule.action.expression.api.AbstractMethodExpression;
import simulator.execution.context.api.Context;

public class EnvironmentMethodExpressionImpl extends AbstractMethodExpression<Object> {
    public EnvironmentMethodExpressionImpl(eExpressionMethod method, Object methodParameter) {
        super(method, methodParameter);
    }

    @Override
    public Object getValue(Context context) {
        String value = context.getPropertyValueByName((String) this.methodParameter);
        String type = context.getPropertyTypeByName((String) this.methodParameter);
        Object returnValue = null;
        switch (ePropertyType.valueOf(type)){
            case DECIMAL:
                returnValue = new Double(value);
                break;
            case BOOLEAN:
                returnValue = new Boolean(value);
                break;
            case STRING:
                returnValue = new String(value);
                break;
            case FLOAT:
                returnValue = new Float(value);
                break;
            case INTEGER:
                returnValue = new Integer(value);
                break;
        }

        return returnValue;
    }

    @Override
    public void setValue(Object value) {

    }
}
