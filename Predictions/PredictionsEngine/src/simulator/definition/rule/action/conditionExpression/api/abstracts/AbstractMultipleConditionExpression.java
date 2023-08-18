package simulator.definition.rule.action.conditionExpression.api.abstracts;

import simulator.definition.rule.action.conditionExpression.api.interfaces.ConditionExpression;

import java.util.List;

public abstract class AbstractMultipleConditionExpression implements ConditionExpression {
    private List<ConditionExpression> conditions;

    public AbstractMultipleConditionExpression(List<ConditionExpression> conditions) {
        this.conditions = conditions;
    }
}
