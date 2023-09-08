package simulator.execution.instance.entity.manager.impl;

import simulator.definition.rule.action.expression.conditionExpression.api.interfaces.ConditionExpression;
import simulator.definition.rule.action.secondaryEntity.api.ActionSecondaryEntityDefinition;
import simulator.definition.rule.action.utils.enums.SecondaryEntitySelectionType;
import simulator.execution.context.api.ExecutionContext;
import simulator.execution.context.impl.ExecutionContextImpl;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.manager.api.EntitiesInstancesManager;
import simulator.execution.instance.entity.manager.api.SecondaryEntityInstancesRetrieve;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

import java.util.*;
import java.util.stream.Collectors;

public class SecondaryEntityInstancesRetrieveImpl implements SecondaryEntityInstancesRetrieve {

    private final EntitiesInstancesManager entitiesInstancesManager;
    private final EnvironmentInstance environmentInstance;

    public SecondaryEntityInstancesRetrieveImpl(EntitiesInstancesManager entitiesInstancesManager, EnvironmentInstance environmentInstance) {
        this.entitiesInstancesManager = entitiesInstancesManager;
        this.environmentInstance = environmentInstance;
    }

    @Override
    public List<EntityInstance> getSecondaryEntityInstancesByDefinition(ActionSecondaryEntityDefinition secondaryEntityDef) {

        List<EntityInstance> selectedSecondaryEntityInstances;

        String secondaryEntityName = secondaryEntityDef.getSecondaryEntityName();
        SecondaryEntitySelectionType instancesSelectionType =
                secondaryEntityDef.getSecondaryEntitySelectionType();

        List<EntityInstance> allSecondaryEntitiesInstances
                = this.entitiesInstancesManager.getEntityInstances(secondaryEntityName);

        if (instancesSelectionType == SecondaryEntitySelectionType.ALL) {
            selectedSecondaryEntityInstances = allSecondaryEntitiesInstances;

        } else if (instancesSelectionType == SecondaryEntitySelectionType.SELECTED
                && secondaryEntityDef.getSelectionCount().isPresent()) {

            selectedSecondaryEntityInstances
                    = getEntityInstancesBySelectionCount(allSecondaryEntitiesInstances, secondaryEntityDef);

        } else {
            throw new SimulatorRunnerException("entitiesInstancesManager couldn't fetch secondary entityInstances");
        }

        return selectedSecondaryEntityInstances;

    }

    @Override
    public List<EntityInstance> getEntityInstancesBySelectionCount(
            List<EntityInstance> allSecondaryEntitiesInstances, ActionSecondaryEntityDefinition secondaryEntityDef) {

        List<EntityInstance> selectedEntityInstances = new ArrayList<>();

        // extract selectionCount
        int selectionCount;
        if (secondaryEntityDef.getSelectionCount().isPresent()) {
            selectionCount = secondaryEntityDef.getSelectionCount().get();
        } else { selectionCount = 0; }

        List<Integer> currEntitiesIds = createEntitiesIdsList(allSecondaryEntitiesInstances);
        // as long as we didn't reached instances as the required selection count
        // and - we do have any potential instances to collect ->
        while (selectedEntityInstances.size() < selectionCount && !currEntitiesIds.isEmpty()) {

            // get a random id - generate randomIdx in the list, which contain Id
            int randId = currEntitiesIds.get(new Random().nextInt(currEntitiesIds.size()));

            // extract the entityInstance with this id.
            final boolean[] conditionToBeAddedIsTrue = {false};
            Optional<EntityInstance> potentialEntityInstance =
                    allSecondaryEntitiesInstances
                            .stream()
                            .filter((entIns) -> entIns.getId() == randId)
                            .findFirst();

            // if it exist, so check if it met the condition
            potentialEntityInstance.ifPresent((entIns) ->
                    conditionToBeAddedIsTrue[0]
                            = testConditionForEntityInstanceProcedure(
                                    entIns, secondaryEntityDef.getConditionExpression()
                    ));

            if (potentialEntityInstance.isPresent() && conditionToBeAddedIsTrue[0]) {
                selectedEntityInstances.add(potentialEntityInstance.get());
            } else {
                currEntitiesIds.removeIf((id) -> id == randId);
            }
        }

        return selectedEntityInstances;
    }

    @Override
    public List<Integer> createEntitiesIdsList(List<EntityInstance> allSecondaryEntitiesInstances) {
        // Collect all the current Ids
        return allSecondaryEntitiesInstances
                        .stream()
                        .map(EntityInstance::getId)
                        .collect(Collectors.toList());
    }

    @Override
    public boolean testConditionForEntityInstanceProcedure(EntityInstance entityInstance, ConditionExpression condExpression) {
        ExecutionContext execContext = new ExecutionContextImpl(entityInstance, this.entitiesInstancesManager, this.environmentInstance);
        return condExpression.test(execContext);
    }
}
