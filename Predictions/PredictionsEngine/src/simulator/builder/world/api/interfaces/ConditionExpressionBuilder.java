package simulator.builder.world.api.interfaces;

import simulator.definition.rule.action.conditionExpression.api.abstracts.AbstractMultipleConditionExpression;
import simulator.definition.rule.action.conditionExpression.api.abstracts.AbstractSingleConditionExpression;
import simulator.definition.rule.action.conditionExpression.api.interfaces.ConditionExpression;

public interface ConditionExpressionBuilder {
    ConditionExpression buildConditionExpression();
    AbstractSingleConditionExpression buildSingleCondition();
    AbstractMultipleConditionExpression buildMultipleCondition();
}
