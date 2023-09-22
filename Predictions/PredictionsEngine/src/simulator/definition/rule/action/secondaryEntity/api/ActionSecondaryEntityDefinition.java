package simulator.definition.rule.action.secondaryEntity.api;

import simulator.definition.rule.action.expression.conditionExpression.api.interfaces.ConditionExpression;
import simulator.definition.rule.action.utils.enums.SecondaryEntitySelectionType;

import java.util.Optional;

public interface ActionSecondaryEntityDefinition {

    String getSecondaryEntityName();
    Optional<Integer> getSelectionCount();
    SecondaryEntitySelectionType getSecondaryEntitySelectionType();
    Optional<ConditionExpression> getConditionExpression();

}
