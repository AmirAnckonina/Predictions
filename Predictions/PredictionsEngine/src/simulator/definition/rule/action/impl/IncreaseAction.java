package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.AbstractPropertyAction;
import simulator.definition.rule.action.expression.api.interfaces.Expression;
import simulator.definition.rule.action.utils.enums.eActionType;
import simulator.execution.context.api.ExecutionContext;


public class IncreaseAction extends AbstractPropertyAction {
    Expression by;
    public IncreaseAction(eActionType type, String entityName, String propertyNane, Expression by) {
        super(type, entityName, propertyNane);
        this.by = by;
    }

    @Override
    public void invoke(ExecutionContext context) {
        //context.getProperyInstance(propertyName).value + by.getValue(context);
    }
}
