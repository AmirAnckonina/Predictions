package simulator.definition.rule.action.api.interfaces;

import simulator.definition.rule.action.secondaryEntity.api.ActionSecondaryEntityDefinition;
import enums.ActionType;
import simulator.execution.context.api.ExecutionContext;

import java.util.Optional;

public interface Action {
    void invoke(ExecutionContext executionContext);
    ActionType getType();
    String getPrimaryEntityName();
    Optional<ActionSecondaryEntityDefinition> getActionSecondaryEntityDefinition();
}
