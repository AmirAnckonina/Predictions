package simulatorWorld.world;

import simulatorWorld.entity.EntityList;
import simulatorWorld.environment.Environment;
import simulatorWorld.rule.RuleList;
import simulatorWorld.termination.Termination;

public class World {
    private Environment environment;
    private EntityList entities;
    private RuleList rules;
    private Termination termination;

    public World(Environment env, EntityList entityList, RuleList rules, Termination termination) {
        this.environment = env;
        this.entities = entityList;
        this.rules = rules;
        this.termination = termination;
    }

    // c'tor for WorldDto (builder)
    public World() {

    }

}
