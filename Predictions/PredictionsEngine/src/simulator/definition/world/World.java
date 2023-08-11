package simulator.definition.world;

import simulator.definition.entity.Entity;
import simulator.definition.environment.Environment;
import simulator.definition.rule.RuleList;
import simulator.definition.termination.Termination;

import java.util.*;
import java.util.stream.Collectors;

public class World {
    private Environment environment;
    private Map<String,Entity> entities;
    private RuleList rules;
    private Termination termination;
    private Integer ticks;

    public World(Environment env, Map<String,Entity> entityList, RuleList rules, Termination termination, Integer ticks) {
        this.environment = env;
        // Clone the list
        this.entities = new HashMap<>(entityList);
        this.rules = rules;
        this.termination = termination;
        this.ticks = ticks;
    }

    // c'tor for WorldDto (builder)
    public World() {

    }

    public Integer getTicks() {
        return ticks;
    }

}
