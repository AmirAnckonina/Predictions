package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.abstracts.AbstractPropertyAction;
import simulator.definition.rule.action.expression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.utils.enums.eActionType;
import simulator.execution.context.api.ExecutionContext;

public class SetAction extends AbstractPropertyAction {
    ArgumentExpression value;

    public SetAction(eActionType type, String entityName, String propertyName, ArgumentExpression value) {
        super(type, entityName, propertyName);
        this.value = value;
    }

    @Override
    public void invoke(ExecutionContext context) {

    }
}
