package simulator.builder.world.validator.impl;

import javafx.util.Pair;
import simulator.builder.world.utils.enums.eDataFileType;
import simulator.builder.world.validator.api.WorldContextBuilderHelper;
import simulator.definition.property.api.AbstractPropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.rule.action.utils.eActionType;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WorldContextBuilderHelperImpl implements WorldContextBuilderHelper {
    private Map<String, ePropertyType> environmentPropertiesToTypeMapper;
    private Set<String> entities;
    private Map<String,Set<String>> entityPropertiesMapper;
    private Set<String> simulatorSystemMethods;

    public WorldContextBuilderHelperImpl() {
        this.environmentPropertiesToTypeMapper = new HashMap<>();
        this.entities = new HashSet<>();
        // Please note, while adding new entity to "entityPropertiesMapper", a new set should be initialize!!!!
        this.entityPropertiesMapper = new HashMap<>();
        this.simulatorSystemMethods = new HashSet<>();
    }


    @Override
    public boolean validateFileExist(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }

    @Override
    public boolean validateFileType(eDataFileType actualDataFileType, eDataFileType expectedDataFileType) {
        return actualDataFileType == expectedDataFileType;
    }

    /**
     *
     * @param envPropertyName
     * // Note that if the new name doesn't exist -> the method will return true
     * // true = verified and could be used by the called class
     * @return
     */
    @Override
    public boolean validateEnvironmentPropertyUniqueness(String envPropertyName) {
        return !environmentPropertiesToTypeMapper.containsKey(envPropertyName);
    }

    /**
     *
     * @param entityPropertyName
     * // Note that if the new name doesn't exist -> the method will return true
     * // true = verified and could be used by the called class
     * @return
     */
    @Override
    public boolean validateEntityPropertyUniqueness(String entityName ,String entityPropertyName) {
        return !entityPropertiesMapper.get(entityName).contains(entityPropertyName);
    }

    @Override
    public boolean validateActionEntityContext(String entityName) {
        return false;
    }

    @Override
    public boolean validateActionEntityPropertyContext(String entityName, String entityPropertyName) {
        return false;
    }

    @Override
    public boolean validateActionArguments(eActionType actionType, String... actionArgs) {

        return false;
    }

    @Override
    public void addEnvironemntProperty(String newEnvPropName, ePropertyType type) {
        environmentPropertiesToTypeMapper.put(newEnvPropName, type);
    }

    @Override
    public void addPropertyToEntityProperties(String entity, String entityProperty) {
        entityPropertiesMapper.get(entity).add(entityProperty);
    }

    @Override
    public void addEntity(String entity) {
        entities.add(entity);
        entityPropertiesMapper.put(entity, new HashSet<>());
    }

    @Override
    public boolean isProperty(String propertySuspect) {
        return this.environmentPropertiesToTypeMapper.containsKey(propertySuspect);
    }

    public boolean isAppropriateType(String propertyName, ePropertyType type){
        return this.environmentPropertiesToTypeMapper.get(propertyName) == type;
    }

}
