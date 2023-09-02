package simulator.definition.rule.action.api.interfaces;

import simulator.definition.rule.action.secondaryEntity.api.ActionSecondaryEntityDefinition;
import simulator.definition.rule.action.utils.enums.eActionType;
import simulator.execution.context.api.ExecutionContext;

import java.util.Optional;

public interface Action {
    void invoke(ExecutionContext executionContext);
    eActionType getType();
    String getPrimaryEntityName();
    Optional<ActionSecondaryEntityDefinition> getActionSecondaryEntityDefinition();
}
