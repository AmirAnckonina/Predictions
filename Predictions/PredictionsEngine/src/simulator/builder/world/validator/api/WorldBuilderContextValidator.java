package simulator.builder.world.validator.api;

import simulator.builder.world.utils.enums.eDataFileType;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.rule.action.utils.eActionType;

public interface WorldBuilderContextValidator {
    boolean validateFileExist(String filePath);
    boolean validateFileType(eDataFileType actualDataFileType, eDataFileType expectedDataFileType);
    boolean validateEnvironmentPropertyUniqueness(String envPropertyName);
    boolean validatePrimaryEntityPropertyUniqueness(String entityPropertyName);
    boolean validateActionEntityContext(String entityName);
    boolean validateActionEntityPropertyContext(String entityName, String entityPropertyName);
    boolean validateActionArguments(eActionType actionType, String... actionArgs);
    void addEnvironemntProperty(String newEnvPropName, ePropertyType type);
    void addEntityProperty(String entityName, String entityProperty);
    void addPrimaryEntity(String entity);
    void addSecondaryEntity(String entity);
    boolean isProperty(String propertySuspect);

    boolean isAppropriateType(String propertyName, ePropertyType type);

}
