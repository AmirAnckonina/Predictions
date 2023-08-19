package simulator.execution.runner.impl;

import simulator.definition.rule.Rule;
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

        int currentTicks = 0;
        long currTime = 0;
        long startTime;

        EntityInstanceManager primaryEntityInstanceManager =
                new EntityInstanceMangerImpl(worldInstance.getPrimaryEntityInstances());

        EnvironmentInstance environmentInstance = worldInstance.getEnvironmentInstance();

        startTime = System.currentTimeMillis();
//        while (this.currTime < this.worldInstance.getTermination().getSecondsTermination().orElse((int)this.currTime + 1) &&
//                this.currentTicks < this.worldInstance.getTermination().getTicksTermination().orElse(this.currentTicks + 1)){
        while (!worldInstance.getTermination().shouldTerminate()) {

            for (EntityInstance entityInstance : primaryEntityInstanceManager.getInstances()) {

                ExecutionContext executionContext =
                        new ExecutionContextImpl(
                                entityInstance, primaryEntityInstanceManager, environmentInstance);

                for (Rule rule : worldInstance.getRules()) {

                    Activation activation = rule.getActivation();
                    if (activation != null && activation.isActive(currentTicks)) {

                        rule.getActions().forEach(action -> action.invoke(executionContext));
                    }
                }
            }
            currentTicks += 1;
            currTime = System.currentTimeMillis() - startTime;
        }

    }
}
