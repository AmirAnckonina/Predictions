package simulator.builder.manager.api;

import dto.EnvironmentPropertiesDto;
import dto.EnvironmentPropertyDto;
import dto.SimulationDetailsDto;
import response.SimulatorResponse;
import simulator.definition.world.WorldDefinition;

import java.util.List;

public interface WorldBuilderManager {
    void buildSimulationWorld(String filePath);
    SimulationDetailsDto getSimulationWorldDetails();
    EnvironmentPropertiesDto getEnvironmentPropertiesDefinition();
    WorldDefinition getWorldDefinition();
    List<EnvironmentPropertyDto> getAllEnvironmentProperties();
}
