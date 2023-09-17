package simulator.runner.impl;

import simulator.execution.instance.spaceGrid.api.SpaceGridInstanceWrapper;
import simulator.definition.rule.Rule;
import simulator.definition.rule.action.api.interfaces.Action;
import simulator.definition.rule.action.secondaryEntity.api.ActionSecondaryEntityDefinition;
import simulator.definition.termination.Termination;
import simulator.execution.instance.entity.manager.api.EntitiesInstancesManager;
import simulator.execution.instance.entity.manager.api.SecondaryEntityInstancesRetrieve;
import simulator.execution.instance.entity.manager.impl.EntitiesInstancesManagerImpl;
import simulator.execution.instance.entity.manager.impl.SecondaryEntityInstancesRetrieveImpl;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.information.tickDocument.api.TickDocument;
import simulator.information.tickDocument.impl.TickDocumentImpl;
import simulator.execution.instance.movement.manager.api.MovementManager;
import simulator.execution.instance.movement.manager.impl.MovementManagerImpl;
import simulator.runner.api.SimulatorRunner;
import simulator.execution.context.api.ExecutionContext;
import simulator.execution.context.impl.ExecutionContextImpl;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.world.api.WorldInstance;

import java.util.*;

public class SimulatorRunnerImpl implements SimulatorRunner {

    private final WorldInstance worldInstance;
    private final EntitiesInstancesManager entitiesInstancesManager;
    private final EnvironmentInstance environmentInstance;
    private SimulationDocument simulationDocument;

    public SimulatorRunnerImpl(SimulationDocument simulationDocument) {
        this.simulationDocument = simulationDocument;
        this.worldInstance = this.simulationDocument.getWorldInstance();
        this.entitiesInstancesManager = new EntitiesInstancesManagerImpl(
                worldInstance.getEntitiesInstances()
        );
        this.environmentInstance = this.simulationDocument.getWorldInstance().getEnvironmentInstance();
    }

//    @Override
//    public void run(SimulationResult simulationResult) {
//
//        int currTick = 0;
//        long currTimeInMilliSec = 0;
//        long startTimeInMilliSec;
//
//        startTimeInMilliSec = System.currentTimeMillis();
//        simulationResult.setStartingTime(startTimeInMilliSec);
//
//        while (!worldInstance.getTermination().shouldTerminate(currTick, currTimeInMilliSec)) {
//
//            List<EntityInstance> entityInstanceList = primaryEntityInstanceManager.getInstances();
//            Iterator<EntityInstance> entityItr = entityInstanceList.iterator();
//            while (entityItr.hasNext()) {
//                EntityInstance entityInstance = entityItr.next();
//                ExecutionContext executionContext = buildExecutionContext(entityInstance);
//                for (Rule rule : worldInstance.getRules()) {
//                    if (rule.getActivation().isActive(currTick)) {
//                        for (AbstractAction action : rule.getActions()) {
//                            try {
//                                action.invoke(executionContext);
//                            } catch (Exception e) {
//                            }
//                        }
//                    }
//                }
//
//               if (!entityInstance.isAlive()) {
//                   entityItr.remove();
//               }
//            }
//
//            currTick += 1;
//            currTimeInMilliSec = System.currentTimeMillis() - startTimeInMilliSec;
//        }
//
//        simulationResult.setTerminationReason(worldInstance.getTermination().reasonForTerminate());
//    }

    @Override
    public void run() {

        int currTick = 0;
        long currTimeInMilliSec = 0;
        long startTimeInMilliSec;

        startTimeInMilliSec = System.currentTimeMillis();
        //this.simulationDocument.setStartingTime(startTimeInMilliSec);

        Termination termination = worldInstance.getTermination();
        List<Rule> rules = worldInstance.getRules();
        Map<String, List<EntityInstance>> entitiesInstances = worldInstance.getEntitiesInstances();
        SpaceGridInstanceWrapper spaceGridInstanceWrapper = worldInstance.getSpaceGridWrapper();

        while (!termination.shouldTerminate(currTick, currTimeInMilliSec)) {

            TickDocument currTickDocument =  new TickDocumentImpl(currTick, currTimeInMilliSec, entitiesInstances);

            // 1. Entities movement
            entitiesMovementProcedure();

            // 2. Scan rules,check activation for the current tick.
            // aggregate the activated rules only. aggregate the actions to invoke under the activated rule
            List<Rule> activatedRules = getActiveRulesForCurrTick(currTick, worldInstance.getRules());
            List<Action> actionsToInvoke = getActionToInvokeFromRules(activatedRules);

            // 3. Foreach entity type
            tickActionsProcedure(entitiesInstances, actionsToInvoke, spaceGridInstanceWrapper, currTickDocument);

            // 4. kill & create procedure
            createNewInstancesProcedure(entitiesInstances);
            cleanKilledInstancesProcedure(entitiesInstances);

            // 5. ticks + time procedures
            currTick += 1;
            currTimeInMilliSec = System.currentTimeMillis() - startTimeInMilliSec;
            // SaveTickDocument

        }

        this.simulationDocument.getSimulationResult().setTerminationReason(worldInstance.getTermination().reasonForTerminate());
    }

    private void tickActionsProcedure(Map<String, List<EntityInstance>> entitiesInstances, List<Action> actionsToInvoke, SpaceGridInstanceWrapper spaceGridInstanceWrapper, TickDocument currTickDocument) {

        try {

            entitiesInstances.forEach((currEntityName , currEntityInstances) -> {
                actionsToInvoke.forEach((currAction) -> {
                    // check if the action entity context is matching the current entity type (by name)
                    if (currAction.getPrimaryEntityName().equals(currEntityName)) {
                        // Retrieve all the current entity instances
                        currEntityInstances.forEach((currEntityInstance) -> {
                            singleActionInvocationProcedure(
                                    currAction, spaceGridInstanceWrapper, currEntityInstance, currTickDocument);
                        });
                    }
                });
            });

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void entitiesMovementProcedure() {
        try {
            MovementManager movementManager = new MovementManagerImpl();
            movementManager.moveAllEntitiesOneStep(this.worldInstance.getSpaceGridWrapper());
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void createNewInstancesProcedure(Map<String, List<EntityInstance>> entitiesInstances) {

       List<EntityInstance> instancesToCreate = this.entitiesInstancesManager.getCreateWaitingList();

    }

    private void cleanKilledInstancesProcedure(Map<String, List<EntityInstance>> entitiesInstances) {

        List<EntityInstance> instanceToKill = this.entitiesInstancesManager.getKillWaitingList();
        entitiesInstances.forEach((entityFamily, entityInstances) -> {

            // Run on all current entity instances with itr
            Iterator<EntityInstance> entityItr = entityInstances.iterator();
            while (entityItr.hasNext()) {
                EntityInstance entityInstance = entityItr.next();
                if (!entityInstance.isAlive()) {
                   entityItr.remove();
                }
            }
        });
    }

    private void singleActionInvocationProcedure(
            Action currAction, SpaceGridInstanceWrapper spaceGridInstanceWrapper, EntityInstance currPrimaryEntityInstance, TickDocument currTickDocument) {

        //Base execContext building
        ExecutionContext executionContext
                = new ExecutionContextImpl(
                spaceGridInstanceWrapper, currPrimaryEntityInstance, this.entitiesInstancesManager, this.environmentInstance, currTickDocument
                );

        // check for secondary entity context existence
        Optional<ActionSecondaryEntityDefinition> maybeSecondaryEntityDefinition =
                currAction.getActionSecondaryEntityDefinition();

        // if yes -> aggregate secondary entity instances according to the amount requested (selection)
        // else -> invoke action for the singular entity instance
        if (maybeSecondaryEntityDefinition.isPresent()) {

            actionWithSecondaryEntityInvocationProcedure(
                    currAction, executionContext, maybeSecondaryEntityDefinition.get());

        } else {
            actionInvocation(currAction, executionContext);
        }
    }

    private void actionWithSecondaryEntityInvocationProcedure(
            Action currAction, ExecutionContext executionContext, ActionSecondaryEntityDefinition secondaryEntityDef) {

        // Gather secondaryInstanceProcedure
        SecondaryEntityInstancesRetrieve instancesRetriever
                = new SecondaryEntityInstancesRetrieveImpl(this.entitiesInstancesManager, this.environmentInstance, executionContext);
        List<EntityInstance> secondaryEntityInstances =
                instancesRetriever.getSecondaryEntityInstancesByDefinition(secondaryEntityDef);

        // Foreach -> invoke with currPrimaryEntityIns and SecondaryIns together
        secondaryEntityInstances
                .forEach((secondaryEntityInstance) -> {
                    executionContext.setSecondaryEntityInstance(secondaryEntityInstance);
                    actionInvocation(currAction, executionContext);
                });
    }

    private void actionInvocation(Action currAction, ExecutionContext executionContext) {
        currAction.invoke(executionContext);
    }

    private List<Rule> getActiveRulesForCurrTick(int currTick, List<Rule> rules) {

        List<Rule> activeRules = new ArrayList<>();
        rules.forEach( (rule) -> {
            if (rule.getActivation().isActive(currTick)) {
                activeRules.add(rule);
            }
        } );

        return activeRules;
    }

    private List<Action> getActionToInvokeFromRules(List<Rule> activatedRules) {

        List<Action> actionsToInvoke = new ArrayList<>();
        activatedRules.forEach( (rule) -> {
            actionsToInvoke.addAll(rule.getActions());
        });

        return actionsToInvoke;
    }


}
