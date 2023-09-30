package simulator.definition.world;

import simulator.definition.environment.EnvironmentDefinition;
import simulator.definition.rule.Rule;
import simulator.definition.spaceGrid.SpaceGridDefinition;
import simulator.definition.termination.Termination;
import simulator.definition.threadCount.ThreadCountDefinition;
import simulator.definition.entity.EntityDefinition;

import java.util.*;

public class WorldDefinition {
    private ThreadCountDefinition threadCountDefinition;
    private SpaceGridDefinition spaceGridDefinition;
    private EnvironmentDefinition environmentDefinition;
    private Map<String,EntityDefinition> entities;
    private List<Rule> rules;
    private Integer sleepTimeBeforeTick;
    private Termination termination;
    public WorldDefinition() {

    }
    public WorldDefinition(
            SpaceGridDefinition spaceGridDefinition,
            EnvironmentDefinition environmentDefinition,
            Map<String, EntityDefinition> entities,
            List<Rule> rules,
            Integer sleepTimeBeforeTick) {

        this.spaceGridDefinition = spaceGridDefinition;
        this.environmentDefinition = environmentDefinition;
        this.entities = entities;
        this.rules = rules;
        this.sleepTimeBeforeTick = sleepTimeBeforeTick;
    }

    public EnvironmentDefinition getEnvironment() {

        return environmentDefinition;
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

    public void setTermination(Termination termination) {
        this.termination = termination;
    }

    public void setThreadCountDefinition(ThreadCountDefinition threadCountDefinition) {
        this.threadCountDefinition = threadCountDefinition;
    }

    public Termination getTermination() {
        return termination;
    }

    public ThreadCountDefinition getThreadCountDefinition() {
        return threadCountDefinition;
    }

    public SpaceGridDefinition getSpaceGridDefinition() {
        return spaceGridDefinition;
    }
}
