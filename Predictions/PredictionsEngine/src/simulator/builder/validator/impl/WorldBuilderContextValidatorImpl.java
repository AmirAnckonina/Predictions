package simulator.builder.validator.impl;

import simulator.builder.utils.exception.WorldBuilderException;
import simulator.builder.validator.api.WorldBuilderContextValidator;
import simulator.builder.utils.file.enums.eDataFileType;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.eExpressionMethod;
import simulator.definition.property.utils.enums.ePropertyType;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WorldBuilderContextValidatorImpl implements WorldBuilderContextValidator {
    private Map<String, ePropertyType> environmentPropertiesToTypeMapper;
    private Set<String> allEntities;
    private Map<String, Map<String, ePropertyType>> entitiesPropertiesToTypeMapper;
    private Set<eExpressionMethod> simulatorSystemMethods;

    public WorldBuilderContextValidatorImpl() {
        this.environmentPropertiesToTypeMapper = new HashMap<>();
        this.allEntities = new HashSet<>();
        // Please note, while adding new entity to "entityPropertiesMapper", a new set should be initialize!!!!
        this.entitiesPropertiesToTypeMapper = new HashMap<>();
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
    public boolean validatePrimaryEntityPropertyUniqueness(String entityName, String entityPropertyName) {
        return !entitiesPropertiesToTypeMapper.get(entityName).containsKey(entityPropertyName);
    }

    @Override
    public boolean validateActionEntityContext(String entityName) {
        return allEntities.contains(entityName);
    }

    @Override
    public boolean validateActionEntityPropertyContext(String entityName, String entityPropertyName) {
        return entitiesPropertiesToTypeMapper.containsKey(entityPropertyName);
    }

    @Override
    public void addEnvironmentProperty(String newEnvPropName, ePropertyType type) {
        environmentPropertiesToTypeMapper.put(newEnvPropName, type);
    }

    @Override
    public void addEntityProperty(String entityName, String entityProperty, ePropertyType type) {
        if (entitiesPropertiesToTypeMapper.containsKey(entityName)) {
            entitiesPropertiesToTypeMapper.get(entityName).put(entityProperty, type);
        } else {
            throw new WorldBuilderException(
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

        for (Map.Entry<String, Map<String, ePropertyType>> entityPropertiesNameTypeMapper
                : entitiesPropertiesToTypeMapper.entrySet()) {
            if (entityPropertiesNameTypeMapper.getValue().containsKey(rawExpression)) {
                isEntityProp = true;
                break;
            }
        }

        return isEntityProp;
    }

    @Override
    public boolean validateEntityPropertyAsExpected(String entityName, String propertyName, ePropertyType expectedType) {
        return this.entitiesPropertiesToTypeMapper.get(entityName).get(propertyName) == expectedType;
    }

    /**
     *
     * @param propertyName
     * @param expectedType
     * @return
     */
    public boolean validateEnvironemntPropertyTypeAsExpected(String propertyName, ePropertyType expectedType){
        return this.environmentPropertiesToTypeMapper.get(propertyName) == expectedType;
    }

    /**
     *
     * @param propertyName
     * Impllemented Currently only of propertie of the PrimaryEntity
     * @return
     */
    @Override
    public ePropertyType getEntityPropertyType(String entityName, String propertyName) {
        return entitiesPropertiesToTypeMapper.get(entityName).get(propertyName);
    }

    @Override
    public ePropertyType getEntityPropertyTypeWithoutEntityNameMentioned(String propertyName) {

        ePropertyType propType = null;

        for (Map.Entry<String, Map<String, ePropertyType>> entityPropertiesMap
                : entitiesPropertiesToTypeMapper.entrySet()) {
            if (entityPropertiesMap.getValue().containsKey(propertyName)) {
                propType = getEntityPropertyType(entityPropertiesMap.getKey(), propertyName);
                break;
            }
        }

        if (propType == null) {
            throw new WorldBuilderException("couldn't find the type of the property, under getEntityPropertyTypeWithoutEntityNameMentioned.");
        }

        return propType;
    }

    @Override
    public ePropertyType getEnvironmentPropertyType(String propertyName) {
        return environmentPropertiesToTypeMapper.get(propertyName);
    }

}
