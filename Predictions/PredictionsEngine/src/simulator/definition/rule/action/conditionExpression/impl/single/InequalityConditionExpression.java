package simulator.definition.rule.action.conditionExpression.impl.single;

import simulator.definition.property.utils.enums.ePropertyType;
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
        ePropertyType type = context.getPrimaryEntityInstance()
                .getPropertyByName(this.propertyName).getPropertyDefinition().getType();

        Boolean returnValue = false;
        switch (type) {
            case DECIMAL:
                returnValue = (Double) context.getPrimaryEntityInstance().getPropertyByName(this.propertyName).getValue()
                        != (Double) this.comparedValue.getValue(context);
                break;
            case BOOLEAN:
                returnValue = (Boolean) context.getPrimaryEntityInstance().getPropertyByName(this.propertyName).getValue()
                        != (Boolean) this.comparedValue.getValue(context);
                break;
            case STRING:
                returnValue = ((String) context.getPrimaryEntityInstance().getPropertyByName(this.propertyName)
                        .getValue()).equals((String) this.comparedValue.getValue(context));
                break;
            case FLOAT:
                returnValue = (Float) context.getPrimaryEntityInstance().getPropertyByName(this.propertyName).getValue()
                        != (Float) this.comparedValue.getValue(context);
                break;
            case INTEGER:
                returnValue = (Integer) context.getPrimaryEntityInstance().getPropertyByName(this.propertyName).getValue()
                        != (Integer) this.comparedValue.getValue(context);
                break;
        }

        return returnValue;
    }

}
