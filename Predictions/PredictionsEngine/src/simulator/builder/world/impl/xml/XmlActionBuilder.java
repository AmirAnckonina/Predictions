package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDAction;
import simulator.builder.world.api.AbstractComponentBuilder;
import simulator.builder.world.api.ActionBuilder;
import simulator.builder.world.impl.BaseExpressionBuilder;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.builder.world.validator.api.WorldBuilderContextValidator;
import simulator.definition.rule.action.api.AbstractAction;
import simulator.definition.rule.action.api.AbstractCalculationAction;
import simulator.definition.rule.action.expression.api.Expression;
import simulator.definition.rule.action.impl.*;
import simulator.definition.rule.action.utils.eActionType;


public class XmlActionBuilder extends AbstractComponentBuilder implements ActionBuilder {
    PRDAction generatedAction;

    public XmlActionBuilder(PRDAction generatedAction, WorldBuilderContextValidator contextValidator) {
        super(contextValidator);
        this.generatedAction = generatedAction;
    }


    @Override
    public AbstractAction BuildAction() {
        eActionType actionType = eActionType.valueOf(generatedAction.getType());

        switch(actionType) {
            case INCREASE:
                return buildIncreaseAction();

            case DECREASE:
                return buildDivideAction();

            case CALCULATION:
                return buildCalculationAction();

            case CONDITION:
                return buildConditionAction();

            case SET:
                return buildSetAction();

            case KILL:
                return buildKillAction();

            case REPLACE:
            case PROXIMITY:
            default:
                throw new WorldBuilderException("Unsupported action type.");

        }
    }

    @Override
    public IncreaseAction buildIncreaseAction() {
        eActionType actionType = eActionType.INCREASE;
        String entityName =   generatedAction.getEntity();
        String entityPropertyName = generatedAction.getProperty();
        Expression by =
                new BaseExpressionBuilder(contextValidator).buildExpression(generatedAction.getBy());

        return new IncreaseAction(actionType, entityName, entityPropertyName, by);
    }

    @Override
    public DecreaseAction buildDecreaseAction() {
        eActionType actionType = eActionType.DECREASE;
        String entityName =   generatedAction.getEntity();
        String entityPropertyName = generatedAction.getProperty();
        Expression by =
                new BaseExpressionBuilder(contextValidator).buildExpression(generatedAction.getBy());

        return new DecreaseAction(actionType, entityName, entityPropertyName, by);
    }

    @Override
    public AbstractCalculationAction buildCalculationAction() {
        if(generatedAction.getPRDMultiply() != null) {
            return buildMultiplyAction();

        } else if (generatedAction.getPRDDivide() != null) {
            return buildDivideAction();

        } else {
            throw new WorldBuilderException("Unsupported calculation action type.");
        }
    }

    @Override
    public MultiplyAction buildMultiplyAction() {

        Expression arg1expression =
                new BaseExpressionBuilder(contextValidator)
                        .buildExpression(generatedAction.getPRDMultiply().getArg1());

        Expression arg2expression =
                new BaseExpressionBuilder(contextValidator)
                        .buildExpression(generatedAction.getPRDMultiply().getArg2());

        return new MultiplyAction(
                eActionType.MULTIPLY,
                generatedAction.getEntity(),
                generatedAction.getProperty(),
                arg1expression,
                arg2expression);
    }

    @Override
    public DivideAction buildDivideAction() {

        Expression arg1expression =
                new BaseExpressionBuilder(contextValidator)
                        .buildExpression(generatedAction.getPRDDivide().getArg1());

        Expression arg2expression =
                new BaseExpressionBuilder(contextValidator)
                        .buildExpression(generatedAction.getPRDDivide().getArg2());

        return new DivideAction(
                eActionType.DIVIDE,
                generatedAction.getEntity(),
                generatedAction.getProperty(),
                arg1expression,
                arg2expression);
    }

    @Override
    public ConditionAction buildConditionAction() {
        return null;
    }

    @Override
    public SetAction buildSetAction() {
        Expression argValueExpression =
                new BaseExpressionBuilder(contextValidator)
                        .buildExpression(generatedAction.getValue());
        new SetAction(
                eActionType.SET,
                generatedAction.getEntity(),
                generatedAction.getProperty(),
                argValueExpression);
    }

    @Override
    public KillAction buildKillAction() {
        return new KillAction(eActionType.KILL, generatedAction.getEntity());
    }
}
