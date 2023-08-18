package simulator.definition.rule.action.mathExpression.api;

import simulator.definition.rule.action.mathExpression.enums.eMathOperator;

public abstract class BinaryMathExpression implements MathExpression {
    private MathExpression expression1;
    private MathExpression expression2;
    private eMathOperator operator;

    public BinaryMathExpression(MathExpression expression1, MathExpression expression2, eMathOperator operator) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public double evaluate() {
        return evaluate(expression1.evaluate(), expression2.evaluate());
    }

    abstract protected double evaluate(double evaluate, double evaluate2);
}
