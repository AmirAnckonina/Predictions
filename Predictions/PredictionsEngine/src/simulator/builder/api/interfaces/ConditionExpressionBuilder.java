package simulator.builder.api.interfaces;

import simulator.definition.rule.action.expression.conditionExpression.api.abstracts.AbstractMultipleConditionExpression;
import simulator.definition.rule.action.expression.conditionExpression.api.abstracts.AbstractSingleConditionExpression;
import simulator.definition.rule.action.expression.conditionExpression.api.interfaces.ConditionExpression;

public interface ConditionExpressionBuilder {
    ConditionExpression buildConditionExpression();
    AbstractSingleConditionExpression buildSingleCondition();
    AbstractMultipleConditionExpression buildMultipleCondition();
}
