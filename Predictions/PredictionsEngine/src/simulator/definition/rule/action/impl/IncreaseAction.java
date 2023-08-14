package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.AbstractEntityPropertyAction;
import simulator.definition.rule.action.utils.eActionType;
import simulator.execution.context.api.Context;

import java.beans.Expression;

public class IncreaseAction extends AbstractEntityPropertyAction {
    Expression by;

    public IncreaseAction(eActionType type, String entityName, String propertyNane, Expression by) {
        super(type, entityName, propertyNane);
        this.by = by;
    }

    @Override
    public void invoke(Context context) {

    }
}
