package simulator.definition.rule.action.api.abstracts;

import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import enums.ActionType;

/**
 * Be aware that result-prop field is depending on AbrtactProprtyAction propName field
 * consider seperate the inheritance from AbstractPropertyAction
 */
public abstract class AbstractCalculationAction extends AbstractPropertyAction {
    protected final ArgumentExpression arg1;
    protected final ArgumentExpression arg2;

    @Override
    public String toString() {
        return "AbstractCalculation{" +
                "arg1=" + arg1 +
                ", arg2=" + arg2 +
                '}';
    }

    public AbstractCalculationAction(ActionType type, String entityName, String propertyName, ArgumentExpression arg1, ArgumentExpression arg2) {
        super(type, entityName, propertyName);
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
}
