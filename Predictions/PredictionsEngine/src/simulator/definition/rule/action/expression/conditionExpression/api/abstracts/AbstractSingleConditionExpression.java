package simulator.definition.rule.action.expression.conditionExpression.api.abstracts;

import simulator.definition.rule.action.expression.conditionExpression.api.interfaces.ConditionExpression;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;

public abstract class AbstractSingleConditionExpression implements ConditionExpression {
    protected final String entityName;
    protected final ArgumentExpression comparedValue;
    protected final ArgumentExpression conditionProperty;

    /**
     *
     * @param entityName
     * @param conditionProperty
     * @param comparedValue
     * In this stage the comparedValue expression should be with the current T value,
     * according to buildExpression procedure.
     */
    public AbstractSingleConditionExpression(String entityName, ArgumentExpression conditionProperty, ArgumentExpression comparedValue) {
        this.entityName = entityName;
        this.conditionProperty = conditionProperty;
        this.comparedValue = comparedValue;
    }
}
