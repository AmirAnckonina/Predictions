package simulator.establishment.manager.api;

import dto.EstablishedEnvironmentInfoDto;
import response.SimulatorResponse;
import simulator.definition.entity.EntityDefinition;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.world.WorldDefinition;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.property.api.PropertyInstance;

import java.util.List;
import java.util.Map;

public interface EstablishmentManager {
    void establishSimulation(WorldDefinition worldDefinitionDefnition);
    EnvironmentInstance establishEnvironment();
    Map<String, List<EntityInstance>> createEntitiesInstances();
    List<EntityInstance> createSingleEntityInstances(EntityDefinition entityDefinition);
    Map<String, PropertyInstance> createPropertyInstances(Map<String, AbstractPropertyDefinition> propertyDefinitions);
    EstablishedEnvironmentInfoDto getEstablishedEnvironmentInfo();
}
