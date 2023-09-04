package simulator.definition.rule.action.secondaryEntity.api;

import simulator.definition.rule.action.expression.conditionExpression.api.interfaces.ConditionExpression;
import simulator.definition.rule.action.impl.ConditionAction;
import simulator.definition.rule.action.utils.enums.eSecondaryEntitySelectionType;

import java.util.Optional;

public interface ActionSecondaryEntityDefinition {

    String getSecondaryEntityName();
    Optional<Integer> getSelectionCount();
    eSecondaryEntitySelectionType getSecondaryEntitySelectionType();
    ConditionExpression getConditionExpression();

}
