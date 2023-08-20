package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.abstracts.AbstractAction;
import simulator.definition.rule.action.conditionExpression.api.interfaces.ConditionExpression;
import simulator.definition.rule.action.utils.enums.eActionType;
import simulator.execution.context.api.ExecutionContext;

import java.util.List;

public class ConditionAction extends AbstractAction {
    private ConditionExpression condition;
    private List<AbstractAction> thenActions;
    private List<AbstractAction> elseActions;

    public ConditionAction(
            eActionType type,
            String entityName,
            ConditionExpression condition,
            List<AbstractAction> thenActions,
            List<AbstractAction> elseActions) {

        super(type, entityName);
        this.condition = condition;
        this.thenActions = thenActions;
        this.elseActions = elseActions;
    }

    @Override
    public void invoke(ExecutionContext executionContext) {
        if (condition.test(executionContext)) {

            for (AbstractAction action : thenActions) {
                try {
                    action.invoke(executionContext);
                } catch (Exception e) {

                }
            }

        } else {

            for (AbstractAction action : elseActions) {
                try {
                    action.invoke(executionContext);
                } catch (Exception e) {
                }
            }
        }
    }
}
