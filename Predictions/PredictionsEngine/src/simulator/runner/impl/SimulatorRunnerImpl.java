package simulator.runner.impl;

import simulator.definition.rule.Rule;
import simulator.definition.rule.action.api.interfaces.Action;
import simulator.definition.rule.action.secondaryEntity.api.ActionSecondaryEntityDefinition;
import simulator.definition.termination.Termination;
import simulator.execution.instance.entity.manager.api.EntitiesInstancesManager;
import simulator.execution.instance.entity.manager.impl.EntitiesInstancesManagerImpl;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.runner.api.SimulatorRunner;
import simulator.execution.context.api.ExecutionContext;
import simulator.execution.context.impl.ExecutionContextImpl;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.result.api.SimulationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        while (!termination.shouldTerminate(currTick, currTimeInMilliSec)) {

            // 1. Entities movement


            // 2. Scan rules,
            // check activation for the current tick,
            // aggregate the activated rules only.
            // aggregate the actions to invoke under the activated rule
            List<Rule> activatedRules = getActiveRulesForCurrTick(currTick, worldInstance.getRules());
            List<Action> actionsToInvoke = getActionToInvokeFromRules(activatedRules);



            // 3. Foreach entity type
            entitiesInstances.forEach( (currEntityName , currEntityInstances) -> {

                        actionsToInvoke.forEach( (currAction) -> {

                                    // check if the action entity context is matching the current entity type (by name)
                                    if (currAction.getPrimaryEntityName().equals(currEntityName)) {

                                         // // // if yes -> retrieve all the current entity instances and
                                        // // // // for each entityInstance (of the current type) do:
                                        currEntityInstances.forEach( (currEntityInstance) -> {
                                                    actionInvocationProcedure(currAction, currEntityInstance);
                                                }
                                        );
                                    }
                                    // // // if not -> continue to the next entity type
                });
            });


            // 4. kill procedure
            // Consider use Stream or Iterator

            // 5. ticks + time procedures
            currTick += 1;
            currTimeInMilliSec = System.currentTimeMillis() - startTimeInMilliSec;
        }

        this.simulationDocument.getSimulationResult().setTerminationReason(worldInstance.getTermination().reasonForTerminate());
    }

    private void actionInvocationProcedure(Action currAction, EntityInstance currPrimaryEntityInstance) {

        //Base execContext building
        ExecutionContext executionContext
                = new ExecutionContextImpl(currPrimaryEntityInstance, this.entitiesInstancesManager, this.environmentInstance);

        // check for secondary entity context existence
        Optional<ActionSecondaryEntityDefinition> maybeSecondaryEntityDefinition = currAction.getActionSecondaryEntityDefinition();

        // if yes -> aggregate secondary entity instances according to the amount requested (selection)
        // if not -> invoke action for the singular entity instance
        if (maybeSecondaryEntityDefinition.isPresent()) {
            // Gather secondaryInstanceProcedure
            // Foreach -> invoke with currPrimaryEntityIns and SecondaryIns together

        } else {

            currAction.invoke(executionContext);
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
