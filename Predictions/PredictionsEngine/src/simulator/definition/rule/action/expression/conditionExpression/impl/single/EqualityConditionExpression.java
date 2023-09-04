package simulator.definition.rule.action.expression.conditionExpression.impl.single;

import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.expression.conditionExpression.api.abstracts.AbstractSingleConditionExpression;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.execution.context.api.ExecutionContext;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

public class EqualityConditionExpression extends AbstractSingleConditionExpression {

    /**
     * @param entityName
     * @param conditionProperty
     * @param comparedValue In this stage the comparedValue expression should be with the current T value,
     *                      according to buildExpression procedure.
     */
    public EqualityConditionExpression(String entityName, ArgumentExpression conditionProperty, ArgumentExpression comparedValue) {
        super(entityName, conditionProperty, comparedValue);
    }



    @Override
    public boolean test(ExecutionContext context) {

        ePropertyType propType = this.conditionProperty.getExpressionReturnedValueType();
        ePropertyType compType = this.comparedValue.getExpressionReturnedValueType();

        if (compType != propType) {
            throw new SimulatorRunnerException("property type is different from compared value type," +
                    "can't complete equality test");
        }

        Boolean returnValue = false;
        switch (propType) {
            case BOOLEAN:
                returnValue = (Boolean) this.conditionProperty.getValue(context)
                        == (Boolean) this.comparedValue.getValue(context);
                break;
            case STRING:
                String condPropValueStr = (String) this.conditionProperty.getValue(context);
                String compValueStr = (String) this.comparedValue.getValue(context);
                returnValue = (condPropValueStr.equals(compValueStr));
                break;
            case FLOAT:
                returnValue = (Float) this.conditionProperty.getValue(context)
                        == (Float) this.comparedValue.getValue(context);
                break;
            case DECIMAL:
                returnValue = (Integer) this.conditionProperty.getValue(context)
                        == (Integer) this.comparedValue.getValue(context);
                break;
        }

        return returnValue;
    }

}
