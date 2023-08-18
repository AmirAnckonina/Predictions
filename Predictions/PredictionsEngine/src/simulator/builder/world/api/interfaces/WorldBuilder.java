package simulator.builder.world.api.interfaces;

import simulator.definition.entity.Entity;
import simulator.definition.environment.Environment;
import simulator.definition.rule.Rule;
import simulator.definition.termination.Termination;
import simulator.definition.world.World;

import java.util.List;

public interface WorldBuilder {
    World buildWorld();
    Environment buildEnvironment();
    Entity buildPrimaryEntity();
    List<Rule> buildRules();
    Termination buildTermination();

}
