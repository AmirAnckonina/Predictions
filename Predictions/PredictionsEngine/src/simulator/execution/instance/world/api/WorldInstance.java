package simulator.execution.instance.world.api;

import simulator.definition.rule.Rule;
import simulator.definition.termination.Termination;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.environment.api.EnvironmentInstance;

import java.util.List;

public interface WorldInstance {
    void setRules(List<Rule> rule);
    void setTermination(Termination termination);
    Termination getTermination();
    List<Rule> getRules();
    EnvironmentInstance getEnvironmentInstance();
    List<EntityInstance> getPrimaryEntityInstances();
    void setPrimaryEntityInstances(List<EntityInstance> primaryEntityInstances);
    void setEnvironmentInstance(EnvironmentInstance environmentInstance);


}
