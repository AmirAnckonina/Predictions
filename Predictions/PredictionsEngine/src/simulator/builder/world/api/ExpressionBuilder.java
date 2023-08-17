package simulator.builder.world.api;

import simulator.builder.world.utils.enums.eExpressionExpectedValueType;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.rule.action.expression.api.Expression;

public interface ExpressionBuilder {
    Expression buildExpression(String rawExpression, ePropertyType type);
}
