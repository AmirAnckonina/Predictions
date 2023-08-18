package simulator.execution.impl;

import simulator.execution.api.SimulatorRunner;
import simulator.execution.context.api.EnvironmentInstance;
import simulator.execution.instance.entity.EntityInstancesImpl;
import simulator.execution.instance.world.WorldInstanceImpl;
import simulator.manager.impl.SimulatorManagerImpl;

public class SimulatorRunnerImpl implements SimulatorRunner {

    WorldInstanceImpl worldInstance = new WorldInstanceImpl();

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

    }
}
