package simulator.execution.runner.impl;

import simulator.definition.rule.Rule;
import simulator.definition.rule.action.api.abstracts.AbstractAction;
import simulator.execution.instance.entity.manager.api.EntityInstanceManager;
import simulator.execution.instance.entity.manager.impl.EntityInstanceMangerImpl;
import simulator.execution.runner.api.SimulatorRunner;
import simulator.execution.context.api.ExecutionContext;
import simulator.execution.context.impl.ExecutionContextImpl;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.manager.api.SimulationResult;
import simulator.manager.impl.SimulationResultImpl;
import simulator.manager.utils.SimulatorUtils;

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
    public SimulationResult run() {

        int currTick = 0;
        long currTimeInMilliSec = 0;
        long startTimeInMilliSec;

        String simulationGuid = SimulatorUtils.getGUID();
        SimulationResult simulationResult = new SimulationResultImpl(simulationGuid, worldInstance);

        startTimeInMilliSec = System.currentTimeMillis();

        while (!worldInstance.getTermination().shouldTerminate(currTick, currTimeInMilliSec)) {

            for (EntityInstance entityInstance : primaryEntityInstanceManager.getInstances()) {

                ExecutionContext executionContext =
                        new ExecutionContextImpl(
                                entityInstance, primaryEntityInstanceManager, environmentInstance);

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
            }
            currTick += 1;
            currTimeInMilliSec = System.currentTimeMillis() - startTimeInMilliSec;
        }

        // Extract the terimnation Reason...
        return simulationResult;
    }

    // private EntityInstanceManager

}
