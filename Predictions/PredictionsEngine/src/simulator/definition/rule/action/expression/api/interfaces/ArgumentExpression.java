package simulator.definition.rule.action.expression.api.interfaces;
import simulator.execution.context.api.ExecutionContext;

public interface ArgumentExpression<T> {

    public T getValue(ExecutionContext context);
    public void setValue(T value);
}