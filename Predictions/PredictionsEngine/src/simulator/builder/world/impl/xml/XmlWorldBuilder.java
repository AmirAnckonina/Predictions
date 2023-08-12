package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.*;
import simulator.builder.world.api.AbstractFileComponentBuilder;
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

public class XmlWorldBuilder extends AbstractFileComponentBuilder<World> implements WorldBuilder {


    private PRDWorld generatedWorld;
    private XmlBuilderUtils xmlWorldUtils;
    private final static String JAXB_PACKAGE_NAME = "jaxb.schema.generated";

    public XmlWorldBuilder(PRDWorld generatedWorld) {
        this.generatedWorld = generatedWorld;
    }

    public XmlWorldBuilder() {
        super();
    }

    public XmlWorldBuilder(File dataSrcFile) {
        super(dataSrcFile);
        setDataSrcFile(dataSrcFile);
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
            Environment environment = buildEnvironment();
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
    public Environment buildEnvironment() {

        Environment environment;

        try {

            PRDEvironment generatedEnv = generatedWorld.getPRDEvironment();
            XmlEnvironmentBuilder xmlEnvBuilder = new XmlEnvironmentBuilder(generatedEnv);
            environment = xmlEnvBuilder.buildEnvironment();
            return environment;

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Entity> buildEntities() {
        List<Entity> entities = new ArrayList<>();
        try {

            List<PRDEntity> generatedEntities = generatedWorld.getPRDEntities().getPRDEntity();
            XmlEntityBuilder entityBuilder = new XmlEntityBuilder();
            for (PRDEntity generatedEntity : generatedEntities) {
                entityBuilder.setGeneratedEntity(generatedEntity);
                Entity newEntity = entityBuilder.buildEntity();
                entities.add(newEntity);
            }

            return entities;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Rule> buildRules() {
        List<Rule> rules = new ArrayList<>();
        try {

            List<PRDRule> generatedRules = generatedWorld.getPRDRules().getPRDRule();
            for (PRDRule generatedRule : generatedRules) {
                XmlRuleBuilder ruleBuilder = new XmlRuleBuilder(generatedRule);
                Rule newRule = ruleBuilder.buildRule();
                rules.add(newRule);
            }

            return rules;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Termination buildTermination() {
        Termination termination;

        try {

            PRDTermination generatedTermination = generatedWorld.getPRDTermination();
            XmlTerminationBuilder xmlTerminationBuilder = new XmlTerminationBuilder(generatedTermination);
            termination = xmlTerminationBuilder.buildTermination();
            return termination;

        } catch (Exception ex) {
            return null;
        }
    }

}
