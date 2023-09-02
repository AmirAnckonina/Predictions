package simulator.definition.rule.action.expression.conditionExpression.impl.single;

import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.expression.conditionExpression.api.abstracts.AbstractSingleConditionExpression;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.execution.context.api.ExecutionContext;

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

        ePropertyType type = context.getPrimaryEntityInstance()
                .getPropertyByName(this.propertyName).getPropertyDefinition().getType();

        Boolean returnValue = false;
        switch (type) {
            case BOOLEAN:
                returnValue = (Boolean) context.getPrimaryEntityInstance().getPropertyByName(this.propertyName).getValue()
                        == (Boolean) this.comparedValue.getValue(context);
                break;
            case STRING:
                returnValue = ((String) context.getPrimaryEntityInstance().getPropertyByName(this.propertyName)
                        .getValue()).equals((String) this.comparedValue.getValue(context));
                break;
            case FLOAT:
                returnValue = (Float) context.getPrimaryEntityInstance().getPropertyByName(this.propertyName).getValue()
                        == (Float) this.comparedValue.getValue(context);
                break;
            case DECIMAL:
                returnValue = (Integer) context.getPrimaryEntityInstance().getPropertyByName(this.propertyName).getValue()
                        == (Integer) this.comparedValue.getValue(context);
                break;
        }

        return returnValue;
    }

}
