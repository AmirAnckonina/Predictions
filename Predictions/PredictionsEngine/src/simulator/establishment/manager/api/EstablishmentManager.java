package simulator.establishment.manager.api;

import dto.establishment.EstablishedEnvironmentInfoDto;
import simulator.definition.entity.EntityDefinition;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.world.WorldDefinition;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.execution.instance.world.api.WorldInstance;

import java.util.List;
import java.util.Map;

public interface EstablishmentManager {
    void establishSimulation(WorldDefinition worldDefinitionDefnition);
    Map<String, EntityDefinition> createEntityDefinitionMap();
    EnvironmentInstance establishEnvironment();
    Map<String, List<EntityInstance>> createAllEntitiesInstances();
    List<EntityInstance> createEntityInstances(EntityDefinition entityDefinition);
    EntityInstance createSingleEntityInstance(EntityDefinition entityDefinition, int id, int tickNoForProperties);
    Map<String, PropertyInstance> createPropertyInstances(Map<String, AbstractPropertyDefinition> propertyDefinitions, int tickNo);
    EstablishedEnvironmentInfoDto getEstablishedEnvironmentInfo();
    WorldInstance getEstablishedWorldInstance();
}
