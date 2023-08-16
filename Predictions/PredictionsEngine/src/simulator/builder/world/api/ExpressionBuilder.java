package simulator.builder.world.api;

import simulator.definition.rule.action.expression.api.Expression;

public interface ExpressionBuilder {
    Expression buildExpression(String rawExpression);
}
