package simulatorWorld.world;

import simulatorWorld.entity.EntityList;
import simulatorWorld.environment.Environment;
import simulatorWorld.rule.Rules;
import simulatorWorld.termination.Termination;

public class World {
    private Environment environment;
    private EntityList entities;
    private Rules rules;
    private Termination termination;

    public World(Environment env, EntityList entityList, Rules rules, Termination termination) {
        this.environment = env;
        this.entities = entityList;
        this.rules = rules;
        this.termination = termination;
    }

    // c'tor for WorldDto (builder)
    public World() {

    }

}
