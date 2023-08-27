package simulator.definition.world;

import simulator.definition.entity.Entity;
import simulator.definition.environment.Environment;
import simulator.definition.rule.Rule;
import simulator.definition.termination.Termination;

import java.util.*;

public class World {
    private Environment environment;
    private Map<String, Entity> entities;
    private List<Rule> rules;
    private Termination termination;

    public World(Environment environment, Map<String ,Entity> entities, List<Rule> rules, Termination termination) {
        this.environment = environment;
        this.entities = entities;
        // this.secondaryEntity = secondaryEntity;
        this.rules = rules;
        this.termination = termination;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public Entity getEntityDefinitionByName(String name) {
        return entities.get(name);
    }

    public Map<String, Entity> getEntities() {
        return entities;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public Termination getTermination() {
        return termination;
    }

}
