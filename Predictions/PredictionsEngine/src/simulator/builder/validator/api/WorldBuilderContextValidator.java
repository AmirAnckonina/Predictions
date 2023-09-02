package simulator.builder.validator.api;

import simulator.builder.utils.file.enums.eDataFileType;
import simulator.definition.property.utils.enums.ePropertyType;

public interface WorldBuilderContextValidator {
    boolean validateFileExist(String filePath);
    boolean validateFileType(eDataFileType actualDataFileType, eDataFileType expectedDataFileType);
    boolean validateEnvironmentPropertyUniqueness(String envPropertyName);
    boolean validatePrimaryEntityPropertyUniqueness(String entityName, String entityPropertyName);
    boolean validateActionEntityContext(String entityName);
    boolean validateActionEntityPropertyContext(String entityName, String entityPropertyName);
    void addEnvironmentProperty(String newEnvPropName, ePropertyType type);
    void addEntityProperty(String entityName, String entityProperty, ePropertyType type);
    void addEntity(String entityName);
    boolean validateEnvironemntPropertyTypeAsExpected(String propertyName, ePropertyType expectedType);
    ePropertyType getPropertyType(String entityName, String propertyName);
    boolean isEnvironmentProperty(String propertySuspect);
    boolean validateSpaceGridDimensions(int rows, int cols);
    boolean validateActionContextProcedure(String entityName, String entityPropertyName);
    boolean isEntityPropertyNameExists(String rawExpression);
    boolean validateEntityPropertyAsExpected(String entityName ,String propertyName, ePropertyType expectedType);
}
