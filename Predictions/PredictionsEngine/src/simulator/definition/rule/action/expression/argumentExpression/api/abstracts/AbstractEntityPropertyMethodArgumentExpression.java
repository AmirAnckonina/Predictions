package simulator.definition.rule.action.expression.argumentExpression.api.abstracts;

import enums.PropertyType;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.ExpressionMethodType;

public abstract class AbstractEntityPropertyMethodArgumentExpression extends AbstractMethodArgumentExpression {

    protected String entityName;
    protected String propertyName;

    public AbstractEntityPropertyMethodArgumentExpression(
            ExpressionMethodType method,
            PropertyType expressionReturnedValueType,
            String entityName,
            String propertyName) {

        super(method, expressionReturnedValueType);
        this.entityName = entityName;
        this.propertyName = propertyName;
    }
}
