package simulator.definition.rule.action.expression.argumentExpression.api.abstracts;

import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;

public abstract class AbstractArgumentExpression implements ArgumentExpression {
    protected ePropertyType expressionReturnedValueType;
    public AbstractArgumentExpression(ePropertyType expressionReturnedValueType) {
        this.expressionReturnedValueType = expressionReturnedValueType;
    }

    @Override
    public ePropertyType getExpressionReturnedValueType() {
        return this.expressionReturnedValueType;
    }
}
