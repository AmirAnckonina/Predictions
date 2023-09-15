package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.abstracts.AbstractAction;
import enums.ActionType;
import simulator.execution.context.api.ExecutionContext;

public class KillAction extends AbstractAction {
    public KillAction(ActionType type, String entityName) {
        super(type, entityName);
    }

    @Override
    public String toString() {
        return "Kill{" +
                "type=" + type +
                ", primaryEntityName='" + primaryEntityName + '\'' +
                ", actionSecondaryEntityDefinition=" + actionSecondaryEntityDefinition +
                '}' + System.lineSeparator();
    }

    @Override
    public void invoke(ExecutionContext executionContext) {
        executionContext
                .getEntityInstanceByName(this.primaryEntityName)
                .killEntity();
    }
}
