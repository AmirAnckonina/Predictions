package simulator.definition.world;

import simulator.definition.entity.Entity;
import simulator.definition.environment.Environment;
import simulator.definition.rule.Rule;
import simulator.definition.termination.Termination;

import java.util.*;

public class World {
    private Environment environment;
    private Entity primaryEntity;
    private List<Rule> rules;
    private Termination termination;

    public World(Environment environment, Entity primaryEntity, List<Rule> rules, Termination termination) {
        this.environment = environment;
        this.primaryEntity = primaryEntity;
        this.rules = rules;
        this.termination = termination;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
