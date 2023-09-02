package simulator.definition.rule.action.expression.argumentExpression.impl;

import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.execution.context.api.ExecutionContext;

public class SimpleValueArgumentExpression<T> implements ArgumentExpression {

    private T value;
    public SimpleValueArgumentExpression(T value) {
        this.value = value;
    }

    @Override
    public T getValue(ExecutionContext context) {
        return this.value;
    }
}
