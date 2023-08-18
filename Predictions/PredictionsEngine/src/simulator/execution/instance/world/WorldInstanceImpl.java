package simulator.execution.instance.world;

import simulator.definition.rule.Rule;
import simulator.definition.termination.Termination;
import simulator.execution.context.api.EnvironmentInstance;
import simulator.execution.context.api.WorldInstance;
import simulator.execution.instance.entity.EntityInstanceImpl;
import simulator.execution.instance.entity.EntityInstancesImpl;

import java.util.List;

public class WorldInstanceImpl implements WorldInstance {
    private EnvironmentInstance environmentInstance;
    private List<EntityInstanceImpl> primaryEntities;
    private List<EntityInstanceImpl> secondaryEntities;

    private List<Rule>  rules;
    private Termination termination;

    public WorldInstanceImpl() {
        this(null, null, null);
    }

    public WorldInstanceImpl(EnvironmentInstance environmentInstance, List<EntityInstanceImpl> primaryEntities, List<EntityInstanceImpl> secondaryEntities) {
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

    public List<EntityInstanceImpl> getPrimaryEntities() {
        return primaryEntities;
    }

    public void setPrimaryEntities(List<EntityInstanceImpl> primaryEntities) {
        this.primaryEntities = primaryEntities;
    }

    public List<EntityInstanceImpl> getSecondaryEntities() {
        return secondaryEntities;
    }

    public void setSecondaryEntities(List<EntityInstanceImpl> secondaryEntities) {
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
