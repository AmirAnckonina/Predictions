package simulator.definition.rule.action.api.abstracts;

import simulator.definition.rule.action.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.utils.enums.eActionType;

/**
 * Be aware that result-prop field is depending on AbrtactProprtyAction propName field
 * consider seperate the inheritance from AbstractPropertyAction
 */
public abstract class AbstractCalculationAction extends AbstractPropertyAction {
    protected final ArgumentExpression arg1;
    protected final ArgumentExpression arg2;

    public AbstractCalculationAction(eActionType type, String entityName, String propertyName, ArgumentExpression arg1, ArgumentExpression arg2) {
        super(type, entityName, propertyName);
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
}
