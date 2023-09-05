package simulator.builder.impl.xml;

import resources.jaxb.schema.generated.PRDAction;
import simulator.builder.api.interfaces.ActionBuilder;
import simulator.builder.impl.baseImpl.BaseArgumentExpressionBuilder;
import simulator.builder.utils.ArgExpressionTypeDemands;
import simulator.builder.utils.MandatoryTypeDemanding;
import simulator.builder.utils.exception.WorldBuilderException;
import simulator.builder.api.abstracts.AbstractComponentBuilder;
import simulator.builder.validator.api.WorldBuilderContextValidator;
import simulator.definition.property.utils.enums.PropertyType;
import simulator.definition.rule.action.api.abstracts.AbstractAction;
import simulator.definition.rule.action.api.abstracts.AbstractCalculationAction;
import simulator.definition.rule.action.expression.conditionExpression.api.interfaces.ConditionExpression;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.impl.*;
import simulator.definition.rule.action.secondaryEntity.api.ActionSecondaryEntityDefinition;
import simulator.definition.rule.action.utils.enums.ActionType;
import simulator.definition.rule.action.utils.enums.ReplaceActionCreationMode;

import java.util.List;


public class XmlActionBuilder extends AbstractComponentBuilder implements ActionBuilder {
    private PRDAction generatedAction;

    public XmlActionBuilder(PRDAction generatedAction, WorldBuilderContextValidator contextValidator) {
        super(contextValidator);
        this.generatedAction = generatedAction;
    }


    @Override
    public AbstractAction BuildAction() {

        AbstractAction newAction;
        ActionSecondaryEntityDefinition actionSecondaryEntityDefinition = null;

        //Context Validation
        boolean actionContextIsValid;
        if (generatedAction.getEntity() != null) {

            actionContextIsValid = contextValidator.validateActionContextProcedure(
                            generatedAction.getEntity(), generatedAction.getProperty());

        } else if (generatedAction.getPRDBetween() != null || generatedAction.getKill() != null) {
            actionContextIsValid = true;
        } else {

            actionContextIsValid = false;
        }

        if (!actionContextIsValid) {
            throw new WorldBuilderException("For Action " + generatedAction.getType() +
                    ", the entity " + generatedAction.getEntity() + ", context doesn't matched");
        }

        // Secondary Entity Build.
        if (generatedAction.getPRDSecondaryEntity() != null) {
            actionSecondaryEntityDefinition = new XmlActionSecondaryEntityBuilder(
                    generatedAction.getPRDSecondaryEntity(), contextValidator)
                    .buildActionSecondaryEntity();
        }

        // Action build
        ActionType actionType = ActionType.valueOf(generatedAction.getType().toUpperCase());
        switch(actionType) {

            case INCREASE:
                newAction = buildIncreaseAction();
                break;

            case DECREASE:
                newAction = buildDecreaseAction();
                break;

            case CALCULATION:
                newAction = buildCalculationAction();
                break;

            case CONDITION:
                newAction = buildConditionAction();
                break;

            case SET:
                newAction = buildSetAction();
                break;

            case KILL:
                newAction = buildKillAction();
                break;

            case REPLACE:
                newAction = buildReplaceAction();
                break;

            case PROXIMITY:
                newAction = buildProximityAction();
                break;

            default:
                throw new WorldBuilderException("Unsupported action type.");

        }

        // SecondaryEntity Setup
        if (actionSecondaryEntityDefinition != null) {
            newAction.setActionSecondaryEntityDefinition(actionSecondaryEntityDefinition);
        }

        return newAction;

    }

    @Override
    public IncreaseAction buildIncreaseAction() {
        ActionType actionType = ActionType.INCREASE;
        String entityName =   generatedAction.getEntity();
        String entityPropertyName = generatedAction.getProperty();
        PropertyType entityPropertyType = contextValidator.getEntityPropertyType(
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
        ActionType actionType = ActionType.DECREASE;
        String entityName =   generatedAction.getEntity();
        String entityPropertyName = generatedAction.getProperty();
        PropertyType entityPropertyType =
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

        PropertyType propType = contextValidator.getEntityPropertyType(
                generatedAction.getEntity(),
                generatedAction.getResultProp()
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
                ActionType.MULTIPLY,
                generatedAction.getEntity(),
                generatedAction.getProperty(),
                arg1ArgumentExpression,
                arg2ArgumentExpression);
    }

    @Override
    public DivideAction buildDivideAction() {

        PropertyType propType = contextValidator.getEntityPropertyType(
                generatedAction.getEntity(),
                generatedAction.getResultProp()
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
                ActionType.DIVIDE,
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
                ActionType.CONDITION,
                generatedAction.getEntity(),
                conditionExpression,
                thenActions,
                elseActions);
    }

    @Override
    public SetAction buildSetAction() {
        PropertyType propType = contextValidator.getEntityPropertyType(
                generatedAction.getEntity(),
                generatedAction.getProperty()
        );
        ArgumentExpression argValueArgumentExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(generatedAction.getValue(),
                                new ArgExpressionTypeDemands(propType)
                        );
        return new SetAction(
                ActionType.SET,
                generatedAction.getEntity(),
                generatedAction.getProperty(),
                argValueArgumentExpression);
    }

    @Override
    public KillAction buildKillAction() {

        return new KillAction(ActionType.KILL, generatedAction.getEntity());
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
                                new ArgExpressionTypeDemands(
                                        PropertyType.FLOAT,
                                        MandatoryTypeDemanding.Mentioned
                                )
                        );

        List<AbstractAction> actionsUnderProximity =
                new XmlActionListBuilder(
                        generatedAction.getPRDActions().getPRDAction(), contextValidator)
                        .buildActions();

        return new ProximityAction(ActionType.PROXIMITY, sourceEntity, targetEntity, envDepth, actionsUnderProximity);
    }

    @Override
    public ReplaceAction buildReplaceAction() {

        String killEntity = generatedAction.getKill();
        String createEntity = generatedAction.getCreate();

        boolean killEntityValid = contextValidator.validateActionEntityContext(killEntity);
        boolean createEntityValid = contextValidator.validateActionEntityContext(createEntity);

        if (!killEntityValid || !createEntityValid) {
            throw new WorldBuilderException("the given kill or create entity under replace action is invalid.");
        }

        ReplaceActionCreationMode creationMode
                = ReplaceActionCreationMode.valueOf(generatedAction.getMode().toUpperCase());

        return new ReplaceAction(ActionType.REPLACE, killEntity, createEntity, creationMode);
    }
}
