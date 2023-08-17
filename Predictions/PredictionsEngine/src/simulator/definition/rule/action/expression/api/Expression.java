package simulator.definition.rule.action.expression.api;
import simulator.execution.context.api.ExecutionContext;

public interface Expression<T> {

    public T getValue(ExecutionContext context);
    public void setValue(T value);
}
