package simulator.builder.api.interfaces;

import simulator.definition.entity.EntityDefinition;
import simulator.definition.environment.EnvironmentDefinition;
import simulator.definition.rule.Rule;
import simulator.definition.spaceGrid.SpaceGridDefinition;
import simulator.definition.termination.Termination;
import simulator.definition.threadCount.ThreadCountDefinition;
import simulator.definition.world.WorldDefinition;

import java.util.List;
import java.util.Map;

public interface WorldBuilder {
    WorldDefinition buildWorld();

    Integer buildSleep();

    //ThreadCountDefinition buildThreadCount();
    SpaceGridDefinition buildSpaceGrid();
    EnvironmentDefinition buildEnvironment();
    Map<String, EntityDefinition> buildEntities();
    List<Rule> buildRules();

    //Termination buildTermination();
    String getSimulationWorldName();
    void setSimulationWorldName(String simulationWorldName);

}
