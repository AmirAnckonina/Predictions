package simulator.definition.rule.action.expression.impl;


import simulator.definition.rule.action.expression.api.Expression;
import simulator.execution.context.api.Context;

public class PropertyExpressionImpl implements Expression<String> {

    String propertyName;

    public PropertyExpressionImpl(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public String getValue(Context context) {
        return context.getPropertyValueByName(propertyName);
    }

    @Override
    public void setValue(String value) {

    }
}
