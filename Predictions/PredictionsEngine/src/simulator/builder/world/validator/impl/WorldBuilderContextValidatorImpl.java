package simulator.builder.world.validator.impl;

import simulator.builder.world.utils.enums.eDataFileType;
import simulator.builder.world.utils.enums.eExpressionMethod;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.builder.world.validator.api.WorldBuilderContextValidator;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.rule.action.utils.eActionType;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WorldBuilderContextValidatorImpl implements WorldBuilderContextValidator {
    private Map<String, ePropertyType> environmentPropertiesToTypeMapper;
    private String primaryEntity;
    private String secondaryEntity;
    private Set<String> allEntities;
    private Map<String, ePropertyType> primaryEntityPropertiesToTypeMapper;
    private Map<String, ePropertyType> secondaryEntityPropertiesToTypeMapper;
    private Set<eExpressionMethod> simulatorSystemMethods;

    public WorldBuilderContextValidatorImpl() {
        this.environmentPropertiesToTypeMapper = new HashMap<>();
        this.allEntities = new HashSet<>();
        // Please note, while adding new entity to "entityPropertiesMapper", a new set should be initialize!!!!
        this.primaryEntityPropertiesToTypeMapper = new HashMap<>();
        this.simulatorSystemMethods = new HashSet<>(
                Arrays.asList(eExpressionMethod.ENVIRONMENT, eExpressionMethod.RANDOM));
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
    public boolean validatePrimaryEntityPropertyUniqueness(String entityPropertyName) {
        return !primaryEntityPropertiesToTypeMapper.containsKey(entityPropertyName);
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
    public void addEnvironmentProperty(String newEnvPropName, ePropertyType type) {
        environmentPropertiesToTypeMapper.put(newEnvPropName, type);
    }

    @Override
    public void addEntityProperty(String entityName, String entityProperty, ePropertyType type) {

        if (entityName.equals(primaryEntity)) {
            primaryEntityPropertiesToTypeMapper.put(entityProperty, type);

        } else if (entityName.equals(secondaryEntity)) {
            secondaryEntityPropertiesToTypeMapper.put(entityProperty, type);

        } else {
            throw new WorldBuilderException("entity name couln't be found for adding new property to contextValidator.");
        }
    }

    @Override
    public void addPrimaryEntity(String entity) {
        this.primaryEntity = entity;
        this.allEntities.add(entity);
    }

    @Override
    public void addSecondaryEntity(String entity) {
        this.secondaryEntity = entity;
        this.allEntities.add(entity);
    }

    @Override
    public boolean validateEnvironmentPropertyExist(String propertySuspect) {
        return this.environmentPropertiesToTypeMapper.containsKey(propertySuspect);
    }

    /**
     *
     * @param propertyName
     * @param type
     * @return
     */
    public boolean validateEnvironemntPropertyTypeAsExpected(String propertyName, ePropertyType type){
        return this.environmentPropertiesToTypeMapper.get(propertyName) == type;
    }

    /**
     *
     * @param propertyName
     * Impllemented Currently only of propertie of the PrimaryEntity
     * @return
     */
    @Override
    public ePropertyType getPropertyType(String propertyName) {
        return primaryEntityPropertiesToTypeMapper.get(propertyName);
    }

}
