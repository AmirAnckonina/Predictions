package simulator.manualSetup.manager.api;

import simulator.definition.environment.EnvironmentDefinition;
import enums.PropertyType;
import simulator.definition.world.WorldDefinition;

public interface ManualSimulationSetupManager {
    void setEntityDefinitionPopulation(WorldDefinition worldDefinition, String entityName, Integer population);
    void setFixedValueToEnvironmentPropertyDefinition(String propName, PropertyType type, String value, EnvironmentDefinition environmentDefinition);
    void setSelectedEnvironmentPropertiesValue(WorldDefinition worldDefinition, String propName, String type, String value);
    <T> void setEnvironmentPropertyValue(WorldDefinition worldDefinition, String envPropertyName, T envPropertyValue);
    void resetSingleEntityPopulation(WorldDefinition worldDefinition, String entityName);
    void resetSingleEnvironmentVariable(WorldDefinition worldDefinition, String envVarName);
}
