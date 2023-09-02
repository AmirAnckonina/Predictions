package simulator.definition.rule.action.api.abstracts;

import simulator.definition.rule.action.api.interfaces.Action;
import simulator.definition.rule.action.secondaryEntity.api.ActionSecondaryEntityDefinition;
import simulator.definition.rule.action.utils.enums.eActionType;

import java.util.Optional;

public abstract class AbstractAction implements Action {
    private final eActionType type;
    private final String primaryEntityName;
    private ActionSecondaryEntityDefinition actionSecondaryEntityDefinition;

    public AbstractAction(eActionType type, String primaryEntityName) {
        this(type, primaryEntityName, null);
    }

    public AbstractAction(eActionType type, String primaryEntityName, ActionSecondaryEntityDefinition actionSecondaryEntityDefinition) {
        this.type = type;
        this.primaryEntityName = primaryEntityName;
        this.actionSecondaryEntityDefinition = actionSecondaryEntityDefinition;
    }


    @Override
    public eActionType getType() {
        return type;
    }

    @Override
    public String getPrimaryEntityName() {
        return primaryEntityName;
    }

    @Override
    public Optional<ActionSecondaryEntityDefinition> getActionSecondaryEntityDefinition() {
        return Optional.ofNullable(this.actionSecondaryEntityDefinition);
    }
}
