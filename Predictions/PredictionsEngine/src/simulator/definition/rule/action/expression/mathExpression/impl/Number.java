package simulator.definition.rule.action.expression.mathExpression.impl;

import simulator.definition.rule.action.expression.mathExpression.api.MathExpression;

public class Number implements MathExpression {
    private double num;
    public Number(double num) {

        this.num = num;
    }

    @Override
    public double evaluate() {
        return num;
    }

    @Override
    public String getOperationSign() {
        return null;
    }
}
