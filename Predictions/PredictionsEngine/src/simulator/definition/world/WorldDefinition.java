package simulator.definition.world;

import simulator.definition.entity.EntityDefinition;
import simulator.definition.environment.Environment;
import simulator.definition.rule.Rule;
import simulator.definition.spaceGrid.SpaceGrid;
import simulator.definition.termination.Termination;
import simulator.definition.threadCount.ThreadCount;

import java.util.*;

public class WorldDefinition {
    private ThreadCount threadCount;
    private SpaceGrid spaceGrid;
    private Environment environment;
    private Map<String, EntityDefinition> entities;
    private List<Rule> rules;
    private Termination termination;

    public WorldDefinition(
            ThreadCount threadCount,
            SpaceGrid spaceGrid,
            Environment environment,
            Map<String, EntityDefinition> entities,
            List<Rule> rules,
            Termination termination) {

        this.threadCount = threadCount;
        this.spaceGrid = spaceGrid;
        this.environment = environment;
        this.entities = entities;
        this.rules = rules;
        this.termination = termination;
    }

    public Environment getEnvironment() {

        return environment;
    }

    public EntityDefinition getEntityDefinitionByName(String name) {

        return entities.get(name);
    }

    public Map<String, EntityDefinition> getEntities() {
        return entities;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public Termination getTermination() {
        return termination;
    }

}
