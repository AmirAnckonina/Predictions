package simulator.builder.world.validator.api;

import simulator.builder.world.utils.file.enums.eDataFileType;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.utils.enums.eActionType;

public interface WorldBuilderContextValidator {
    boolean validateFileExist(String filePath);
    boolean validateFileType(eDataFileType actualDataFileType, eDataFileType expectedDataFileType);
    boolean validateEnvironmentPropertyUniqueness(String envPropertyName);
    boolean validatePrimaryEntityPropertyUniqueness(String entityPropertyName);
    boolean validateActionEntityContext(String entityName);
    boolean validateActionEntityPropertyContext(String entityName, String entityPropertyName);
    boolean validateActionArguments(eActionType actionType, String... actionArgs);
    void addEnvironmentProperty(String newEnvPropName, ePropertyType type);
    void addEntityProperty(String entityName, String entityProperty, ePropertyType type);
    void addPrimaryEntity(String entity);
    void addSecondaryEntity(String entity);
    boolean validateEnvironemntPropertyTypeAsExpected(String propertyName, ePropertyType type);
    ePropertyType getPropertyType(String propertyName);
    boolean isEnvironmentProperty(String propertySuspect);



}
