package simulator.definition.rule.action.expression.argumentExpression.api.abstracts;

import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.eExpressionMethod;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;

public abstract class AbstractMethodArgumentExpression extends AbstractArgumentExpression {

    protected eExpressionMethod method;

    public AbstractMethodArgumentExpression(eExpressionMethod method, ePropertyType expressionReturnedValueType) {
        super(expressionReturnedValueType);
        this.method = method;
    }

}
