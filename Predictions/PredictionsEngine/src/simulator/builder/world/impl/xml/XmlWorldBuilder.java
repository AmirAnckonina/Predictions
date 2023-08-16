package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.*;
import simulator.builder.world.api.AbstractFileComponentBuilder;
import simulator.builder.world.api.WorldBuilder;
import simulator.builder.world.impl.xml.utils.XmlBuilderUtils;
import simulator.builder.world.utils.WorldBuilderUtils;
import simulator.builder.world.utils.enums.eDataFileType;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.builder.world.validator.api.WorldContextBuilderHelper;
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

    public XmlWorldBuilder(String filePath, WorldContextBuilderHelper contextValidator) {
        super(filePath, contextValidator);
        worldBuilderFileSetup(filePath);
    }

    public void worldBuilderFileSetup(String filePath) {
        // The file should be already set in this stage.
            boolean isExist = contextValidator.validateFileExist(filePath);
            boolean isXmlFile = contextValidator.validateFileType(
                     eDataFileType.XML, WorldBuilderUtils.getDataFileTypeByFileExtension(filePath));
            if (isExist && isXmlFile) {
                try {
                    File dataFile = WorldBuilderUtils.getFileByPath(filePath);
                    generatedWorld = XmlBuilderUtils.getGeneratedClassFromFile(dataFile, JAXB_PACKAGE_NAME);
                }
                catch (Exception ex) {
                    throw new WorldBuilderException("An issue detected while accessing the generated world class.");
                }

            } else {
                throw new WorldBuilderException("The data source file is invalid.");
            }

    }

    /**
     * // The order is mandatory! buildEnv and Entities ahould be before Rules.
     * @return
     */
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
        return new XmlEnvironmentBuilder(generatedEnv, contextValidator).buildEnvironment();
    }

    @Override
    public List<Entity> buildEntities() {
        List<Entity> entities = new ArrayList<>();

        List<PRDEntity> generatedEntitiesList =  generatedWorld.getPRDEntities().getPRDEntity();
        for (PRDEntity singleGeneratedEntity : generatedEntitiesList) {
            Entity newEntity = new XmlEntityBuilder(singleGeneratedEntity, contextValidator).buildEntity();
            entities.add(newEntity);
        }

        return entities;
    }

    @Override
    public List<Rule> buildRules() {
        List<Rule> rules = new ArrayList<>();

        for (PRDRule generatedRule : generatedWorld.getPRDRules().getPRDRule()) {
            Rule newRule = new XmlRuleBuilder(generatedRule, contextValidator).buildRule();
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
