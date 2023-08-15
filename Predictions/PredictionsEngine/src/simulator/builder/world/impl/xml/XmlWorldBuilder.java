package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.*;
import simulator.builder.world.api.AbstractFileComponentBuilder;
import simulator.builder.world.api.RuleBuilder;
import simulator.builder.world.api.WorldBuilder;
import simulator.builder.world.impl.xml.utils.XmlBuilderUtils;
import simulator.definition.entity.Entity;
import simulator.definition.environment.Environment;
import simulator.definition.rule.Rule;
import simulator.definition.termination.Termination;
import simulator.definition.world.World;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlWorldBuilder extends AbstractFileComponentBuilder implements WorldBuilder {

    private PRDWorld generatedWorld;
    private final static String JAXB_PACKAGE_NAME = "jaxb.schema.generated";

    public XmlWorldBuilder(File dataSrcFile) {
        super(dataSrcFile);
        setDataSrcFile(dataSrcFile);
    }

    public XmlWorldBuilder(PRDWorld generatedWorld) {
        this.generatedWorld = generatedWorld;
    }

    @Override
    public void setDataSrcFile(File dataSrcFile) {
        super.setDataSrcFile(dataSrcFile);
        try {
            generatedWorld = XmlBuilderUtils.getGeneratedClassFromFile(dataSrcFile, JAXB_PACKAGE_NAME);
        }
        catch (Exception e) {

        }
    }

    @Override
    public World buildWorld() {

        try {
            Environment environment = buildEnvironemnt();
            List<Entity> entities = buildEntities();
            List<Rule> rules = buildRules();
            Termination termination = buildTermination();
            //return new World(environment, new Set<String, new Entity()>(), rules, termination);
            return new World();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Environment buildEnvironemnt() {
        PRDEvironment generatedEnv = generatedWorld.getPRDEvironment();
        return new XmlEnvironmentBuilder(generatedEnv).buildEnvironment();
    }

    @Override
    public List<Entity> buildEntities() {
        List<Entity> entities = new ArrayList<>();

        List<PRDEntity> generatedEntitiesList =  generatedWorld.getPRDEntities().getPRDEntity();
        for (PRDEntity singleGeneratedEntity : generatedEntitiesList) {
            Entity newEntity = new XmlEntityBuilder(singleGeneratedEntity).buildEntity();
            entities.add(newEntity);
        }

        return entities;
    }

    @Override
    public List<Rule> buildRules() {
        List<Rule> rules = new ArrayList<>();

        for (PRDRule generatedRule : generatedWorld.getPRDRules().getPRDRule()) {
            Rule newRule = new XmlRuleBuilder(generatedRule).buildRule();
            rules.add(newRule);
        }

        return rules;
    }

    @Override
    public Termination buildTermination() {
        PRDTermination generatedTermination = generatedWorld.getPRDTermination();
        return new XmlTerminationBuilder(generatedTermination).buildTermination();
    }

}
