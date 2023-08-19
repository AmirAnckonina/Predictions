package simulator.definition.rule.action.argumentExpression.impl;

import simulator.definition.rule.action.argumentExpression.utils.enums.eExpressionMethod;
import simulator.definition.rule.action.argumentExpression.api.abstracts.AbstractMethodArgumentExpression;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.execution.context.api.ExecutionContext;

public class EnvironmentMethodArgumentExpressionImpl extends AbstractMethodArgumentExpression<String> {
    public EnvironmentMethodArgumentExpressionImpl(eExpressionMethod method, String methodParameter) {
        super(method, methodParameter);
    }

    @Override
    public Object getValue(ExecutionContext context) {
        Object value = context.getEnvironmentVariable(this.methodParameter).getValue();
        ePropertyType type = context.getEnvironmentVariable(this.methodParameter).getPropertyDefinition().getType();
        Object returnValue = null;
        switch (type){
            case DECIMAL:
                returnValue = (Double) value;
                break;
            case BOOLEAN:
                returnValue =  (Boolean) value;
                break;
            case STRING:
                returnValue = (String) value;
                break;
            case FLOAT:
                returnValue = (Float) value;
                break;
        }

        return returnValue;
    }

    @Override
    public void setValue(String value) {

    }
}
