package simulator.builder.validator.api;

import simulator.builder.utils.file.enums.DataFileType;
import simulator.definition.property.utils.enums.PropertyType;

public interface WorldBuilderContextValidator {
    boolean validateFileExist(String filePath);
    boolean validateFileType(DataFileType actualDataFileType, DataFileType expectedDataFileType);
    boolean validateEnvironmentPropertyUniqueness(String envPropertyName);
    boolean validatePrimaryEntityPropertyUniqueness(String entityName, String entityPropertyName);
    boolean validateActionEntityContext(String entityName);
    boolean validateActionEntityPropertyContext(String entityName, String entityPropertyName);
    void addEnvironmentProperty(String newEnvPropName, PropertyType type);
    void addEntityProperty(String entityName, String entityProperty, PropertyType type);
    void addEntity(String entityName);
    boolean validateEnvironemntPropertyTypeAsExpected(String propertyName, PropertyType expectedType);
    PropertyType getEntityPropertyType(String entityName, String propertyName);
    PropertyType getEntityPropertyTypeWithoutEntityNameMentioned(String propertyName);
    PropertyType getEnvironmentPropertyType(String properyName);
    boolean isEnvironmentProperty(String propertySuspect);
    boolean validateSpaceGridDimensions(int rows, int cols);
    boolean validateActionContextProcedure(String entityName, String entityPropertyName);
    boolean isEntityPropertyNameExists(String rawExpression);
    boolean validateEntityPropertyAsExpected(String entityName ,String propertyName, PropertyType expectedType);
}
