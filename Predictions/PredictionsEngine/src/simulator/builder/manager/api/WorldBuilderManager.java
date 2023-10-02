package simulator.builder.manager.api;

import dto.worldBuilder.simulationComponents.EnvironmentPropertiesDto;
import dto.worldBuilder.simulationComponents.EnvironmentPropertyDto;
import dto.worldBuilder.SimulationWorldDetailsDto;
import dto.worldBuilder.SimulationWorldNamesDto;
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
