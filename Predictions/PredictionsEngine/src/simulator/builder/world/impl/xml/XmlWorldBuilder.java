package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.*;
import simulator.builder.world.api.abstracts.AbstractFileComponentBuilder;
import simulator.builder.world.api.interfaces.WorldBuilder;
import simulator.builder.world.utils.file.XmlBuilderUtils;
import simulator.builder.world.utils.file.WorldBuilderFileUtils;
import simulator.builder.world.utils.file.enums.eDataFileType;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.builder.world.validator.api.WorldBuilderContextValidator;
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
    private final static String JAXB_PACKAGE_NAME = "resources.jaxb.schema.generated";

    public XmlWorldBuilder(String filePath, WorldBuilderContextValidator contextValidator) {
        super(filePath, contextValidator);
        worldBuilderFileSetup(filePath);
    }

    public void worldBuilderFileSetup(String filePath) {
        // The file should be already set in this stage.
            boolean isExist = contextValidator.validateFileExist(filePath);
            boolean isXmlFile = contextValidator.validateFileType(
                     eDataFileType.XML, WorldBuilderFileUtils.getDataFileTypeByFileExtension(filePath));
            if (isExist && isXmlFile) {
                try {
                    File dataFile = WorldBuilderFileUtils.getFileByPath(filePath);
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
        Environment environment = buildEnvironment();
        Entity primaryEntity = buildPrimaryEntity();
        // Entity secondaryEntity = buildSecondaryEntity();
        List<Rule> rules = buildRules();
        Termination termination = buildTermination();
        //return new World(environment, new Set<String, new Entity()>(), rules, termination);
        return new World(environment, primaryEntity, rules, termination);
    }

    @Override
    public Environment buildEnvironment() {
        PRDEvironment generatedEnv = generatedWorld.getPRDEvironment();
        return new XmlEnvironmentBuilder(generatedEnv, contextValidator).buildEnvironment();
    }

    @Override
    public Entity buildPrimaryEntity() {

        PRDEntity generatedPrimaryEntity =  generatedWorld.getPRDEntities().getPRDEntity().get(0);
        return new XmlEntityBuilder(generatedPrimaryEntity, contextValidator).buildEntity();
    }

    public Entity buildSecondaryEntity() {

        PRDEntity generatedSecondaryEntity =  generatedWorld.getPRDEntities().getPRDEntity().get(1);
        return new XmlEntityBuilder(generatedSecondaryEntity, contextValidator).buildEntity();
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
