package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.AbstractPropertyAction;
import simulator.definition.rule.action.utils.eActionType;
import simulator.execution.context.api.Context;
import simulator.definition.rule.action.expression.api.Expression;


public class IncreaseAction extends AbstractPropertyAction {
    Expression by;
    public IncreaseAction(eActionType type, String entityName, String propertyNane, Expression by) {
        super(type, entityName, propertyNane);
        this.by = by;
    }

    @Override
    public void invoke(Context context) {
        //
    }
}
