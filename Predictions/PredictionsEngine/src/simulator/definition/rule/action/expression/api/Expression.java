package simulator.definition.rule.action.expression.api;

public interface Expression<T> {

    public T getValue();
    public void setValue(T value);
}
