package simulator.definition.rule.action.expression.api.abstracts;

import simulator.definition.rule.action.expression.utils.enums.eExpressionMethod;
import simulator.definition.rule.action.expression.api.interfaces.Expression;

public abstract class AbstractMethodExpression<T> implements Expression<T> {

    protected eExpressionMethod method;
    protected T methodParameter;

    public AbstractMethodExpression(eExpressionMethod method, T methodParameter  ) {
        this.method = method;
        this.methodParameter = methodParameter;
    }
}
