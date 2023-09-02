package simulator.definition.rule.action.expression.argumentExpression.impl;


import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.execution.context.api.ExecutionContext;

public class PropertyNameArgumentExpression implements ArgumentExpression {

    private String propertyName;

    public PropertyNameArgumentExpression(String propertyName) {

        this.propertyName = propertyName;
    }

    @Override
    public Object getValue(ExecutionContext context) {
        return context.getPrimaryEntityInstance().getPropertyByName(propertyName).getValue();
        //throw new SimulatorRunnerException("Not impl propertyArgExp...");
    }
}
