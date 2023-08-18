package simulator.definition.rule.action.api;

import simulator.definition.rule.action.utils.enums.eActionType;

public abstract class AbstractAction implements Action {
    private final eActionType type;
    private final String entityName;

    public AbstractAction(eActionType type, String entityName) {
        this.type = type;
        this.entityName = entityName;
    }

    @Override
    public eActionType getType() {
        return type;
    }

    @Override
    public String getEntityName() {
        return entityName;
    }
}
