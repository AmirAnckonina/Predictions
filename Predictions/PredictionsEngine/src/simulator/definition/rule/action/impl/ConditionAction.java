package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.abstracts.AbstractAction;
import simulator.definition.rule.action.expression.conditionExpression.api.interfaces.ConditionExpression;
import enums.ActionType;
import simulator.execution.context.api.ExecutionContext;

import java.util.List;

public class ConditionAction extends AbstractAction {
    private ConditionExpression condition;
    private List<AbstractAction> thenActions;
    private List<AbstractAction> elseActions;

    @Override
    public String toString() {

        String resVal;
        String fixThenActions = (thenActions != null)?Integer.toString(thenActions.size()):"0";
        String fixElseActions = (elseActions != null)?Integer.toString(elseActions.size()):"0";

        if (actionSecondaryEntityDefinition == null) {

            resVal = "Condition{" +
                    "condition=" + condition +
                    ", thenActions=" + fixThenActions +
                    ", elseActions=" + fixElseActions +
                    ", type=" + type +
                    ", primaryEntityName='" + primaryEntityName + '\'' +
                    ", actionSecondaryEntityDefinition=" +  " - " +
                    '}' + System.lineSeparator();
        } else {
            resVal = "Condition{" +
                    "condition=" + condition +
                    ", thenActions=" + fixThenActions +
                    ", elseActions=" + fixElseActions +
                    ", type=" + type +
                    ", primaryEntityName='" + primaryEntityName + '\'' +
                    ", actionSecondaryEntityDefinition=" + actionSecondaryEntityDefinition +
                    '}' + System.lineSeparator();
        }
        return resVal;
    }

    public ConditionAction(
            ActionType type,
            String entityName,
            ConditionExpression condition,
            List<AbstractAction> thenActions,
            List<AbstractAction> elseActions) {

        super(type, entityName);
        this.condition = condition;
        this.thenActions = thenActions;
        this.elseActions = elseActions;
    }

    public ConditionAction(ActionType type, String entityName, ConditionExpression condition, List<AbstractAction> thenActions) {
        this(type, entityName, condition, thenActions, null);
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

        } else if (elseActions != null) {

            for (AbstractAction action : elseActions) {
                try {
                    action.invoke(executionContext);
                } catch (Exception e) {
                }
            }
        }
    }
}
