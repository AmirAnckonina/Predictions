package simulator.definition.rule.action.expression.argumentExpression.api.abstracts;

import simulator.definition.property.utils.enums.PropertyType;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;

public abstract class AbstractArgumentExpression implements ArgumentExpression {
    protected PropertyType expressionReturnedValueType;
    public AbstractArgumentExpression(PropertyType expressionReturnedValueType) {
        this.expressionReturnedValueType = expressionReturnedValueType;
    }

    @Override
    public PropertyType getExpressionReturnedValueType() {

        return this.expressionReturnedValueType;
    }
}
