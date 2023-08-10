package simulator.builder.mainBuilder.impl.xml;

import resources.jaxb.schema.generated.*;
import simulator.builder.componentsBuilder.impl.xml.XmlEntityBuilder;
import simulator.builder.componentsBuilder.impl.xml.XmlEnvironemntBuilder;
import simulator.builder.componentsBuilder.impl.xml.XmlRuleBuilder;
import simulator.builder.componentsBuilder.impl.xml.XmlTerminationBuilder;
import simulator.builder.mainBuilder.api.AbstractWorldBuilder;
import simulator.builder.mainBuilder.impl.xml.utils.XmlWorldBuilderUtils;
import simulator.definition.entity.Entity;
import simulator.definition.environment.Environment;
import simulator.definition.rule.Rule;
import simulator.definition.rule.action.Action;
import simulator.definition.termination.Termination;
import simulator.definition.world.World;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class XmlWorldBuilder extends AbstractWorldBuilder {
    private File dataSrcFile;
    private PRDWorld generatedWorld;
    private World world;
    private XmlWorldBuilderUtils xmlWorldUtils;
    private final static String JAXB_PACKAGE_NAME = "jaxb.schema.generated";

    public XmlWorldBuilder() {
        super(new XmlEnvironemntBuilder(), new XmlEntityBuilder(), new XmlRuleBuilder(), new XmlTerminationBuilder());
    }
    public void setDataSrcFile(File dataSrcFile) {

        this.dataSrcFile = dataSrcFile;
    }

    @Override
    public World buildWorld() {

        try {
            generatedWorld = xmlWorldUtils.getGeneratedWorldFromFile(dataSrcFile, JAXB_PACKAGE_NAME);
            Environment environment = buildEnvironment();
            List<Entity> entities = buildEntities();
            List<Rule> rules = buildRules();
            Termination termination = buildTermination();

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return world;
    }

    @Override
    public Environment buildEnvironment() {

        Environment environment = new Environment();
        try {

            PRDEvironment generatedEnv = generatedWorld.getPRDEvironment();
            xmlWorldUtils.MapEnvironment(generatedEnv, environment);
            return environment;

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Entity> buildEntities() {
        List<Entity> entities = new ArrayList<>();
        try {

            PRDEntities generatedEntities = generatedWorld.getPRDEntities();
            xmlWorldUtils.MapEntities(generatedEntities, entities);
            return entities;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Rule> buildRules() {
        List<Rule> rules = new ArrayList<>();
        try {

            PRDRules generatedRules = generatedWorld.getPRDRules();
            xmlWorldUtils.MapRules(generatedRules, rules);
            return rules;
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public Termination buildTermination() {
        Termination termination = new Termination();
        try {

            PRDTermination generatedTermination = generatedWorld.getPRDTermination();
            xmlWorldUtils.MapTermination(generatedTermination, termination);
            return termination;

        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public Entity buildEntity() {
        return null;
    }

    @Override
    public Rule buildRule() {
        return null;
    }

    @Override
    public Action buildAction() {
        return null;
    }
}
