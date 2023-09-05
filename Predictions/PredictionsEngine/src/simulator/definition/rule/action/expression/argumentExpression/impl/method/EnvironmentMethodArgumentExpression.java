package simulator.definition.rule.action.expression.argumentExpression.impl.method;

import simulator.definition.rule.action.expression.argumentExpression.utils.enums.ExpressionMethodType;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractMethodArgumentExpression;
import simulator.definition.property.utils.enums.PropertyType;
import simulator.execution.context.api.ExecutionContext;

public class EnvironmentMethodArgumentExpression extends AbstractMethodArgumentExpression {

    private String environmentPropertyName;

    public EnvironmentMethodArgumentExpression(
            ExpressionMethodType method,
            PropertyType expressionReturnedValueType,
            String envPropertyName) {

        super(method, expressionReturnedValueType);
        this.environmentPropertyName = envPropertyName;
    }

    @Override
    public Object getValue(ExecutionContext context) {
        Object value = context.getEnvironmentVariable(this.environmentPropertyName).getValue();
        PropertyType type = context.getEnvironmentVariable(this.environmentPropertyName).getPropertyDefinition().getType();
        Object returnValue = null;
        switch (type){
            case DECIMAL:
                returnValue = (Integer) value;
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

}
