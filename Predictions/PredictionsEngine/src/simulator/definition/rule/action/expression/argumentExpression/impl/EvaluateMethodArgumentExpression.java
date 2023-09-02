package simulator.definition.rule.action.expression.argumentExpression.impl;

import com.sun.deploy.panel.IProperty;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractEntityPropertyMethodArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.eExpressionMethod;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractMethodArgumentExpression;
import simulator.execution.context.api.ExecutionContext;
import simulator.runner.utils.exceptions.SimulatorRunnerException;


public class EvaluateMethodArgumentExpression extends AbstractEntityPropertyMethodArgumentExpression {


    public EvaluateMethodArgumentExpression(eExpressionMethod method, String entityName, String propertyName) {
        super(method, entityName, propertyName);
    }

    @Override
    public Object getValue(ExecutionContext context) {
        throw new SimulatorRunnerException("NotImpl evaluate method expression");
    }

}
