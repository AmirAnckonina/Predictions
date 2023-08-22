package simulator.runner.impl;

import simulator.definition.rule.Rule;
import simulator.definition.rule.action.api.abstracts.AbstractAction;
import simulator.execution.instance.entity.manager.api.EntityInstanceManager;
import simulator.execution.instance.entity.manager.impl.EntityInstanceMangerImpl;
import simulator.runner.api.SimulatorRunner;
import simulator.execution.context.api.ExecutionContext;
import simulator.execution.context.impl.ExecutionContextImpl;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.result.api.SimulationResult;

import java.util.Iterator;
import java.util.List;

public class SimulatorRunnerImpl implements SimulatorRunner {

    private final WorldInstance worldInstance;
    private final EntityInstanceManager primaryEntityInstanceManager;
    private final EnvironmentInstance environmentInstance;

    public SimulatorRunnerImpl(WorldInstance worldInstance) {
        this.worldInstance = worldInstance;
        this.primaryEntityInstanceManager =
                new EntityInstanceMangerImpl(worldInstance.getPrimaryEntityInstances());
        this.environmentInstance = worldInstance.getEnvironmentInstance();
    }

    @Override
    public void run(SimulationResult simulationResult) {

        int currTick = 0;
        long currTimeInMilliSec = 0;
        long startTimeInMilliSec;

        startTimeInMilliSec = System.currentTimeMillis();
        simulationResult.setStartingTime(startTimeInMilliSec);

        while (!worldInstance.getTermination().shouldTerminate(currTick, currTimeInMilliSec)) {

            List<EntityInstance> entityInstanceList = primaryEntityInstanceManager.getInstances();
            Iterator<EntityInstance> entityItr = entityInstanceList.iterator();
            while (entityItr.hasNext()) {

                EntityInstance entityInstance = entityItr.next();
                ExecutionContext executionContext = buildExecutionContext(entityInstance);
                for (Rule rule : worldInstance.getRules()) {
                    if (rule.getActivation().isActive(currTick)) {
                        for (AbstractAction action : rule.getActions()) {
                            try {

                                action.invoke(executionContext);
                            } catch (Exception e) {
                                System.out.println("bla");
                            }
                        }
                    }
                }

               if (!entityInstance.isAlive()) {
                   entityItr.remove();
               }
            }

            currTick += 1;
            currTimeInMilliSec = System.currentTimeMillis() - startTimeInMilliSec;
        }

        simulationResult.setTerminationReason(worldInstance.getTermination().reasonForTerminate());
    }

//    private void cleanKilledEntitiesProcedure() {
//
//        int currInstancesListSize = primaryEntityInstanceManager.getInstances().size();
//
//        for (int idx = 0 ; idx < currInstancesListSize ; idx++) {
//            primaryEntityInstanceManager
//                    .getInstances()
//                    .forEach()
//                    .removeIf(entityInstance -> !entityInstance.isAlive());
//        }
//
//    }

    private ExecutionContext buildExecutionContext(EntityInstance entityInstance) {

        return new ExecutionContextImpl(
                entityInstance, primaryEntityInstanceManager, environmentInstance);
    }

 //   @Override
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
//            for (EntityInstance entityInstance : primaryEntityInstanceManager.getInstances()) {
//
//                ExecutionContext executionContext = buildExecutionContext(entityInstance);
//                for (Rule rule : worldInstance.getRules()) {
//                    if (rule.getActivation().isActive(currTick)) {
//                        for (AbstractAction action : rule.getActions()) {
//                            try {
//
//                                action.invoke(executionContext);
//                            } catch (Exception e) {
//                                System.out.println("bla");
//                            }
//                        }
//                    }
//                }
//            }
//            cleanKilledEntitiesProcedure();
//            currTick += 1;
//            currTimeInMilliSec = System.currentTimeMillis() - startTimeInMilliSec;
//        }
//
//        // Extract the terimnation Reason...
//
//    }
}