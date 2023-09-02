package simulator.builder.impl.xml;

import resources.jaxb.schema.generated.PRDAction;
import simulator.builder.api.abstracts.AbstractComponentBuilder;
import simulator.builder.api.interfaces.ActionSecondaryEntityBuilder;
import simulator.builder.utils.exception.WorldBuilderException;
import simulator.builder.validator.api.WorldBuilderContextValidator;
import simulator.definition.rule.action.expression.conditionExpression.api.interfaces.ConditionExpression;
import simulator.definition.rule.action.secondaryEntity.api.ActionSecondaryEntityDefinition;
import simulator.definition.rule.action.secondaryEntity.impl.ActionSecondaryEntityDefinitionImpl;
import simulator.definition.rule.action.utils.enums.eSecondaryEntitySelectionType;

public class XmlActionSecondaryEntityBuilder
        extends AbstractComponentBuilder implements ActionSecondaryEntityBuilder {

    private PRDAction.PRDSecondaryEntity generatedSecondaryEntity;

    public XmlActionSecondaryEntityBuilder(
            PRDAction.PRDSecondaryEntity generatedSecondaryEntity,
            WorldBuilderContextValidator contextValidator) {

        super(contextValidator);
        this.generatedSecondaryEntity = generatedSecondaryEntity;
    }

    @Override
    public ActionSecondaryEntityDefinition buildActionSecondaryEntity() {

        ActionSecondaryEntityDefinition actionSecondaryEntityDefinition;

        String secondaryEntityName = generatedSecondaryEntity.getEntity();
        boolean secondaryEntityContextValid = contextValidator.validateActionEntityContext(secondaryEntityName);
        if (!secondaryEntityContextValid) {
            throw new WorldBuilderException("Secondary entity context is invalid.");
        }

        Integer selectionCount = null;
        eSecondaryEntitySelectionType secondaryEntitySelectionType;

        ConditionExpression conditionExpression =
                new XmlConditionExpressionBuilder(
                        generatedSecondaryEntity.getPRDSelection().getPRDCondition(), contextValidator
                ).buildConditionExpression();

        String rawSelectionCount = generatedSecondaryEntity.getPRDSelection().getCount();
        if (rawSelectionCount.equalsIgnoreCase("ALL")) {
            secondaryEntitySelectionType = eSecondaryEntitySelectionType.ALL;
            actionSecondaryEntityDefinition
                    = new ActionSecondaryEntityDefinitionImpl(
                            secondaryEntityName, conditionExpression, secondaryEntitySelectionType
            );
        } else {
            secondaryEntitySelectionType = eSecondaryEntitySelectionType.SELECTED;
            selectionCount =  Integer.parseInt(rawSelectionCount);
            actionSecondaryEntityDefinition =
                    new ActionSecondaryEntityDefinitionImpl(
                            secondaryEntityName, conditionExpression, secondaryEntitySelectionType, selectionCount
                    );
        }


        return actionSecondaryEntityDefinition;
    }
}
