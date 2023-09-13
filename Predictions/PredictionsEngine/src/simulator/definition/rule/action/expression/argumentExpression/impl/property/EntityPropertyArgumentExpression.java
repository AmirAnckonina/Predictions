package simulator.definition.rule.action.expression.argumentExpression.impl.property;


import enums.PropertyType;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractArgumentExpression;
import simulator.execution.context.api.ExecutionContext;

public class EntityPropertyArgumentExpression extends AbstractArgumentExpression {
    private String entityName;
    private String propertyName;

    public EntityPropertyArgumentExpression(String entityName, String propertyName, PropertyType expressionReturnedValueType) {
        super(expressionReturnedValueType);
        this.entityName = entityName;
        this.propertyName = propertyName;
    }

    @Override
    public Object getValue(ExecutionContext context) {
        return context
                .getEntityInstanceByName(this.entityName)
                .getPropertyInstanceByName(this.propertyName)
                .getValue();

    }



}
