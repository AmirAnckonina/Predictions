package simulator.definition.world;

import simulator.definition.entity.Entity;
import simulator.definition.environment.Environment;
import simulator.definition.rule.Rule;
import simulator.definition.termination.Termination;

import java.util.*;

public class World {
    private Environment environment;
    private Entity primaryEntity;
    private Entity secondaryEntity;
    private List<Rule> rules;
    private Termination termination;

    public World(Environment environment, Entity primaryEntity, List<Rule> rules, Termination termination) {
        this.environment = environment;
        this.primaryEntity = primaryEntity;
        // this.secondaryEntity = secondaryEntity;
        this.rules = rules;
        this.termination = termination;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Entity getPrimaryEntity() {
        return primaryEntity;
    }

    public Entity getSecondaryEntity() {
        return secondaryEntity;
    }

    public void setPrimaryEntity(Entity primaryEntity) {
        this.primaryEntity = primaryEntity;
    }

    public void setSecondaryEntity(Entity secondaryEntity) {
        this.secondaryEntity = secondaryEntity;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public Termination getTermination() {
        return termination;
    }

}
