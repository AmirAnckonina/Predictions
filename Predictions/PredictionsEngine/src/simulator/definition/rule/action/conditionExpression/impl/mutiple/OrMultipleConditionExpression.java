package simulator.definition.rule.action.conditionExpression.impl.mutiple;

import simulator.definition.rule.action.conditionExpression.api.abstracts.AbstractMultipleConditionExpression;
import simulator.definition.rule.action.conditionExpression.api.abstracts.AbstractSingleConditionExpression;
import simulator.definition.rule.action.conditionExpression.api.interfaces.ConditionExpression;
import simulator.execution.context.api.ExecutionContext;

import java.util.List;

public class OrMultipleConditionExpression extends AbstractMultipleConditionExpression {

    public OrMultipleConditionExpression(List<ConditionExpression> conditions) {
        super(conditions);
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
