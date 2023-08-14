package simulator.definition.rule.action.expression.impl;

import simulator.definition.rule.action.expression.api.Expression;

public class ExpressionImpl implements Expression {
    private String value;

    public ExpressionImpl(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void execute() {

    }
}
