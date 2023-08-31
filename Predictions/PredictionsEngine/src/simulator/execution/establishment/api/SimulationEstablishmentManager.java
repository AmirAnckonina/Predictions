package simulator.execution.establishment.api;

import simulator.definition.entity.EntityDefinition;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.world.WorldDefinition;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.execution.instance.world.api.WorldInstance;

import java.util.List;
import java.util.Map;

public interface SimulationEstablishmentManager {
    WorldInstance establishSimulation(WorldDefinition worldDefinitionDefnition);
    EnvironmentInstance activateEnvironment();
    Map<String, List<EntityInstance>> createEntitiesInstances();
    List<EntityInstance> createSingleEntityInstances(EntityDefinition entityDefinition);
    Map<String, PropertyInstance> createPropertyInstances(Map<String, AbstractPropertyDefinition> propertyDefinitions);
    Map<String, String> getEstablishedEnvironmentInfo(WorldInstance establishedWorldInstance);
}
