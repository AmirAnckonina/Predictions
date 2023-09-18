package simulator.definition.rule.action.expression.argumentExpression.impl.method;

import enums.PropertyType;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractEntityPropertyMethodArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.ExpressionMethodType;
import simulator.execution.context.api.ExecutionContext;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

public class TicksMethodArgumentExpression extends AbstractEntityPropertyMethodArgumentExpression {

    public TicksMethodArgumentExpression(
            ExpressionMethodType method,
            PropertyType expressionReturnedValueType,
            String entityName,
            String propertyName) {

        super(method, expressionReturnedValueType, entityName, propertyName);
    }

    @Override
    public String toString() {
        return "Ticks";
    }

    @Override
    public Object getValue(ExecutionContext context) {
        Integer tickNo = context
                .getEntityInstanceByName(this.entityName)
                .getPropertyInstanceByName(this.propertyName)
                .getLastTickUpdate();

        return Float.parseFloat(tickNo.toString());
    }
}
