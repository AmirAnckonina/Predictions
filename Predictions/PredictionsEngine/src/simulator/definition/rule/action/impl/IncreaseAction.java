package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.abstracts.AbstractPropertyAction;
import simulator.definition.rule.action.expression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.utils.enums.eActionType;
import simulator.execution.context.api.ExecutionContext;


public class IncreaseAction extends AbstractPropertyAction {
    ArgumentExpression by;
    public IncreaseAction(eActionType type, String entityName, String propertyNane, ArgumentExpression by) {
        super(type, entityName, propertyNane);
        this.by = by;
    }

    @Override
    public void invoke(ExecutionContext context) {
        //context.getProperyInstance(propertyName).value + by.getValue(context);
    }
}
