package simulator.builder.manager.api;

import dto.EnvironmentPropertiesDto;
import dto.SimulationDetailsDto;
import response.SimulatorResponse;
import simulator.definition.world.WorldDefinition;

public interface WorldBuilderManager {
    void buildSimulationWorld(String filePath);
    SimulationDetailsDto getSimulationWorldDetails();
    EnvironmentPropertiesDto getEnvironmentPropertiesDefinition();
    WorldDefinition getWorldDefinition();
}
