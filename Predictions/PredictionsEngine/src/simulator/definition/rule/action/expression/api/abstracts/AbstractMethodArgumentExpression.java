package simulator.definition.rule.action.expression.api.abstracts;

import simulator.definition.rule.action.expression.utils.enums.eExpressionMethod;
import simulator.definition.rule.action.expression.api.interfaces.ArgumentExpression;

public abstract class AbstractMethodArgumentExpression<T> implements ArgumentExpression<T> {

    protected eExpressionMethod method;
    protected T methodParameter;

    public AbstractMethodArgumentExpression(eExpressionMethod method, T methodParameter  ) {
        this.method = method;
        this.methodParameter = methodParameter;
    }
}
