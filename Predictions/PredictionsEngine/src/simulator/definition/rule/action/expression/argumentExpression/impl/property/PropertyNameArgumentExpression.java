package simulator.definition.rule.action.expression.argumentExpression.impl.property;


import simulator.definition.property.utils.enums.PropertyType;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractArgumentExpression;
import simulator.execution.context.api.ExecutionContext;

public class PropertyNameArgumentExpression extends AbstractArgumentExpression {

    private String propertyName;

    public PropertyNameArgumentExpression(String propertyName, PropertyType expressionReturnedValueType) {
        super(expressionReturnedValueType);
        this.propertyName = propertyName;
    }

    @Override
    public Object getValue(ExecutionContext context) {
        return context.getPrimaryEntityInstance().getPropertyByName(propertyName).getValue();
    }



}
