package simulator.definition.rule.action.expression.argumentExpression.impl.method;

import enums.PropertyType;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.ExpressionMethodType;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractMethodArgumentExpression;
import simulator.execution.context.api.ExecutionContext;

public class PercentMethodArgumentExpression extends AbstractMethodArgumentExpression {

    private ArgumentExpression originValueArg;
    private ArgumentExpression partValueArg;
    public PercentMethodArgumentExpression(
            ExpressionMethodType method, PropertyType expressionReturnedValueType, ArgumentExpression originalValue, ArgumentExpression percentage) {

        super(method, expressionReturnedValueType);
        this.originValueArg = originalValue;
        this.partValueArg = percentage;
    }

    @Override
    public Object getValue(ExecutionContext context) {
        Float originVal = (Float) PropertyType.FLOAT.convert(partValueArg.getValue(context));
        Float partVal = (Float) PropertyType.FLOAT.convert(partValueArg.getValue(context));
        double percentageRes = ((partVal / originVal) * 100);
        return percentageRes;
    }

}
