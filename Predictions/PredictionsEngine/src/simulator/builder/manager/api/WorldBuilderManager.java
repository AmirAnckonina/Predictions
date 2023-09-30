package simulator.builder.manager.api;

import dto.EnvironmentPropertiesDto;
import dto.EnvironmentPropertyDto;
import dto.SimulationWorldDetailsDto;
import dto.SimulationWorldNamesDto;
import simulator.definition.world.WorldDefinition;

import java.io.InputStream;
import java.util.List;

public interface WorldBuilderManager {
    SimulationWorldDetailsDto buildSimulationWorld(InputStream xmlFile);
    SimulationWorldDetailsDto getSimulationWorldDetailsByName(String simulationWorldName);
    EnvironmentPropertiesDto getEnvironmentPropertiesDefinition(String simulationWorldName);
    WorldDefinition getWorldDefinition(String simulationWorldName);
    List<EnvironmentPropertyDto> getAllEnvironmentProperties(String simulationWorldName);
    Integer getMaxPopulationSize(String simulationWorldName);

    SimulationWorldNamesDto getAllLoadedSimulationWorldNames();
}
