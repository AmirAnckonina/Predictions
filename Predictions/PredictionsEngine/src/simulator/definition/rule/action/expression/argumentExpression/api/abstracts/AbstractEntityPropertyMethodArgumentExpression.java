package simulator.definition.rule.action.expression.argumentExpression.api.abstracts;

import simulator.definition.rule.action.expression.argumentExpression.utils.enums.eExpressionMethod;

public abstract class AbstractEntityPropertyMethodArgumentExpression extends AbstractMethodArgumentExpression {

    protected String entityName;

    protected String propertyName;

    public AbstractEntityPropertyMethodArgumentExpression(eExpressionMethod method, String entityName, String propertyName) {
        super(method);
        this.entityName = entityName;
        this.propertyName = propertyName;
    }
}
