package simulator.definition.rule.action.expression.argumentExpression.api.abstracts;

import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.eExpressionMethod;

public abstract class AbstractEntityPropertyMethodArgumentExpression extends AbstractMethodArgumentExpression {

    protected String entityName;

    protected String propertyName;

    public AbstractEntityPropertyMethodArgumentExpression(
            eExpressionMethod method,
            ePropertyType expressionReturnedValueType,
            String entityName,
            String propertyName) {

        super(method, expressionReturnedValueType);
        this.entityName = entityName;
        this.propertyName = propertyName;
    }
}
