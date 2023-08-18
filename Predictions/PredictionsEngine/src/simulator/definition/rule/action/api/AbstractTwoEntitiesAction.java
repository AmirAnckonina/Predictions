package simulator.definition.rule.action.api;

import simulator.definition.rule.action.utils.enums.eActionType;

public abstract class AbstractTwoEntitiesAction extends AbstractAction {
    private final String secondaryEntityName;

    public AbstractTwoEntitiesAction(eActionType type, String entityName, String secondaryEntityName) {
        super(type, entityName);
        this.secondaryEntityName = secondaryEntityName;
    }

    public String getSecondaryEntityName() {
        return secondaryEntityName;
    }
}
