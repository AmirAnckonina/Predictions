package simulator.builder.mainBuilder.api;

import simulator.definition.entity.Entity;
import simulator.definition.environment.Environment;
import simulator.definition.rule.Rule;
import simulator.definition.rule.action.Action;
import simulator.definition.termination.Termination;
import simulator.definition.world.World;

import java.util.List;

public interface WorldBuilder {
    World buildWorld();
    Environment buildEnvironment();
    List<Entity> buildEntities();
    List<Rule> buildRules();
    Termination buildTermination();
    Entity buildEntity();
    Rule buildRule();
    Action buildAction();
}
