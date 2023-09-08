package simulator.execution.context.impl;

import simulator.execution.context.api.ExecutionContext;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.manager.api.EntitiesInstancesManager;
import simulator.execution.instance.entity.manager.api.EntityInstanceManager;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.information.tickDocument.api.TickDocument;

import java.util.HashMap;
import java.util.Map;

public class ExecutionContextImpl implements ExecutionContext {
    private Map<String, EntityInstance> entityInstanceMap;
    private EntitiesInstancesManager entitiesInstancesManager;
    private EnvironmentInstance environmentInstance;
    private TickDocument currTickDocument;

    public ExecutionContextImpl(EntityInstance entityInstance, EntitiesInstancesManager entitiesInstancesManager, EnvironmentInstance environmentInstance) {
        entityInstanceMap = new HashMap<>();
        entityInstanceMap.put(entityInstance.getEntityNameFamily(), entityInstance);
        this.entitiesInstancesManager = entitiesInstancesManager;
        this.environmentInstance = environmentInstance;
    }


    @Override
    public EntityInstance getEntityInstanceByName(String entityName) {
        return this.entityInstanceMap.get(entityName);
    }

    @Override
    public void removeEntity(String entityName, EntityInstance entityInstance) {
        entitiesInstancesManager.killEntity(entityName,entityInstance.getId());
    }

    @Override
    public PropertyInstance getEnvironmentVariable(String name) {
        return environmentInstance.getPropertyByName(name);
    }

    @Override
    public void addEntityInstance(EntityInstance additionalEntityInstance) {
        this.entityInstanceMap.put(additionalEntityInstance.getEntityNameFamily(), additionalEntityInstance);
    }

    @Override
    public TickDocument getTickDocument() {
        return this.currTickDocument;
    }
}
