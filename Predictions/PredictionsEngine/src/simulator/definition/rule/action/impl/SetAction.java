package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.AbstractPropertyAction;
import simulator.definition.rule.action.expression.api.Expression;
import simulator.definition.rule.action.utils.eActionType;
import simulator.execution.context.api.Context;

public class SetAction extends AbstractPropertyAction {
    Expression value;

    public SetAction(eActionType type, String entityName, String propertyName, Expression value) {
        super(type, entityName, propertyName);
        this.value = value;
    }

    @Override
    public void invoke(Context context) {

    }
}