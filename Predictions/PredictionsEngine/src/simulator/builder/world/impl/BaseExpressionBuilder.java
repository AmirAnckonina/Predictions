package simulator.builder.world.impl;

import simulator.builder.world.api.AbstractComponentBuilder;
import simulator.builder.world.api.ExpressionBuilder;
import simulator.builder.world.validator.api.WorldContextBuilderHelper;
import simulator.definition.rule.action.expression.api.Expression;

public class BaseExpressionBuilder extends AbstractComponentBuilder implements ExpressionBuilder {

    public BaseExpressionBuilder(WorldContextBuilderHelper contextValidator) {
        super(contextValidator);
    }

    @Override
    public Expression buildExpression(String rawExpression) {
        return null;
    }
}
