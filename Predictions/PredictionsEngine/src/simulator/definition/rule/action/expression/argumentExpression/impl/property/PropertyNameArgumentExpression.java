package simulator.definition.rule.action.expression.argumentExpression.impl.property;


import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.execution.context.api.ExecutionContext;

public class PropertyNameArgumentExpression extends AbstractArgumentExpression {

    private String propertyName;

    public PropertyNameArgumentExpression(String propertyName, ePropertyType expressionReturnedValueType) {
        super(expressionReturnedValueType);
        this.propertyName = propertyName;
    }

    @Override
    public Object getValue(ExecutionContext context) {
        return context.getPrimaryEntityInstance().getPropertyByName(propertyName).getValue();
    }



}
