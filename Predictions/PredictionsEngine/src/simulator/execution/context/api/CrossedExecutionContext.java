package simulator.execution.context.api;

import simulator.definition.entity.EntityDefinition;
import simulator.execution.instance.entity.manager.api.EntitiesInstancesManager;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.spaceGrid.api.SpaceGridInstanceWrapper;

public interface CrossedExecutionContext {
    EntitiesInstancesManager getEntitiesInstancesManager();
    EnvironmentInstance getEnvironmentInstance();
    SpaceGridInstanceWrapper getSpaceGridInstanceWrapper();
    EntityDefinition getEntityDefinitionByName(String entityFamilyName);
}
