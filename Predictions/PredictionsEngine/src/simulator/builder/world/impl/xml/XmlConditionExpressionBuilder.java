package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDCondition;
import simulator.builder.world.api.abstracts.AbstractComponentBuilder;
import simulator.builder.world.api.interfaces.ConditionExpressionBuilder;
import simulator.builder.world.impl.baseImpl.BaseArgumentExpressionBuilder;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.builder.world.utils.file.WorldBuilderUtils;
import simulator.builder.world.validator.api.WorldBuilderContextValidator;
import simulator.definition.rule.action.conditionExpression.api.abstracts.AbstractMultipleConditionExpression;
import simulator.definition.rule.action.conditionExpression.api.abstracts.AbstractSingleConditionExpression;
import simulator.definition.rule.action.conditionExpression.api.interfaces.ConditionExpression;
import simulator.definition.rule.action.conditionExpression.impl.mutiple.AndMultipleConditionExpression;
import simulator.definition.rule.action.conditionExpression.impl.mutiple.OrMultipleConditionExpression;
import simulator.definition.rule.action.conditionExpression.impl.single.BiggerThanConditionExpression;
import simulator.definition.rule.action.conditionExpression.impl.single.EqualityConditionExpression;
import simulator.definition.rule.action.conditionExpression.impl.single.InequalityConditionExpression;
import simulator.definition.rule.action.conditionExpression.impl.single.LowerThanConditionExpression;
import simulator.definition.rule.action.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.utils.enums.eConditionCompartorType;
import simulator.definition.rule.action.utils.enums.eConditionSingularity;
import simulator.definition.rule.action.utils.enums.eMultipleConditionLogicalOperator;

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

        eConditionSingularity conditionSingularity =
                eConditionSingularity.valueOf(generatedCondition.getSingularity().toUpperCase());

        if (conditionSingularity == eConditionSingularity.SINGLE) {

            return buildSingleCondition();

        } else if (conditionSingularity == eConditionSingularity.MULTIPLE) {

            return buildMultipleCondition();

        } else {
            throw new WorldBuilderException("Condition singularity doesn't provided");
        }
    }

    @Override
    public AbstractSingleConditionExpression buildSingleCondition() {

        String entityName = generatedCondition.getEntity();
        String propertyName = generatedCondition.getProperty();

        ArgumentExpression conditionComparedValue =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                generatedCondition.getValue(),
                                contextValidator.getPropertyType(propertyName));

        eConditionCompartorType comparatorType =
                WorldBuilderUtils
                        .convertOperatorSignToComparatorType(generatedCondition.getOperator().toUpperCase());

        switch (comparatorType) {
            case EQAULITY:
                return new EqualityConditionExpression(entityName, propertyName, conditionComparedValue);
            case INEQUALITY:
                return new InequalityConditionExpression(entityName, propertyName, conditionComparedValue);
            case BIGGERTHAN:
                return new BiggerThanConditionExpression(entityName, propertyName, conditionComparedValue);
            case LOWERTHAN:
                return new LowerThanConditionExpression(entityName, propertyName, conditionComparedValue);
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

        eMultipleConditionLogicalOperator logicalOperator =
                eMultipleConditionLogicalOperator.valueOf(generatedCondition.getLogical().toUpperCase());
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
