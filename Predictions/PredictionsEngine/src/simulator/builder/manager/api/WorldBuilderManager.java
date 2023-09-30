package simulator.builder.manager.api;

import dto.EnvironmentPropertiesDto;
import dto.EnvironmentPropertyDto;
import dto.SimulationWorldDetailsDto;
import simulator.definition.world.WorldDefinition;

import java.util.List;

public interface WorldBuilderManager {
    void buildSimulationWorld(String filePath);
    SimulationWorldDetailsDto getSimulationWorldDetailsByName(String simulationWorldName);
    EnvironmentPropertiesDto getEnvironmentPropertiesDefinition(String simulationWorldName);
    WorldDefinition getWorldDefinition(String simulationWorldName);
    List<EnvironmentPropertyDto> getAllEnvironmentProperties(String simulationWorldName);
    Integer getMaxPopulationSize(String simulationWorldName);

    List<String> getAllLoadedSimulationWorldNames();
}
