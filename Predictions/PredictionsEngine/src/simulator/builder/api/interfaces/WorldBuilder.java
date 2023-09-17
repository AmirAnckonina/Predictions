package simulator.builder.api.interfaces;

import simulator.definition.entity.impl.EntityDefinition;
import simulator.definition.environment.EnvironmentDefinition;
import simulator.definition.rule.Rule;
import simulator.definition.spaceGrid.SpaceGridDefinition;
import simulator.definition.termination.Termination;
import simulator.definition.threadCount.ThreadCount;
import simulator.definition.world.WorldDefinition;

import java.util.List;
import java.util.Map;

public interface WorldBuilder {
    WorldDefinition buildWorld();
    ThreadCount buildThreadCount();
    SpaceGridDefinition buildSpaceGrid();
    EnvironmentDefinition buildEnvironment();
    Map<String, EntityDefinition> buildEntities();
    List<Rule> buildRules();
    Termination buildTermination();

}
