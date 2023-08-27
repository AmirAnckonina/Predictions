package simulator.establishment.api;

import simulator.definition.entity.Entity;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.world.World;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.execution.instance.world.api.WorldInstance;

import java.util.List;
import java.util.Map;

public interface SimulationEstablishmentManager {
    WorldInstance establishSimulation(World worldDefnition);
    EnvironmentInstance activateEnvironment();
    Map<String, List<EntityInstance>> createEntitiesInstances();
    List<EntityInstance> createSingleEntityInstances(Entity entityDefinition);
    Map<String, PropertyInstance> createPropertyInstances(Map<String, AbstractPropertyDefinition> propertyDefinitions);
    Map<String, String> getEstablishedEnvironmentInfo(WorldInstance establishedWorldInstance);
}
