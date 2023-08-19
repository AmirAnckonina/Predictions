package simulator.definition.rule.action.argumentExpression.api.abstracts;

import simulator.definition.rule.action.argumentExpression.utils.enums.eExpressionMethod;
import simulator.definition.rule.action.argumentExpression.api.interfaces.ArgumentExpression;

public abstract class AbstractMethodArgumentExpression<T> implements ArgumentExpression<T> {

    protected eExpressionMethod method;
    protected T methodParameter;

    public AbstractMethodArgumentExpression(eExpressionMethod method, T methodParameter  ) {
        this.method = method;
        this.methodParameter = methodParameter;
    }
}
