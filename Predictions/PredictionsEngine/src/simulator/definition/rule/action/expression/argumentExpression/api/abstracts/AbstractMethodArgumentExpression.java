package simulator.definition.rule.action.expression.argumentExpression.api.abstracts;

import enums.PropertyType;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.ExpressionMethodType;

public abstract class AbstractMethodArgumentExpression extends AbstractArgumentExpression {

    protected ExpressionMethodType method;

    public AbstractMethodArgumentExpression(ExpressionMethodType method, PropertyType expressionReturnedValueType) {
        super(expressionReturnedValueType);
        this.method = method;
    }

}
