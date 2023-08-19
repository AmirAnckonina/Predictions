package simulator.definition.rule.action.argumentExpression.impl;


import simulator.definition.rule.action.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.execution.context.api.ExecutionContext;

public class PropertyArgumentExpressionImpl implements ArgumentExpression<String> {

    String propertyName;

    public PropertyArgumentExpressionImpl(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public String getValue(ExecutionContext context) {
        return null;//context.getPropertyValueByName(propertyName);
    }

    @Override
    public void setValue(String value) {

    }
}
