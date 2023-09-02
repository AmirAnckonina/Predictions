package simulator.definition.rule.action.expression.conditionExpression.impl.single;

import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.expression.conditionExpression.api.abstracts.AbstractSingleConditionExpression;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.execution.context.api.ExecutionContext;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

public class BiggerThanConditionExpression extends AbstractSingleConditionExpression {

    /**
     * @param entityName
     * @param conditionProperty
     * @param comparedValue In this stage the comparedValue expression should be with the current T value,
     *                      according to buildExpression procedure.
     */
    public BiggerThanConditionExpression(String entityName, ArgumentExpression conditionProperty, ArgumentExpression comparedValue) {
        super(entityName, conditionProperty, comparedValue);
    }


    @Override
    public boolean test(ExecutionContext context) {
        ePropertyType type = context.getPrimaryEntityInstance()
                .getPropertyByName(this.propertyName).getPropertyDefinition().getType();

        Boolean returnValue = false;
        switch (type) {
            case DECIMAL:
                returnValue = (Integer) this.conditionProperty.getValue(context)
                        > (Integer) this.comparedValue.getValue(context);
                break;
            case BOOLEAN:
            case STRING:
                throw new SimulatorRunnerException("Different arguments types - Condition test is not available");
            case FLOAT:
                returnValue = (Float) this.conditionProperty.getValue(context)
                        > (Float) this.comparedValue.getValue(context);
                break;
        }
        return returnValue;
    }
}
