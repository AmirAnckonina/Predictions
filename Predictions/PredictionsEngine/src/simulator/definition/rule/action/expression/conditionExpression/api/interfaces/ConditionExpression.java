package simulator.definition.rule.action.expression.conditionExpression.api.interfaces;

import simulator.execution.context.api.ExecutionContext;

public interface ConditionExpression {
    boolean test(ExecutionContext context);
}
