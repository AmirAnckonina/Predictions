package simulator.builder.world.api.interfaces;

import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.argumentExpression.api.interfaces.ArgumentExpression;

public interface ArgumentExpressionBuilder {
    ArgumentExpression buildExpression(String rawExpression, ePropertyType type);
}
