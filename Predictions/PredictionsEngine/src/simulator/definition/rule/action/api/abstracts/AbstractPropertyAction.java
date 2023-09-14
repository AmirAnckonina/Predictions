package simulator.definition.rule.action.api.abstracts;

import enums.ActionType;

public abstract class AbstractPropertyAction extends AbstractAction {

    protected String propertyName;

    public AbstractPropertyAction(ActionType type, String entityName, String propertyName) {
        super(type, entityName);
        this.propertyName = propertyName;
    }
}
