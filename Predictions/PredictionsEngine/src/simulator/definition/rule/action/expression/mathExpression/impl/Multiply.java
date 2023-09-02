package simulator.definition.rule.action.expression.mathExpression.impl;

import simulator.definition.rule.action.expression.mathExpression.api.BinaryMathExpression;
import simulator.definition.rule.action.expression.mathExpression.api.MathExpression;
import simulator.definition.rule.action.expression.mathExpression.enums.eMathOperator;

public class Multiply extends BinaryMathExpression {

    public Multiply(MathExpression arg1, MathExpression arg2, eMathOperator operator) {
        super(arg1, arg2, operator);
    }

    @Override
    protected double evaluate(double evaluate, double evaluate2) {
        return 0;
    }

    @Override
    public String getOperationSign() {
        return null;
    }
}
