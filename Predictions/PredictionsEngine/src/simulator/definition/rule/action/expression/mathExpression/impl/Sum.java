package simulator.definition.rule.action.expression.mathExpression.impl;

import simulator.definition.rule.action.expression.mathExpression.api.BinaryMathExpression;
import simulator.definition.rule.action.expression.mathExpression.api.MathExpression;
import simulator.definition.rule.action.expression.mathExpression.enums.MathOperator;

public class Sum extends BinaryMathExpression {
    public Sum(MathExpression expression1, MathExpression expression2, MathOperator operator) {
        super(expression1, expression2, operator);
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
