package simulator.definition.rule.action.api;

import simulator.definition.rule.action.utils.eActionType;

public abstract class AbstractEntityPropertyAction extends AbstractAction {
    private String propertyNane;

    public AbstractEntityPropertyAction(eActionType type, String entityName, String propertyNane) {
        super(type, entityName);
        this.propertyNane = propertyNane;
    }

    public String getPropertyNane() {
        return propertyNane;
    }
}
