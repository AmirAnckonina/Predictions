package simulator.builder.manager.api;

import dto.EnvironmentPropertiesDto;
import dto.SimulationDetailsDto;
import response.SimulatorResponse;
import simulator.definition.world.WorldDefinition;

public interface WorldBuilderManager {
    SimulatorResponse buildSimulationWorld(String filePath);
    SimulatorResponse<SimulationDetailsDto> getSimulationWorldDetails();
    SimulatorResponse<EnvironmentPropertiesDto> getEnvironmentPropertiesDefinition();
    WorldDefinition getWorldDefinition();
}
