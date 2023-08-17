package simulator.definition.rule.action.expression.api;

import simulator.execution.context.api.Context;

public interface Expression<T> {

    public T getValue(Context context);
    public void setValue(T value);
}
