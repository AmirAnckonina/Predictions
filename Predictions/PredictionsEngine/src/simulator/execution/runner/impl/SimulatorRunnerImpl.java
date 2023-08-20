package simulator.execution.runner.impl;

import simulator.definition.rule.Rule;
import simulator.definition.rule.action.api.abstracts.AbstractAction;
import simulator.definition.rule.activation.Activation;
import simulator.execution.instance.entity.manager.api.EntityInstanceManager;
import simulator.execution.instance.entity.manager.impl.EntityInstanceMangerImpl;
import simulator.execution.runner.api.SimulatorRunner;
import simulator.execution.context.api.ExecutionContext;
import simulator.execution.context.impl.ExecutionContextImpl;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.world.api.WorldInstance;

import java.util.List;

public class SimulatorRunnerImpl implements SimulatorRunner {

    private WorldInstance worldInstance;

    public SimulatorRunnerImpl(WorldInstance worldInstance) {
        this.worldInstance = worldInstance;
        // Consider to set here the runner:
    }

    @Override
    public void createInstances() {
        //this.worldInstance.setPrimaryEntities();

        //this.worldInstance = new WorldInstanceImpl(environmentInstance);

    }

    @Override
    public void activateEnvironment() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void run() {

        int currTick = 0;
        long currTime = 0;
        long startTime;

        EntityInstanceManager primaryEntityInstanceManager =
                new EntityInstanceMangerImpl(worldInstance.getPrimaryEntityInstances());

        EnvironmentInstance environmentInstance = worldInstance.getEnvironmentInstance();

        startTime = System.currentTimeMillis();

        while (!worldInstance.getTermination().shouldTerminate(currTick, currTime)) {

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

//                        rule.getActions()
//                                .forEach(action ->
//                                        action.invoke(executionContext));
                    }
                }
            }
            currTick += 1;
            currTime = System.currentTimeMillis() - startTime;
        }
    }

}
