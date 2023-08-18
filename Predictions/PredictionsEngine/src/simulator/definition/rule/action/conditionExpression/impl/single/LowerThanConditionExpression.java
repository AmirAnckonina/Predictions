package simulator.definition.rule.action.conditionExpression.impl.single;

import simulator.definition.rule.action.conditionExpression.api.abstracts.AbstractSingleConditionExpression;
import simulator.definition.rule.action.expression.api.interfaces.Expression;

public class LowerThanConditionExpression extends AbstractSingleConditionExpression {
    /**
     * @param entityName
     * @param propertyName
     * @param comparedValue In this stage the comparedValue expression should be with the current T value,
     *                      according to buildExpression procedure.
     */
    public LowerThanConditionExpression(String entityName, String propertyName, Expression comparedValue) {
        super(entityName, propertyName, comparedValue);
    }

    @Override
    public boolean test() {
        return false;
    }
}
