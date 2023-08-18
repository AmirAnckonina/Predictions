package simulator.definition.rule.action.api;

import simulator.definition.rule.action.expression.api.interfaces.Expression;
import simulator.definition.rule.action.utils.enums.eActionType;

/**
 * Be aware that result-prop field is depending on AbrtactProprtyAction propName field
 * consider seperate the inheritance from AbstractPropertyAction
 */
public abstract class AbstractCalculationAction extends AbstractPropertyAction {
    protected final Expression arg1;
    protected final Expression arg2;

    public AbstractCalculationAction(eActionType type, String entityName, String propertyName, Expression arg1, Expression arg2) {
        super(type, entityName, propertyName);
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
}
