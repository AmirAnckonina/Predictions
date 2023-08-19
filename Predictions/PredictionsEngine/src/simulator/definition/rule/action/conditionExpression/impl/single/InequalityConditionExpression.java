package simulator.definition.rule.action.conditionExpression.impl.single;

import simulator.definition.rule.action.conditionExpression.api.abstracts.AbstractSingleConditionExpression;
import simulator.definition.rule.action.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.execution.context.api.ExecutionContext;

public class InequalityConditionExpression extends AbstractSingleConditionExpression {
    /**
     * @param entityName
     * @param propertyName
     * @param comparedValue In this stage the comparedValue expression should be with the current T value,
     *                      according to buildExpression procedure.
     */
    public InequalityConditionExpression(String entityName, String propertyName, ArgumentExpression comparedValue) {
        super(entityName, propertyName, comparedValue);
    }

    @Override
    public boolean test(ExecutionContext context) {
        return context.getPrimaryEntityInstance(this.entityName)
                .getPropertyByName(this.propertyName)
                .getValue()
                != this.comparedValue;
    }
}
