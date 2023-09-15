package simulator.definition.rule.action.expression.conditionExpression.impl.single;

import enums.PropertyType;
import simulator.definition.rule.action.expression.conditionExpression.api.abstracts.AbstractSingleConditionExpression;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.execution.context.api.ExecutionContext;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

public class LowerThanConditionExpression extends AbstractSingleConditionExpression {


    /**
     * @param entityName
     * @param conditionProperty
     * @param comparedValue In this stage the comparedValue expression should be with the current T value,
     *                      according to buildExpression procedure.
     */
    public LowerThanConditionExpression(String entityName, ArgumentExpression conditionProperty, ArgumentExpression comparedValue) {
        super(entityName, conditionProperty, comparedValue);
    }

    @Override
    public String toString() {
        return "Condition{" +
                "conditionType=single" +
                ", operator=<" +
                ", entityName='" + entityName + '\'' +
                ", comparedValue=" + comparedValue +
                ", conditionProperty=" + conditionProperty +
                '}';
    }

    @Override
    public boolean test(ExecutionContext context) {
        PropertyType propType = this.conditionProperty.getExpressionReturnedValueType();
        PropertyType compType = this.comparedValue.getExpressionReturnedValueType();

        if (compType != propType) {
            throw new SimulatorRunnerException("property type is different from compared value type," +
                    "can't complete lower than test");
        }

        Boolean returnValue = false;

        switch (propType) {
            case DECIMAL:
                returnValue = (Integer) this.conditionProperty.getValue(context)
                        < (Integer) this.comparedValue.getValue(context);
                break;
            case BOOLEAN:
            case STRING:
                throw new SimulatorRunnerException("Different arguments types - Condition test is not available");
            case FLOAT:
                returnValue = (Float) this.conditionProperty.getValue(context)
                        < (Float) this.comparedValue.getValue(context);
                break;
        }
        return returnValue;
    }
}
