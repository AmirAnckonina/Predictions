package simulator.builder.impl.xml;

import resources.jaxb.schema.generated.*;
import simulator.builder.api.abstracts.AbstractFileComponentBuilder;
import simulator.builder.api.interfaces.WorldBuilder;
import simulator.builder.utils.exception.WorldBuilderException;
import simulator.builder.utils.file.XmlBuilderUtils;
import simulator.builder.validator.api.WorldBuilderContextValidator;
import simulator.builder.utils.file.WorldBuilderFileUtils;
import simulator.builder.utils.file.enums.eDataFileType;
import simulator.definition.entity.EntityDefinition;
import simulator.definition.environment.Environment;
import simulator.definition.rule.Rule;
import simulator.definition.spaceGrid.SpaceGrid;
import simulator.definition.termination.Termination;
import simulator.definition.threadCount.ThreadCount;
import simulator.definition.world.WorldDefinition;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlWorldBuilder extends AbstractFileComponentBuilder implements WorldBuilder {

    private PRDWorldDefinition generatedWorldDefinition;
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
                    generatedWorldDefinition = XmlBuilderUtils.getGeneratedClassFromFile(dataFile, JAXB_PACKAGE_NAME);
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
    public WorldDefinition buildWorld() {
        ThreadCount threadCount = buildThreadCount();
        SpaceGrid spaceGrid = buildSpaceGrid();
        Environment environment = buildEnvironment();
        Map<String, EntityDefinition> entities = buildEntities();
        List<Rule> rules = buildRules();
        Termination termination = buildTermination();

        return new WorldDefinition(threadCount, spaceGrid, environment, entities, rules, termination);
    }

    @Override
    public ThreadCount buildThreadCount() {
        return new ThreadCount(generatedWorldDefinition.getPRDThreadCount());
    }

    @Override
    public SpaceGrid buildSpaceGrid() {
        PRDWorldDefinition.PRDGrid generatedGrid = generatedWorldDefinition.getPRDGrid();
        return new SpaceGrid(generatedGrid.getRows(), generatedGrid.getColumns());
    }

    @Override
    public Environment buildEnvironment() {

        PRDEnvironment generatedEnv = generatedWorldDefinition.getPRDEnvironment();
        return new XmlEnvironmentBuilder(generatedEnv, contextValidator).buildEnvironment();
    }

    @Override
    public Map<String, EntityDefinition> buildEntities() {

        Map<String, EntityDefinition> entities = new HashMap<>();

        List<PRDEntity> generatedEntityList =  generatedWorldDefinition.getPRDEntities().getPRDEntity();
        for (PRDEntity genEntity: generatedEntityList) {
            EntityDefinition newEntityDefinition = new XmlEntityBuilder(genEntity, contextValidator).buildEntity();
            entities.put(newEntityDefinition.getName(), newEntityDefinition);
        }
        return entities;
    }

    public EntityDefinition buildSecondaryEntity() {

        PRDEntity generatedSecondaryEntity =  generatedWorldDefinition.getPRDEntities().getPRDEntity().get(1);
        return new XmlEntityBuilder(generatedSecondaryEntity, contextValidator).buildEntity();
    }

    @Override
    public List<Rule> buildRules() {
        List<Rule> rules = new ArrayList<>();

        for (PRDRule generatedRule : generatedWorldDefinition.getPRDRules().getPRDRule()) {
            Rule newRule = new XmlRuleBuilder(generatedRule, contextValidator).buildRule();
            rules.add(newRule);
        }

        return rules;
    }

    @Override
    public Termination buildTermination() {
        PRDTermination generatedTermination = generatedWorldDefinition.getPRDTermination();
        return new XmlTerminationBuilder(generatedTermination).buildTermination();
    }

}
