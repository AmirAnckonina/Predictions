package simulator.definition.rule.action.expression.conditionExpression.impl.mutiple;

import simulator.definition.rule.action.expression.conditionExpression.api.abstracts.AbstractMultipleConditionExpression;
import simulator.definition.rule.action.expression.conditionExpression.api.interfaces.ConditionExpression;
import simulator.execution.context.api.ExecutionContext;

import java.util.List;

public class OrMultipleConditionExpression extends AbstractMultipleConditionExpression {

    public OrMultipleConditionExpression(List<ConditionExpression> conditions) {
        super(conditions);
    }

    @Override
    public String toString() {
        return "Condition{" +
                "conditionType=multi" +
                ", operator=or" +
                ", conditions=" + conditions.size() +
                '}';
    }

    @Override
    public boolean test(ExecutionContext context) {
        boolean result = false;
        for(ConditionExpression conditionExpression:this.conditions){
            if(conditionExpression.test(context)){
                result = true;
                break;
            }
        }

        return result;
    }
}
