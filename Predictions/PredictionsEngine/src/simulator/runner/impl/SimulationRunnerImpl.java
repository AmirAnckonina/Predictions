package simulator.runner.impl;

import enums.SimulationStatus;
import simulator.definition.entity.EntityDefinition;
import simulator.execution.context.api.CrossedExecutionContext;
import simulator.execution.context.impl.CrossedExecutionContextImpl;
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
import simulator.execution.context.api.ExecutionContext;
import simulator.execution.context.impl.ExecutionContextImpl;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.runner.utils.exceptions.TerminationReason;

import java.util.*;

public class SimulationRunnerImpl implements Runnable {

    private final WorldInstance worldInstance;
    private SimulationDocument simulationDocument;
    private CrossedExecutionContext crossedExecutionContext;

    public SimulationRunnerImpl(SimulationDocument simulationDocument) {
        this.simulationDocument = simulationDocument;
        this.worldInstance = this.simulationDocument.getWorldInstance();
        EntitiesInstancesManager entitiesInstancesManager = new EntitiesInstancesManagerImpl(worldInstance.getEntitiesInstances());
        EnvironmentInstance environmentInstance = this.simulationDocument.getWorldInstance().getEnvironmentInstance();
        SpaceGridInstanceWrapper spaceGridInstanceWrapper = this.simulationDocument.getWorldInstance().getSpaceGridWrapper();
        Map<String, EntityDefinition> entityDefinitionMap = this.simulationDocument.getWorldInstance().getEntityDefinitionMap();
        this.crossedExecutionContext = new CrossedExecutionContextImpl(entitiesInstancesManager, environmentInstance, spaceGridInstanceWrapper, entityDefinitionMap);
    }

    @Override
    public void run() {

        int currTick = 0;
        long currTimeInMilliSec = 0;
        long startTimeInMilliSec;


        Termination termination = this.worldInstance.getTermination();
        List<Rule> rules = this.worldInstance.getRules();
        Map<String, List<EntityInstance>> entitiesInstances = this.worldInstance.getEntitiesInstances();
        SpaceGridInstanceWrapper spaceGridInstanceWrapper = this.crossedExecutionContext.getSpaceGridInstanceWrapper();
        this.simulationDocument.setSimulationStatus(SimulationStatus.RUNNING);

        startTimeInMilliSec = System.currentTimeMillis();

        while (!termination.shouldTerminate(currTick, currTimeInMilliSec)) {

            TickDocument currTickDocument =  new TickDocumentImpl(currTick, currTimeInMilliSec, entitiesInstances);

            // 1. Entities movement
            entitiesMovementProcedure();

            // 2. Scan rules,check activation for the current tick.
            // aggregate the activated rules only. aggregate the actions to invoke under the activated rule
            List<Rule> activatedRules = getActiveRulesForCurrTick(currTick, this.worldInstance.getRules());
            List<Action> actionsToInvoke = getActionToInvokeFromRules(activatedRules);

            // 3. Foreach entity type
            tickActionsProcedure(entitiesInstances, actionsToInvoke, spaceGridInstanceWrapper, currTickDocument);

            // 4. kill & create procedure
            createNewInstancesProcedure();
            killInstancesProcedure();

            // 5. ticks + time procedures
            simulationDocument.addTickDocument(currTickDocument);
            currTick += 1;
            currTimeInMilliSec = System.currentTimeMillis() - startTimeInMilliSec;
            handleSimulationRunInterruptions(termination);
        }

        if(termination.reasonForTerminate() == TerminationReason.USER){
            this.simulationDocument.setSimulationStatus(SimulationStatus.PAUSED);
        }else {
            this.simulationDocument.setSimulationStatus(SimulationStatus.COMPLETED);
        }

        this.simulationDocument.finishSimulationSession(startTimeInMilliSec);
        this.simulationDocument.getSimulationResult().setTerminationReason(worldInstance.getTermination().reasonForTerminate());
    }

    private void handleSimulationRunInterruptions(Termination termination) {

        SimulationStatus status = this.simulationDocument.getSimulationStatus();
        while (status == SimulationStatus.PAUSED) {
            try {
                this.wait();
            } catch (InterruptedException e) { }
        }

        if (status == SimulationStatus.STOPPED) {
            termination.terminateInTheNextTick();
        }

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

    private void createNewInstancesProcedure() {

        try {

            List<EntityInstance> createdInstances =
                    this.crossedExecutionContext.getEntitiesInstancesManager().getCreateWaitingList();

            this.crossedExecutionContext
                    .getSpaceGridInstanceWrapper()
                    .applyReservedCellsForCreatedInstances(createdInstances);
            
            this.crossedExecutionContext.getEntitiesInstancesManager().completeCreationWaitingList();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }


    }

    private void killInstancesProcedure() {
        try {
            List<EntityInstance> killInstances
                    = this.crossedExecutionContext.getEntitiesInstancesManager().getKillWaitingList();

            this.crossedExecutionContext
                    .getSpaceGridInstanceWrapper()
                    .applyReservedCellsForKilledInstances(killInstances);

            this.crossedExecutionContext.getEntitiesInstancesManager().completeKillEntitiesInstancesInWaitingList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void singleActionInvocationProcedure(
            Action currAction, SpaceGridInstanceWrapper spaceGridInstanceWrapper, EntityInstance currPrimaryEntityInstance, TickDocument currTickDocument) {

        //Base execContext building
        ExecutionContext executionContext
                = new ExecutionContextImpl(this.crossedExecutionContext, currPrimaryEntityInstance, currTickDocument);

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
        SecondaryEntityInstancesRetrieve instancesRetriever = new SecondaryEntityInstancesRetrieveImpl(executionContext);

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
        try {
            currAction.invoke(executionContext);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
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
