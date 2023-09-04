package simulator.runner.impl;

import simulator.definition.rule.Rule;
import simulator.definition.rule.action.api.interfaces.Action;
import simulator.definition.termination.Termination;
import simulator.execution.instance.entity.manager.api.EntitiesInstancesManager;
import simulator.execution.instance.entity.manager.impl.EntitiesInstancesManagerImpl;
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

public class SimulatorRunnerImpl implements SimulatorRunner {

    private final WorldInstance worldInstance;
    private final EntitiesInstancesManager entitiesInstancesManager;
    private final EnvironmentInstance environmentInstance;

    public SimulatorRunnerImpl(WorldInstance worldInstance) {
        this.worldInstance = worldInstance;
        this.entitiesInstancesManager =
                new EntitiesInstancesManagerImpl(
                        worldInstance.getEntitiesInstances()
                );
        this.environmentInstance = worldInstance.getEnvironmentInstance();
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
    public void run(SimulationResult simulationResult) {

        int currTick = 0;
        long currTimeInMilliSec = 0;
        long startTimeInMilliSec;

        startTimeInMilliSec = System.currentTimeMillis();
        simulationResult.setStartingTime(startTimeInMilliSec);

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
            entitiesInstances.forEach( (entityName , entityInstances) -> {

                        actionsToInvoke.forEach( (action) -> {

                                    // check if the action entity context is matching the current entity type (by name)
                                    if (action.getPrimaryEntityName().equals(entityName)) {

                                         // // // if yes -> retrieve all the current entity instances
                                        // // // // for each entityInstance (of the current type)
                                        entityInstances.forEach( (entityInstance) -> {
                                                    actionInvocationProcedure();
                                                }
                                        );
                                    }
                                    // // // if not -> continue to the next entity type
                });
            });


            // 4. kill procedure

            // 5. ticks + time procedures

            currTick += 1;
            currTimeInMilliSec = System.currentTimeMillis() - startTimeInMilliSec;
        }

        simulationResult.setTerminationReason(worldInstance.getTermination().reasonForTerminate());
    }

    private void actionInvocationProcedure() {
        // // // // check for secondary entity context existence
        // // // // // if not -> invoke action for the singular entity instance
        // // // // // if yes -> aggregate secondary entity instances according to the amount requested (selection)
        // // // // // // inject the ExecutionContext both the primaryEntityInstance and the secondary instance
        // // // // // // invoke
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

    private ExecutionContext buildExecutionContext(EntityInstance entityInstance) {

        return new ExecutionContextImpl(
                entityInstance, entitiesInstancesManager, environmentInstance);
    }

}
