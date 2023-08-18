package simulator.definition.rule.action.expression.impl;

import simulator.definition.rule.action.expression.utils.enums.eExpressionMethod;
import simulator.definition.rule.action.expression.api.abstracts.AbstractMethodArgumentExpression;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.execution.context.api.ExecutionContext;

public class EnvironmentMethodArgumentExpressionImpl extends AbstractMethodArgumentExpression<Object> {
    public EnvironmentMethodArgumentExpressionImpl(eExpressionMethod method, Object methodParameter) {
        super(method, methodParameter);
    }

    @Override
    public Object getValue(ExecutionContext context) {
        String value = (String) context.getPropertyValueByName((String) this.methodParameter);
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
