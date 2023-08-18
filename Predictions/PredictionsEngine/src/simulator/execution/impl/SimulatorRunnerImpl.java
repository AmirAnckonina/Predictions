package simulator.execution.impl;

import simulator.definition.entity.Entity;
import simulator.definition.rule.Rule;
import simulator.definition.rule.action.api.interfaces.Action;
import simulator.definition.rule.activation.Activation;
import simulator.execution.api.SimulatorRunner;
import simulator.execution.context.api.EnvironmentInstance;
import simulator.execution.context.api.ExecutionContext;
import simulator.execution.context.impl.ExecutionContextImpl;
import simulator.execution.instance.entity.EntityInstanceImpl;
import simulator.execution.instance.entity.EntityInstancesImpl;
import simulator.execution.instance.world.WorldInstanceImpl;
import simulator.manager.impl.SimulatorManagerImpl;

import java.time.LocalTime;
import java.util.Random;

public class SimulatorRunnerImpl implements SimulatorRunner {

    private WorldInstanceImpl worldInstance = new WorldInstanceImpl();
    private Integer currentTicks = 0;
    private long currTime = 0;
    private long startTime;

    public SimulatorRunnerImpl(WorldInstanceImpl worldInstance) {
        this.worldInstance = worldInstance;
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
        ExecutionContextImpl executionContext = new ExecutionContextImpl(this.worldInstance);

        this.startTime = System.currentTimeMillis();
        while (this.currTime < this.worldInstance.getTermination().getSecondsTermination().orElse((int)this.currTime + 1) &&
                this.currentTicks < this.worldInstance.getTermination().getTicksTermination().orElse(this.currentTicks + 1)){
            for(Rule rule:this.worldInstance.getRules())
            {
                Activation activation = rule.getActivation().orElse(null);
                if (activation != null && activation.isActive(this.currentTicks));{
                    rule.getActions().forEach(action -> action.invoke(executionContext));
                }
            }
            this.currentTicks += 1;
            this.currTime = System.currentTimeMillis() - this.startTime;
        }

    }
}
