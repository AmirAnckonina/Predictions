package simulator.definition.rule.action.conditionExpression.impl.single;

import simulator.definition.rule.action.conditionExpression.api.abstracts.AbstractSingleConditionExpression;
import simulator.definition.rule.action.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.execution.context.api.ExecutionContext;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BiggerThanConditionExpression extends AbstractSingleConditionExpression {
    /**
     * @param entityName
     * @param propertyName
     * @param comparedValue In this stage the comparedValue expression should be with the current T value,
     *                      according to buildExpression procedure.
     */
    public BiggerThanConditionExpression(String entityName, String propertyName, ArgumentExpression comparedValue) {
        super(entityName, propertyName, comparedValue);
    }

    @Override
    public boolean test(ExecutionContext context) {

        throw new NotImplementedException();
        //return propertyName.getValue() >=   comparedValue.getValue();

    }
}
