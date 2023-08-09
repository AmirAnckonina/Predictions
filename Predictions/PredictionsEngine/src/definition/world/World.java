package definition.world;

import definition.environment.Environment;
import definition.rule.RuleList;
import definition.termination.Termination;

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
