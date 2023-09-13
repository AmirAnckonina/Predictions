package simulator.execution.instance.world.impl;

import simulator.definition.board.api.SpaceGridInstance;
import simulator.definition.rule.Rule;
import simulator.definition.termination.Termination;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.world.api.WorldInstance;

import java.util.List;
import java.util.Map;

public class WorldInstanceImpl implements WorldInstance {

    private EnvironmentInstance environmentInstance;
    private Map<String, List<EntityInstance>> entitiesInstances;
    private List<Rule> rules;
    private Termination termination;
    private SpaceGridInstance spaceGrid;

    public WorldInstanceImpl(
            EnvironmentInstance environmentInstance,
            Map<String, List<EntityInstance>> entitiesInstances,
            List<Rule> rules,
            Termination termination,
            SpaceGridInstance spaceGrid) {

        this.environmentInstance = environmentInstance;
        this.entitiesInstances = entitiesInstances;
        this.rules = rules;
        this.termination = termination;
        this.spaceGrid = spaceGrid;
    }

    @Override
    public EnvironmentInstance getEnvironmentInstance() {
        return environmentInstance;
    }

    @Override
    public Map<String, List<EntityInstance>> getEntitiesInstances() {
        return this.entitiesInstances;
    }

    @Override
    public List<EntityInstance> getEntityInstancesByEntityName(String entityName) {
        return entitiesInstances.get(entityName);
    }

    @Override
    public SpaceGridInstance getSpaceGrid() {
        return this.spaceGrid;
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
