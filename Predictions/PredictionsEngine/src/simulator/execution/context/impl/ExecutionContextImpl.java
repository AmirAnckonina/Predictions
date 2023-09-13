package simulator.execution.context.impl;

import simulator.definition.board.api.SpaceGridInstance;
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

    //private Map<String, EntityInstance> entityInstanceMap;
    private EntityInstance primaryEntityInstance;
    private EntityInstance secondaryEntityInstance;
    private EntitiesInstancesManager entitiesInstancesManager;
    private EnvironmentInstance environmentInstance;
    private TickDocument currTickDocument;
    private SpaceGridInstance spaceGridInstance;

    public ExecutionContextImpl(SpaceGridInstance spaceGridInstance, EntityInstance entityInstance, EntitiesInstancesManager entitiesInstancesManager, EnvironmentInstance environmentInstance, TickDocument currTickDocument) {
        this.spaceGridInstance = spaceGridInstance;
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
    public EntityInstance getPrimaryEntityInstance() {
        return this.primaryEntityInstance;
    }

    @Override
    public EntityInstance getSecondaryEntityInstance() {
        return secondaryEntityInstance;
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
    public void setSecondaryEntityInstance(EntityInstance secondaryEntityInstance) {
        //this.entityInstanceMap.put(additionalEntityInstance.getEntityNameFamily(), additionalEntityInstance);
        this.secondaryEntityInstance = secondaryEntityInstance;
    }

    @Override
    public TickDocument getTickDocument() {

        return this.currTickDocument;
    }

    @Override
    public SpaceGridInstance getSpaceGridInstance() {
        return this.spaceGridInstance;
    }
}
