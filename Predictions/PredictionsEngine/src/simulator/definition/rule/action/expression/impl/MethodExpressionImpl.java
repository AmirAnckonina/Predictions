package simulator.definition.rule.action.expression.impl;

import simulator.builder.world.utils.enums.eExpressionMethod;
import simulator.definition.rule.action.expression.api.Expression;

public class MethodExpressionImpl<T> implements Expression<T> {

    eExpressionMethod method;
    T methodParameter;

    public MethodExpressionImpl(eExpressionMethod method, T methodParameter  ) {
        this.method = method;
        this.methodParameter = methodParameter;
    }

    @Override
    public T getValue() {
        return null;
    }

    @Override
    public void setValue(T value) {

    }
}
