package simulator.execution.context.impl;

import simulator.execution.instance.spaceGrid.api.SpaceGridInstanceWrapper;
import simulator.execution.context.api.ExecutionContext;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.manager.api.EntitiesInstancesManager;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.information.tickDocument.api.TickDocument;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

import java.util.HashMap;
import java.util.Map;

public class ExecutionContextImpl implements ExecutionContext {

    private Map<String, EntityInstance> entityInstanceMap;
    private EntitiesInstancesManager entitiesInstancesManager;
    private EnvironmentInstance environmentInstance;
    private TickDocument currTickDocument;
    private SpaceGridInstanceWrapper spaceGridInstanceWrapper;

    public ExecutionContextImpl(SpaceGridInstanceWrapper spaceGridInstanceWrapper, EntityInstance primaryEntityInstance, EntitiesInstancesManager entitiesInstancesManager, EnvironmentInstance environmentInstance, TickDocument currTickDocument) {
        this.spaceGridInstanceWrapper = spaceGridInstanceWrapper;
        this.entityInstanceMap = new HashMap<>();
        this.entityInstanceMap.put(primaryEntityInstance.getEntityNameFamily(), primaryEntityInstance);
        this.entitiesInstancesManager = entitiesInstancesManager;
        this.environmentInstance = environmentInstance;
        this.currTickDocument = currTickDocument;
    }


    @Override
    public EntityInstance getEntityInstanceByName(String entityName) {
        return this.entityInstanceMap.get(entityName);
    }

    @Override
    public PropertyInstance getEnvironmentVariable(String name) {
        return environmentInstance.getPropertyByName(name);
    }

    @Override
    public void setSecondaryEntityInstance(EntityInstance secondaryEntityInstance) {
        this.entityInstanceMap.put(secondaryEntityInstance.getEntityNameFamily(), secondaryEntityInstance);
    }

    @Override
    public TickDocument getTickDocument() { return this.currTickDocument; }

    @Override
    public SpaceGridInstanceWrapper getSpaceGridInstanceWrapper() {
        return this.spaceGridInstanceWrapper;
    }

    @Override
    public void killEntityInstanceProcedure(String primaryEntityName) {
        EntityInstance entityInstanceToKill = getEntityInstanceByName(primaryEntityName);
        entityInstanceToKill.killMyself();
        this.entitiesInstancesManager.addInstanceToKillWaitingList(entityInstanceToKill);
    }
}
