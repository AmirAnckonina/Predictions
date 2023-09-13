package simulator.manualSetup.manager.api;

import simulator.definition.environment.EnvironmentDefinition;
import enums.PropertyType;
import simulator.definition.world.WorldDefinition;

public interface ManualSimulationSetupManager {
    void setEntityDefinitionPopulation(WorldDefinition worldDefinition, String entityName, Integer population);
    void setFixedValueToEnvironmentPropertyDefinition(String propName, PropertyType type, String value, EnvironmentDefinition environmentDefinition);
    void setSelectedEnvironmentPropertiesValue(WorldDefinition worldDefinition, String propName, String type, String value);
}
