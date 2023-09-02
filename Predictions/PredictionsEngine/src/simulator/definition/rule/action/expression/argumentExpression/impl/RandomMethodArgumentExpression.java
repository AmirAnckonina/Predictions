package simulator.definition.rule.action.expression.argumentExpression.impl;

import simulator.definition.rule.action.expression.argumentExpression.utils.enums.eExpressionMethod;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractMethodArgumentExpression;
import simulator.execution.context.api.ExecutionContext;

import java.util.Random;


public class RandomMethodArgumentExpression extends AbstractMethodArgumentExpression {
    private Integer maxRandomValue;
    public RandomMethodArgumentExpression(eExpressionMethod method, Integer maxRandomValue) {
        super(method);
        this.maxRandomValue = maxRandomValue;
    }

    @Override
    public Integer getValue(ExecutionContext context) {
        return (new Random()).nextInt(this.maxRandomValue);
    }


}
