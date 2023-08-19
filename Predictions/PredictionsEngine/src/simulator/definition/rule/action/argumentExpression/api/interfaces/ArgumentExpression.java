package simulator.definition.rule.action.argumentExpression.api.interfaces;
import simulator.execution.context.api.ExecutionContext;

public interface ArgumentExpression<T> {

    public Object getValue(ExecutionContext context);
    public void setValue(T value);
}
