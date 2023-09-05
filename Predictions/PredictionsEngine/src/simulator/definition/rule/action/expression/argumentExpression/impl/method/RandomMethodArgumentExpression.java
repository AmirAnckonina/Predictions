package simulator.definition.rule.action.expression.argumentExpression.impl.method;

import simulator.definition.property.utils.enums.PropertyType;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.ExpressionMethodType;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractMethodArgumentExpression;
import simulator.execution.context.api.ExecutionContext;

import java.util.Random;


public class RandomMethodArgumentExpression extends AbstractMethodArgumentExpression {
    private Integer maxRandomValue;
    public RandomMethodArgumentExpression(ExpressionMethodType method, PropertyType expressionReturnedValueType, Integer maxRandomValue) {
        super(method, expressionReturnedValueType);
        this.maxRandomValue = maxRandomValue;
    }

    @Override
    public Integer getValue(ExecutionContext context) {
        return (new Random()).nextInt(this.maxRandomValue);
    }


}
