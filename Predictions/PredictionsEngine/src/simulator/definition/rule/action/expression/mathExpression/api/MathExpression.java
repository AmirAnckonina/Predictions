package simulator.definition.rule.action.expression.mathExpression.api;

public interface MathExpression {

    /**
     * evaluate the expression and return the result
     *
     * @return the results of the expression
     */
    double evaluate();

    String getOperationSign();
}
