package simulator.establishment.api;

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
    List<EntityInstance> createPrimaryEntityInstances();
    Map<String, PropertyInstance> createPropertyInstances(Map<String, AbstractPropertyDefinition> propertyDefinitions);
}
