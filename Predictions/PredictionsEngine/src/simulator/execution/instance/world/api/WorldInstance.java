package simulator.execution.instance.world.api;

import simulator.definition.board.api.SpaceGridInstance;
import simulator.definition.rule.Rule;
import simulator.definition.termination.Termination;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.environment.api.EnvironmentInstance;

import java.util.List;
import java.util.Map;

public interface WorldInstance {
    void setRules(List<Rule> rule);
    void setTermination(Termination termination);
    Termination getTermination();
    List<Rule> getRules();
    EnvironmentInstance getEnvironmentInstance();
    Map<String, List<EntityInstance>> getEntitiesInstances();
    List<EntityInstance> getEntityInstancesByEntityName(String entityName);
    SpaceGridInstance getSpaceGrid();

}
