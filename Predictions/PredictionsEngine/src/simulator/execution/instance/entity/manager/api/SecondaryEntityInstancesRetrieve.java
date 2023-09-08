package simulator.execution.instance.entity.manager.api;

import simulator.definition.rule.action.expression.conditionExpression.api.interfaces.ConditionExpression;
import simulator.definition.rule.action.secondaryEntity.api.ActionSecondaryEntityDefinition;
import simulator.execution.instance.entity.api.EntityInstance;

import java.util.List;

public interface SecondaryEntityInstancesRetrieve {
    List<EntityInstance> getSecondaryEntityInstancesByDefinition(ActionSecondaryEntityDefinition secondaryEntityDef);
    List<EntityInstance> getEntityInstancesBySelectionCount(
            List<EntityInstance> allSecondaryEntitiesInstances,
            ActionSecondaryEntityDefinition secondaryEntityDef);
    List<Integer> createEntitiesIdsList(List<EntityInstance> allSecondaryEntitiesInstances);
    boolean testConditionForEntityInstanceProcedure(EntityInstance entityInstance, ConditionExpression condExpression);
}
