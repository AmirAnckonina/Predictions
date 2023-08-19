package simulator.definition.rule.action.conditionExpression.impl.single;

import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.conditionExpression.api.abstracts.AbstractSingleConditionExpression;
import simulator.definition.rule.action.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.execution.context.api.ExecutionContext;
import simulator.execution.runner.utils.exceptions.RunnerException;
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
        ePropertyType type = context.getPrimaryEntityInstance()
                .getPropertyByName(this.propertyName).getPropertyDefinition().getType();

        Boolean returnValue = false;
        switch (type) {
            case DECIMAL:
                returnValue = (Integer) context.getPrimaryEntityInstance().getPropertyByName(this.propertyName).getValue()
                        > (Integer) this.comparedValue.getValue(context);
                break;
            case BOOLEAN:
            case STRING:
                throw new RunnerException("Different arguments types - Condition test is not available");
            case FLOAT:
                returnValue = (Float) context.getPrimaryEntityInstance().getPropertyByName(this.propertyName).getValue()
                        > (Float) this.comparedValue.getValue(context);
                break;
        }
        return returnValue;
    }
}
