package simulator.builder.world.api.interfaces;

import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.expression.api.interfaces.Expression;

public interface ExpressionBuilder {
    Expression buildExpression(String rawExpression, ePropertyType type);
}
