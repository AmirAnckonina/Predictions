package dto;

import enums.PropertyType;

public class EnvironmentPropertyDto {

    private String envPropName;
    private PropertyType propertyType;

    public EnvironmentPropertyDto(String envPropName, PropertyType propertyType) {
        this.envPropName = envPropName;
        this.propertyType = propertyType;
    }

    public String getEnvPropName() {
        return envPropName;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }
}
