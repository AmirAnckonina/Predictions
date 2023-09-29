package simulator.builder.validator.impl;

import simulator.builder.utils.exception.WorldBuilderManagerException;
import simulator.builder.validator.api.WorldBuilderContextValidator;
import simulator.builder.utils.file.enums.DataFileType;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.ExpressionMethodType;
import enums.PropertyType;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WorldBuilderContextValidatorImpl implements WorldBuilderContextValidator {
    private Map<String, PropertyType> environmentPropertiesToTypeMapper;
    private Set<String> allEntities;
    private Map<String, Map<String, PropertyType>> entitiesPropertiesToTypeMapper;
    private Set<ExpressionMethodType> simulatorSystemMethods;

    public WorldBuilderContextValidatorImpl() {
        this.environmentPropertiesToTypeMapper = new HashMap<>();
        this.allEntities = new HashSet<>();
        // Please note, while adding new entity to "entityPropertiesMapper", a new set should be initialize!!!!
        this.entitiesPropertiesToTypeMapper = new HashMap<>();
        this.simulatorSystemMethods = new HashSet<>(
                Arrays.asList(ExpressionMethodType.ENVIRONMENT, ExpressionMethodType.RANDOM));
    }


    @Override
    public boolean validateFileExist(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }

    @Override
    public boolean validateFileType(DataFileType actualDataFileType, DataFileType expectedDataFileType) {
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
    public boolean validatePrimaryEntityPropertyUniqueness(String entityName, String entityPropertyName) {
        return !entitiesPropertiesToTypeMapper.get(entityName).containsKey(entityPropertyName);
    }

    @Override
    public boolean validateActionEntityContext(String entityName) {
        return allEntities.contains(entityName);
    }

    @Override
    public boolean validateActionEntityPropertyContext(String entityName, String entityPropertyName) {
        boolean entityPropValid = false;
        if (entitiesPropertiesToTypeMapper.containsKey(entityName)) {
            entityPropValid = entitiesPropertiesToTypeMapper.get(entityName).containsKey(entityPropertyName);
        }

        return entityPropValid;
    }

    @Override
    public void addEnvironmentProperty(String newEnvPropName, PropertyType type) {
        environmentPropertiesToTypeMapper.put(newEnvPropName, type);
    }

    @Override
    public void addEntityProperty(String entityName, String entityProperty, PropertyType type) {
        if (entitiesPropertiesToTypeMapper.containsKey(entityName)) {
            entitiesPropertiesToTypeMapper.get(entityName).put(entityProperty, type);
        } else {
            throw new WorldBuilderManagerException(
                    "entity name couln't be found for adding new property to contextValidator."
            );
        }


    }

    @Override
    public void addEntity(String entityName) {
        this.allEntities.add(entityName);
        this.entitiesPropertiesToTypeMapper.put(entityName, new HashMap<>());
    }


    @Override
    public boolean isEnvironmentProperty(String propertySuspect) {
        return this.environmentPropertiesToTypeMapper.containsKey(propertySuspect);
    }

    @Override
    public boolean validateSpaceGridDimensions(int rows, int cols) {
        return rows >= 10 && rows <= 100 && cols >= 10 && cols <= 100;
    }

    @Override
    public boolean validateActionContextProcedure(String entityName, String entityPropertyName) {

        boolean entityContextValid = validateActionEntityContext(entityName);

        boolean entityPropertyValid;
        if (entityPropertyName != null) {
            entityPropertyValid =
                    validateActionEntityPropertyContext(
                            entityName, entityPropertyName);
        }
        else { entityPropertyValid = true; }

        return entityContextValid && entityPropertyValid;
    }

    @Override
    public boolean isEntityPropertyNameExists(String rawExpression) {

        boolean isEntityProp = false;

        for (Map.Entry<String, Map<String, PropertyType>> entityPropertiesNameTypeMapper
                : entitiesPropertiesToTypeMapper.entrySet()) {
            if (entityPropertiesNameTypeMapper.getValue().containsKey(rawExpression)) {
                isEntityProp = true;
                break;
            }
        }

        return isEntityProp;
    }

    @Override
    public boolean validateEntityPropertyAsExpected(String entityName, String propertyName, PropertyType expectedType) {
        return this.entitiesPropertiesToTypeMapper.get(entityName).get(propertyName) == expectedType;
    }

    /**
     *
     * @param propertyName
     * @param expectedType
     * @return
     */
    public boolean validateEnvironemntPropertyTypeAsExpected(String propertyName, PropertyType expectedType){
        return this.environmentPropertiesToTypeMapper.get(propertyName) == expectedType;
    }

    /**
     *
     * @param propertyName
     * Impllemented Currently only of propertie of the PrimaryEntity
     * @return
     */
    @Override
    public PropertyType getEntityPropertyType(String entityName, String propertyName) {
        return entitiesPropertiesToTypeMapper.get(entityName).get(propertyName);
    }

    @Override
    public PropertyType getEntityPropertyTypeWithoutEntityNameMentioned(String propertyName) {

        PropertyType propType = null;

        for (Map.Entry<String, Map<String, PropertyType>> entityPropertiesMap
                : entitiesPropertiesToTypeMapper.entrySet()) {
            if (entityPropertiesMap.getValue().containsKey(propertyName)) {
                propType = getEntityPropertyType(entityPropertiesMap.getKey(), propertyName);
                break;
            }
        }

        if (propType == null) {
            throw new WorldBuilderManagerException("couldn't find the type of the property, under getEntityPropertyTypeWithoutEntityNameMentioned.");
        }

        return propType;
    }

    @Override
    public PropertyType getEnvironmentPropertyType(String propertyName) {
        return environmentPropertiesToTypeMapper.get(propertyName);
    }

}
