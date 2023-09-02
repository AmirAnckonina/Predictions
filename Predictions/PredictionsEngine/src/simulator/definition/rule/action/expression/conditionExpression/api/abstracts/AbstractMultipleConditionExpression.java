package simulator.definition.rule.action.expression.conditionExpression.api.abstracts;

import simulator.definition.rule.action.expression.conditionExpression.api.interfaces.ConditionExpression;

import java.util.List;

public abstract class AbstractMultipleConditionExpression implements ConditionExpression {
    protected List<ConditionExpression> conditions;
    public AbstractMultipleConditionExpression(List<ConditionExpression> conditions) {
        this.conditions = conditions;
    }
}
