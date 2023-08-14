package simulator.builder.world.api;

import simulator.definition.entity.Entity;
import simulator.definition.environment.Environment;
import simulator.definition.rule.Rule;
import simulator.definition.termination.Termination;
import simulator.definition.world.World;

import java.util.List;

public interface WorldBuilder {
    World buildWorld();
    Environment buildEnvironemnt();
    List<Entity> buildEntities();
    List<Rule> buildRules();
    Termination buildTermination();

}
