package simulator.definition.rule.action.expression.api;

import simulator.builder.world.utils.enums.eExpressionMethod;

public abstract class AbstractMethodExpression<T> implements Expression<T> {

    protected eExpressionMethod method;
    protected T methodParameter;

    public AbstractMethodExpression(eExpressionMethod method, T methodParameter  ) {
        this.method = method;
        this.methodParameter = methodParameter;
    }
}
