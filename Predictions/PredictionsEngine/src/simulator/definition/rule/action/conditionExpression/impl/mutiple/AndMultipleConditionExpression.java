package simulator.definition.rule.action.conditionExpression.impl.mutiple;

import simulator.definition.rule.action.conditionExpression.api.abstracts.AbstractMultipleConditionExpression;
import simulator.definition.rule.action.conditionExpression.api.abstracts.AbstractSingleConditionExpression;

import java.util.List;

public class AndMultipleConditionExpression extends AbstractMultipleConditionExpression {
    public AndMultipleConditionExpression(List<AbstractSingleConditionExpression> conditions) {
        super(conditions);
    }

    @Override
    public boolean test() {
        return false;
    }
}
