package simulator.definition.rule.action.conditionExpression.api.abstracts;

import simulator.definition.rule.action.conditionExpression.api.interfaces.ConditionExpression;
import simulator.definition.rule.action.expression.api.interfaces.ArgumentExpression;

public abstract class AbstractSingleConditionExpression implements ConditionExpression {
    private final String entityName;
    private final String propertyName;
    private final ArgumentExpression comparedValue;

    /**
     *
     * @param entityName
     * @param propertyName
     * @param comparedValue
     * In this stage the comparedValue expression should be with the current T value,
     * according to buildExpression procedure.
     */
    public AbstractSingleConditionExpression(String entityName, String propertyName, ArgumentExpression comparedValue) {
        this.entityName = entityName;
        this.propertyName = propertyName;
        this.comparedValue = comparedValue;
    }
}
