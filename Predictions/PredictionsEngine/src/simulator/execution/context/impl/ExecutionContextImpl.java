package simulator.execution.context.impl;

import simulator.execution.instance.spaceGrid.api.SpaceGridInstanceWrapper;
import simulator.execution.context.api.ExecutionContext;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.manager.api.EntitiesInstancesManager;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.information.tickDocument.api.TickDocument;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

public class ExecutionContextImpl implements ExecutionContext {

    //private Map<String, EntityInstance> entityInstanceMap;
    private EntityInstance primaryEntityInstance;
    private EntityInstance secondaryEntityInstance;
    private EntitiesInstancesManager entitiesInstancesManager;
    private EnvironmentInstance environmentInstance;
    private TickDocument currTickDocument;
    private SpaceGridInstanceWrapper spaceGridInstanceWrapper;

    public ExecutionContextImpl(SpaceGridInstanceWrapper spaceGridInstanceWrapper, EntityInstance entityInstance, EntitiesInstancesManager entitiesInstancesManager, EnvironmentInstance environmentInstance, TickDocument currTickDocument) {
        this.spaceGridInstanceWrapper = spaceGridInstanceWrapper;
        //entityInstanceMap = new HashMap<>();
        //entityInstanceMap.put(entityInstance.getEntityNameFamily(), entityInstance);
        this.primaryEntityInstance = entityInstance;
        this.entitiesInstancesManager = entitiesInstancesManager;
        this.environmentInstance = environmentInstance;
        this.currTickDocument = currTickDocument;
    }


    @Override
    public EntityInstance getEntityInstanceByName(String entityName) {
        //return this.entityInstanceMap.get(entityName);
        EntityInstance entityInstance;
        if (this.primaryEntityInstance.getEntityNameFamily().equals(entityName)) {
            entityInstance = this.primaryEntityInstance;
        } else if (this.secondaryEntityInstance.getEntityNameFamily().equals(entityName)) {
            entityInstance = this.secondaryEntityInstance;
        } else {
            throw new SimulatorRunnerException("Couldn't reach the requested entityInstance by entity name, under exec context impl");
        }

        return entityInstance;
    }

    @Override
    public EntityInstance getPrimaryEntityInstance() { return this.primaryEntityInstance; }

    @Override
    public EntityInstance getSecondaryEntityInstance() {
        return secondaryEntityInstance;
    }

    @Override
    public PropertyInstance getEnvironmentVariable(String name) {
        return environmentInstance.getPropertyByName(name);
    }

    @Override
    public void setSecondaryEntityInstance(EntityInstance secondaryEntityInstance) {
        //this.entityInstanceMap.put(additionalEntityInstance.getEntityNameFamily(), additionalEntityInstance);
        this.secondaryEntityInstance = secondaryEntityInstance;
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
