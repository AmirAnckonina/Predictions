package simulator.execution.instance.world.impl;

import simulator.definition.rule.Rule;
import simulator.definition.termination.Termination;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.world.api.WorldInstance;

import java.util.List;

public class WorldInstanceImpl implements WorldInstance {
    private EnvironmentInstance environmentInstance;
    private List<EntityInstance> primaryEntityInstances;

    private List<Rule> rules;
    private Termination termination;
    public WorldInstanceImpl() {
        this(null, null, null);
    }

    public WorldInstanceImpl(EnvironmentInstance environmentInstance, List<EntityInstance> primaryEntities, List<EntityInstance> secondaryEntities) {
        this.environmentInstance = environmentInstance;
        this.primaryEntityInstances = primaryEntities;
        this.secondaryEntitiesInstances = secondaryEntities;
    }

    @Override
    public EnvironmentInstance getEnvironmentInstance() {
        return environmentInstance;
    }

    @Override
    public List<EntityInstance> getPrimaryEntityInstances() {
        return primaryEntityInstances;
    }

    @Override
    public void setPrimaryEntityInstances(List<EntityInstance> primaryEntityInstances) {
        this.primaryEntityInstances = primaryEntityInstances;
    }

    @Override
    public void setEnvironmentInstance(EnvironmentInstance environmentInstance) {
        this.environmentInstance = environmentInstance;
    }


    @Override
    public Termination getTermination() {
        return termination;
    }

    @Override
    public List<Rule> getRules() {
        return rules;
    }

    @Override
    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public void setTermination(Termination termination) {
        this.termination = termination;
    }
}
