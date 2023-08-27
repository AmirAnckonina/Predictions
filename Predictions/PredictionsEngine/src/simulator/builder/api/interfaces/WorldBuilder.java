package simulator.builder.api.interfaces;

import simulator.definition.entity.Entity;
import simulator.definition.environment.Environment;
import simulator.definition.rule.Rule;
import simulator.definition.termination.Termination;
import simulator.definition.world.World;

import java.util.List;
import java.util.Map;

public interface WorldBuilder {
    World buildWorld();
    Environment buildEnvironment();
    Map<String, Entity> buildEntities();
    List<Rule> buildRules();
    Termination buildTermination();

}
