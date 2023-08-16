package simulator.definition.rule.action.api;

import simulator.definition.rule.action.utils.eActionType;

public abstract class AbstractPropertyAction extends AbstractAction {
    protected String propertyName;

    public AbstractPropertyAction(eActionType type, String entityName, String propertyName) {
        super(type, entityName);
        this.propertyName = propertyName;
    }
}
