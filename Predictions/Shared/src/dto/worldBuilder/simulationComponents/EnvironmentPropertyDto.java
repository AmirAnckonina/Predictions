package dto.worldBuilder.simulationComponents;

import enums.PropertyType;
import structure.range.Range;

import java.util.Optional;

public class EnvironmentPropertyDto {

    private String envPropName;
    private PropertyType propertyType;
    private Range range;

    public EnvironmentPropertyDto(String envPropName, PropertyType propertyType) {
        this(envPropName, propertyType, null);
    }

    public EnvironmentPropertyDto(String envPropName, PropertyType propertyType, Range range) {
        this.envPropName = envPropName;
        this.propertyType = propertyType;
        this.range = range;
    }

    public String getEnvPropName() {return envPropName; }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public Optional<Range> getRange() { return Optional.ofNullable(range); }
}
