package simulator.definition.rule.action.expression.argumentExpression.api.interfaces;
import simulator.execution.context.api.ExecutionContext;

public interface ArgumentExpression {
    public Object getValue(ExecutionContext context);
}
