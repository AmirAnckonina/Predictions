package simulator.manualSetup.manager.api;

import response.SimulatorResponse;
import simulator.definition.environment.EnvironmentDefinition;
import simulator.definition.property.utils.enums.PropertyType;
import simulator.definition.world.WorldDefinition;

public interface ManualSimulationSetupManager {
    SimulatorResponse setEntityDefinitionPopulation(WorldDefinition worldDefinition, String entityName, Integer population);
    void setFixedValueToEnvironmentPropertyDefinition(String propName, PropertyType type, String value, EnvironmentDefinition environmentDefinition);
    SimulatorResponse setSelectedEnvironmentPropertiesValue(WorldDefinition worldDefinition, String propName, String type, String value);
}
