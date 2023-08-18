package simulator.definition.rule.action.conditionExpression.impl.v2;

import simulator.definition.rule.action.conditionExpression.api.interfaces.ConditionExpression;

import java.util.List;

public class ConditionExpressionImpl implements ConditionExpression {
    private List<ConditionExpression> condition;
    @Override
    public boolean test() {
        return false;
    }
}
