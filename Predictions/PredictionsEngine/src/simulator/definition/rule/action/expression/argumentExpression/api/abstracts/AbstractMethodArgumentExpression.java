package simulator.definition.rule.action.expression.argumentExpression.api.abstracts;

import simulator.definition.rule.action.expression.argumentExpression.utils.enums.eExpressionMethod;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;

public abstract class AbstractMethodArgumentExpression implements ArgumentExpression {

    protected eExpressionMethod method;

    public AbstractMethodArgumentExpression(eExpressionMethod method) {
        this.method = method;
    }
}
