package simulator.builder.impl.xml;

import resources.jaxb.schema.generated.PRDAction;
import simulator.builder.api.interfaces.ActionBuilder;
import simulator.builder.impl.baseImpl.BaseArgumentExpressionBuilder;
import simulator.builder.utils.ArgExpressionTypeDemands;
import simulator.builder.utils.eMandatoryTypeDemanding;
import simulator.builder.utils.exception.WorldBuilderException;
import simulator.builder.api.abstracts.AbstractComponentBuilder;
import simulator.builder.validator.api.WorldBuilderContextValidator;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.api.abstracts.AbstractAction;
import simulator.definition.rule.action.api.abstracts.AbstractCalculationAction;
import simulator.definition.rule.action.api.interfaces.Action;
import simulator.definition.rule.action.expression.conditionExpression.api.interfaces.ConditionExpression;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.impl.*;
import simulator.definition.rule.action.secondaryEntity.api.ActionSecondaryEntityDefinition;
import simulator.definition.rule.action.utils.enums.eActionType;
import simulator.definition.rule.action.utils.enums.eReplaceActionCreationMode;

import java.util.List;


public class XmlActionBuilder extends AbstractComponentBuilder implements ActionBuilder {
    private PRDAction generatedAction;

    public XmlActionBuilder(PRDAction generatedAction, WorldBuilderContextValidator contextValidator) {
        super(contextValidator);
        this.generatedAction = generatedAction;
    }


    @Override
    public AbstractAction BuildAction() {

        boolean actionContextIsValid =
                contextValidator.validateActionContextProcedure(
                        generatedAction.getEntity(), generatedAction.getProperty()
                );

        if (actionContextIsValid) {

            if (generatedAction.getPRDSecondaryEntity() != null) {
                ActionSecondaryEntityDefinition actionSecondaryEntityDefinition =
                        new XmlActionSecondaryEntityBuilder(
                                generatedAction.getPRDSecondaryEntity(),
                                contextValidator
                                ).buildActionSecondaryEntity();
            }

            eActionType actionType = eActionType.valueOf(generatedAction.getType().toUpperCase());

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
                    return buildReplaceAction();

                case PROXIMITY:
                    return buildProximityAction();

                    default:
                    throw new WorldBuilderException("Unsupported action type.");

            }

        } else {
            throw new WorldBuilderException(
                    "For Action " +
                    generatedAction.getType() +
                            ", the entity " + generatedAction.getEntity() + ", context doesn't matched");
        }
    }

    @Override
    public IncreaseAction buildIncreaseAction() {
        eActionType actionType = eActionType.INCREASE;
        String entityName =   generatedAction.getEntity();
        String entityPropertyName = generatedAction.getProperty();
        ePropertyType entityPropertyType = contextValidator.getEntityPropertyType(
                entityName, entityPropertyName
        );
        ArgumentExpression by =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                generatedAction.getBy(),
                                new ArgExpressionTypeDemands(entityPropertyType)
                        );

        return new IncreaseAction(actionType, entityName, entityPropertyName, by);
    }

    @Override
    public DecreaseAction buildDecreaseAction() {
        eActionType actionType = eActionType.DECREASE;
        String entityName =   generatedAction.getEntity();
        String entityPropertyName = generatedAction.getProperty();
        ePropertyType entityPropertyType =
                contextValidator.getEntityPropertyType(
                        entityName, entityPropertyName
        );
        ArgumentExpression by =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                generatedAction.getBy() ,
                                new ArgExpressionTypeDemands(entityPropertyType)
                        );

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

        ePropertyType propType = contextValidator.getEntityPropertyType(
                generatedAction.getEntity(),
                generatedAction.getProperty()
        );

        ArgumentExpression arg1ArgumentExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                generatedAction.getPRDMultiply().getArg1(),
                                new ArgExpressionTypeDemands(propType)
                        );

        ArgumentExpression arg2ArgumentExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                generatedAction.getPRDMultiply().getArg2(),
                                new ArgExpressionTypeDemands(propType)
                        );

        return new MultiplyAction(
                eActionType.MULTIPLY,
                generatedAction.getEntity(),
                generatedAction.getProperty(),
                arg1ArgumentExpression,
                arg2ArgumentExpression);
    }

    @Override
    public DivideAction buildDivideAction() {

        ePropertyType propType = contextValidator.getEntityPropertyType(
                generatedAction.getEntity(),
                generatedAction.getProperty()
        );
        ArgumentExpression arg1ArgumentExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                generatedAction.getPRDDivide().getArg1(),
                                new ArgExpressionTypeDemands(propType)
                        );

        ArgumentExpression arg2ArgumentExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                generatedAction.getPRDDivide().getArg2(),
                                new ArgExpressionTypeDemands(propType)
                        );

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
        ePropertyType propType = contextValidator.getEntityPropertyType(
                generatedAction.getEntity(),
                generatedAction.getProperty()
        );
        ArgumentExpression argValueArgumentExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(generatedAction.getValue(),
                                new ArgExpressionTypeDemands(propType)
                        );
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

    @Override
    public ProximityAction buildProximityAction() {

        String sourceEntity = generatedAction.getPRDBetween().getSourceEntity();
        String targetEntity = generatedAction.getPRDBetween().getTargetEntity();

        boolean srcValid = contextValidator.validateActionEntityContext(sourceEntity);
        boolean targetValid = contextValidator.validateActionEntityContext(targetEntity);

        if (!srcValid || !targetValid) {
            throw new WorldBuilderException("the given source or target entity under proximity action is invalid.");
        }

        String rawEnvDepth = generatedAction.getPRDEnvDepth().getOf();
        ArgumentExpression envDepth =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                rawEnvDepth,
                                new ArgExpressionTypeDemands(eMandatoryTypeDemanding.NUMERIC)
                        );

        List<AbstractAction> actionsUnderProximity =
                new XmlActionListBuilder(
                        generatedAction.getPRDActions().getPRDAction(), contextValidator)
                        .buildActions();

        return new ProximityAction(eActionType.PROXIMITY, sourceEntity, targetEntity, envDepth, actionsUnderProximity);
    }

    @Override
    public ReplaceAction buildReplaceAction() {

        String killEntity = generatedAction.getKill();
        String createEntity = generatedAction.getCreate();
        eReplaceActionCreationMode creationMode
                = eReplaceActionCreationMode.valueOf(generatedAction.getMode().toUpperCase());

        return new ReplaceAction(eActionType.REPLACE, killEntity, createEntity, creationMode);
    }
}
