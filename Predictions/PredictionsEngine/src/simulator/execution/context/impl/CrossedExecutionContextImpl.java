package simulator.execution.context.impl;

import simulator.definition.entity.EntityDefinition;
import simulator.execution.context.api.CrossedExecutionContext;
import simulator.execution.instance.entity.manager.api.EntitiesInstancesManager;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.spaceGrid.api.SpaceGridInstanceWrapper;

import java.util.Map;

public class CrossedExecutionContextImpl implements CrossedExecutionContext {
    private EntitiesInstancesManager entitiesInstancesManager;
    private EnvironmentInstance environmentInstance;
    private SpaceGridInstanceWrapper spaceGridInstanceWrapper;
    private Map<String, EntityDefinition> entityDefinitionMap;

    public CrossedExecutionContextImpl(EntitiesInstancesManager entitiesInstancesManager, EnvironmentInstance environmentInstance, SpaceGridInstanceWrapper spaceGridInstanceWrapper, Map<String, EntityDefinition> entityDefinitionMap) {
        this.entitiesInstancesManager = entitiesInstancesManager;
        this.environmentInstance = environmentInstance;
        this.spaceGridInstanceWrapper = spaceGridInstanceWrapper;
        this.entityDefinitionMap = entityDefinitionMap;
    }

    @Override
    public EntitiesInstancesManager getEntitiesInstancesManager() { return this.entitiesInstancesManager; }

    @Override
    public EnvironmentInstance getEnvironmentInstance() {
        return this.environmentInstance;
    }

    @Override
    public SpaceGridInstanceWrapper getSpaceGridInstanceWrapper() {
        return this.spaceGridInstanceWrapper;
    }

    @Override
    public EntityDefinition getEntityDefinitionByName(String entityFamilyName) {
        return this.entityDefinitionMap.get(entityFamilyName);
    }
}
