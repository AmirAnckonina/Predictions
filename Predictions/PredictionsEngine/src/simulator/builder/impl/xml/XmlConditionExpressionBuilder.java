package simulator.builder.impl.xml;

import resources.jaxb.schema.generated.PRDCondition;
import simulator.builder.api.abstracts.AbstractComponentBuilder;
import simulator.builder.api.interfaces.ConditionExpressionBuilder;
import simulator.builder.impl.baseImpl.BaseArgumentExpressionBuilder;
import simulator.builder.utils.ArgExpressionTypeDemands;
import simulator.builder.utils.exception.WorldBuilderException;
import simulator.builder.validator.api.WorldBuilderContextValidator;
import simulator.builder.utils.file.WorldBuilderUtils;
import simulator.definition.rule.action.expression.conditionExpression.api.abstracts.AbstractMultipleConditionExpression;
import simulator.definition.rule.action.expression.conditionExpression.api.abstracts.AbstractSingleConditionExpression;
import simulator.definition.rule.action.expression.conditionExpression.api.interfaces.ConditionExpression;
import simulator.definition.rule.action.expression.conditionExpression.impl.mutiple.AndMultipleConditionExpression;
import simulator.definition.rule.action.expression.conditionExpression.impl.mutiple.OrMultipleConditionExpression;
import simulator.definition.rule.action.expression.conditionExpression.impl.single.BiggerThanConditionExpression;
import simulator.definition.rule.action.expression.conditionExpression.impl.single.EqualityConditionExpression;
import simulator.definition.rule.action.expression.conditionExpression.impl.single.InequalityConditionExpression;
import simulator.definition.rule.action.expression.conditionExpression.impl.single.LowerThanConditionExpression;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.utils.enums.ConditionCompartorType;
import simulator.definition.rule.action.utils.enums.ConditionSingularity;
import simulator.definition.rule.action.utils.enums.MultipleConditionLogicalOperator;

import java.util.ArrayList;
import java.util.List;

public class XmlConditionExpressionBuilder extends AbstractComponentBuilder implements ConditionExpressionBuilder {

    private PRDCondition generatedCondition;

    public XmlConditionExpressionBuilder(PRDCondition generatedCondition, WorldBuilderContextValidator contextValidator) {
        super(contextValidator);
        this.generatedCondition = generatedCondition;
    }

    @Override
    public ConditionExpression buildConditionExpression() {

        ConditionExpression condExpression;

        ConditionSingularity conditionSingularity =
                ConditionSingularity.valueOf(generatedCondition.getSingularity().toUpperCase());

        if (conditionSingularity == ConditionSingularity.SINGLE) {

            condExpression = buildSingleCondition();

        } else if (conditionSingularity == ConditionSingularity.MULTIPLE) {

            condExpression = buildMultipleCondition();

        } else {
            throw new WorldBuilderException("Condition singularity doesn't provided");
        }

        return condExpression;
    }

    private boolean conditionEntityContextValidationProcedure() {
        boolean conditionContextValid =
                contextValidator.validateActionEntityContext(
                        generatedCondition.getEntity()
                );

        return conditionContextValid;
    }

    @Override
    public AbstractSingleConditionExpression buildSingleCondition() {

        boolean conditionContextValid = conditionEntityContextValidationProcedure();

        if (!conditionContextValid) {
            throw new WorldBuilderException("Condition context is invalid.");
        }

        String entityName = generatedCondition.getEntity();
        String rawConditionProperty = generatedCondition.getProperty();
        String rawValue = generatedCondition.getValue();

        ArgumentExpression conditionPropertyArgExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                rawConditionProperty,
                                new ArgExpressionTypeDemands()

                        );

        ArgumentExpression conditionComparedValue =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                rawValue,
                                new ArgExpressionTypeDemands(
                                        conditionPropertyArgExpression.getExpressionReturnedValueType()
                                )

                        );

        ConditionCompartorType comparatorType =
                WorldBuilderUtils
                        .convertOperatorSignToComparatorType(generatedCondition.getOperator().toUpperCase());

        switch (comparatorType) {
            case EQAULITY:
                return new EqualityConditionExpression(entityName, conditionPropertyArgExpression, conditionComparedValue);
            case INEQUALITY:
                return new InequalityConditionExpression(entityName, conditionPropertyArgExpression, conditionComparedValue);
            case BIGGERTHAN:
                return new BiggerThanConditionExpression(entityName,conditionPropertyArgExpression, conditionComparedValue);
            case LOWERTHAN:
                return new LowerThanConditionExpression(entityName, conditionPropertyArgExpression, conditionComparedValue);
            default:
                throw new WorldBuilderException("Couldn't determine which SingleConditionExpression to build");
        }
    }

    @Override
    public AbstractMultipleConditionExpression buildMultipleCondition() {

        List<ConditionExpression> conditionList = new ArrayList<>();
        for (PRDCondition genCond : generatedCondition.getPRDCondition()) {
             conditionList.add(
                     new XmlConditionExpressionBuilder(genCond, contextValidator)
                    .buildConditionExpression()
             );
        }

        MultipleConditionLogicalOperator logicalOperator =
                MultipleConditionLogicalOperator.valueOf(generatedCondition.getLogical().toUpperCase());
        switch (logicalOperator) {
            case AND:
                return new AndMultipleConditionExpression(conditionList);
            case OR:
                return new OrMultipleConditionExpression(conditionList);
            default:
                throw new WorldBuilderException("Unsupported logical condition operator");
        }
    }
}
