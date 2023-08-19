package simulator.execution.impl;

import simulator.definition.rule.Rule;
import simulator.definition.rule.activation.Activation;
import simulator.execution.api.SimulatorRunner;
import simulator.execution.context.impl.ExecutionContextImpl;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.world.impl.WorldInstanceImpl;

import java.util.ArrayList;
import java.util.List;

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

            for (EntityInstance entityInstance : new ArrayList<EntityInstance>(0)) {

                for(Rule rule:this.worldInstance.getRules())
                {
                    Activation activation = rule.getActivation();
                    if (activation != null && activation.isActive(this.currentTicks));{
                    rule.getActions().forEach(action -> action.invoke(executionContext));
                }
                }
            }

            this.currentTicks += 1;
            this.currTime = System.currentTimeMillis() - this.startTime;
        }

    }
}
