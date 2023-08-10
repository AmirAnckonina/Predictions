package simulator.definition.world;

import simulator.definition.environment.Environment;
import simulator.definition.rule.RuleList;
import simulator.definition.termination.Termination;

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
