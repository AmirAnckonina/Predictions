package simulator.execution.instance.world.impl;

import simulator.definition.rule.Rule;
import simulator.definition.termination.Termination;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.execution.instance.entity.impl.EntityInstanceImpl;



import java.util.List;

public class WorldInstanceImpl implements WorldInstance {
    private EnvironmentInstance environmentInstance;
    private List<EntityInstance> primaryEntities;
    private List<EntityInstance> secondaryEntities;

    private List<Rule>  rules;
    private Termination termination;

    public WorldInstanceImpl() {
        this(null, null, null);
    }

    public WorldInstanceImpl(EnvironmentInstance environmentInstance, List<EntityInstance> primaryEntities, List<EntityInstance> secondaryEntities) {
        this.environmentInstance = environmentInstance;
        this.primaryEntities = primaryEntities;
        this.secondaryEntities = secondaryEntities;
    }

    public EnvironmentInstance getEnvironmentInstance() {
        return environmentInstance;
    }

    public void setEnvironmentInstance(EnvironmentInstance environmentInstance) {
        this.environmentInstance = environmentInstance;
    }

    public List<EntityInstance> getPrimaryEntities() {
        return primaryEntities;
    }

    public void setPrimaryEntities(List<EntityInstance> primaryEntities) {
        this.primaryEntities = primaryEntities;
    }

    public List<EntityInstance> getSecondaryEntities() {
        return secondaryEntities;
    }

    public void setSecondaryEntities(List<EntityInstance> secondaryEntities) {
        this.secondaryEntities = secondaryEntities;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public Termination getTermination() {
        return termination;
    }

    public void setTermination(Termination termination) {
        this.termination = termination;
    }
}
