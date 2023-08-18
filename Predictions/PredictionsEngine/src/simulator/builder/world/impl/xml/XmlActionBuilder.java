package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDAction;
import simulator.builder.world.api.abstracts.AbstractComponentBuilder;
import simulator.builder.world.api.interfaces.ActionBuilder;
import simulator.builder.world.impl.baseImpl.BaseArgumentExpressionBuilder;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.builder.world.validator.api.WorldBuilderContextValidator;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.api.abstracts.AbstractAction;
import simulator.definition.rule.action.api.abstracts.AbstractCalculationAction;
import simulator.definition.rule.action.conditionExpression.api.interfaces.ConditionExpression;
import simulator.definition.rule.action.expression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.impl.*;
import simulator.definition.rule.action.utils.enums.eActionType;

import java.util.List;


public class XmlActionBuilder extends AbstractComponentBuilder implements ActionBuilder {
    private PRDAction generatedAction;

    public XmlActionBuilder(PRDAction generatedAction, WorldBuilderContextValidator contextValidator) {
        super(contextValidator);
        this.generatedAction = generatedAction;
    }


    @Override
    public AbstractAction BuildAction() {
        boolean actionContextIsValid = validateActionContextProcedure();
        if (actionContextIsValid) {
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

        } else {
            throw new WorldBuilderException("Action entity context doesn't matched the existing entity");
        }
    }

    private boolean validateActionContextProcedure() {
        boolean entityContextValid = contextValidator.validateActionEntityContext(generatedAction.getEntity());

        boolean entityPropertyValid;
        if (generatedAction.getProperty() != null) {
            entityPropertyValid =
                    contextValidator.validateActionEntityPropertyContext(
                            generatedAction.getEntity(), generatedAction.getProperty());
        }
        else { entityPropertyValid = true; }

        return entityContextValid && entityPropertyValid;
    }
    @Override
    public IncreaseAction buildIncreaseAction() {
        eActionType actionType = eActionType.INCREASE;
        String entityName =   generatedAction.getEntity();
        String entityPropertyName = generatedAction.getProperty();
        ePropertyType entityPropertyType = contextValidator.getPropertyType(entityPropertyName);
        ArgumentExpression by =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(generatedAction.getBy(), entityPropertyType);

        return new IncreaseAction(actionType, entityName, entityPropertyName, by);
    }

    @Override
    public DecreaseAction buildDecreaseAction() {
        eActionType actionType = eActionType.DECREASE;
        String entityName =   generatedAction.getEntity();
        String entityPropertyName = generatedAction.getProperty();
        ePropertyType entityPropertyType = contextValidator.getPropertyType(entityPropertyName);
        ArgumentExpression by =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(generatedAction.getBy() , entityPropertyType);

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

        ePropertyType propType = contextValidator.getPropertyType(generatedAction.getProperty());
        ArgumentExpression arg1ArgumentExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                generatedAction.getPRDMultiply().getArg1(), propType);

        ArgumentExpression arg2ArgumentExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                generatedAction.getPRDMultiply().getArg2(), propType);

        return new MultiplyAction(
                eActionType.MULTIPLY,
                generatedAction.getEntity(),
                generatedAction.getProperty(),
                arg1ArgumentExpression,
                arg2ArgumentExpression);
    }

    @Override
    public DivideAction buildDivideAction() {

        ePropertyType propType = contextValidator.getPropertyType(generatedAction.getProperty());
        ArgumentExpression arg1ArgumentExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                generatedAction.getPRDDivide().getArg1() ,propType);

        ArgumentExpression arg2ArgumentExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(generatedAction.getPRDDivide().getArg2(), propType);

        return new DivideAction(
                eActionType.DIVIDE,
                generatedAction.getEntity(),
                generatedAction.getProperty(),
                arg1ArgumentExpression,
                arg2ArgumentExpression);
    }

    @Override
    public ConditionAction buildConditionAction() {

        // build ConditionExpression by sending root generatedCondition
        ConditionExpression conditionExpression =
                new XmlConditionExpressionBuilder(
                        generatedAction.getPRDCondition(), contextValidator)
                        .buildConditionExpression();

        // build Then - it's like to build Action;
        List<AbstractAction> thenActions =
                new XmlActionListBuilder(
                        generatedAction.getPRDThen().getPRDAction(), contextValidator)
                        .buildActions();

        // build Else - it's like to build Action
        List<AbstractAction> elseActions = null;
        if (generatedAction.getPRDElse() != null) {

            elseActions =
                    new XmlActionListBuilder(generatedAction.getPRDElse().getPRDAction(), contextValidator)
                            .buildActions();
        }

        return new ConditionAction(
                eActionType.CONDITION,
                generatedAction.getEntity(),
                conditionExpression,
                thenActions,
                elseActions);
    }

    @Override
    public SetAction buildSetAction() {
        ePropertyType propType = contextValidator.getPropertyType(generatedAction.getProperty());
        ArgumentExpression argValueArgumentExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(generatedAction.getValue(), propType);
        return new SetAction(
                eActionType.SET,
                generatedAction.getEntity(),
                generatedAction.getProperty(),
                argValueArgumentExpression);
    }

    @Override
    public KillAction buildKillAction() {
        return new KillAction(eActionType.KILL, generatedAction.getEntity());
    }
}
