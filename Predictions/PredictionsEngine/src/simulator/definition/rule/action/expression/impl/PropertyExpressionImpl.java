package simulator.definition.rule.action.expression.impl;


import simulator.definition.rule.action.expression.api.interfaces.Expression;
import simulator.execution.context.api.ExecutionContext;

public class PropertyExpressionImpl implements Expression<String> {

    String propertyName;

    public PropertyExpressionImpl(String propertyName) {
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
