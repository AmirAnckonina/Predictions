package simulator.builder.utils;

import simulator.definition.property.utils.enums.PropertyType;

import java.util.Optional;

public class ArgExpressionContextDemands {
    private String entityName;
    private PropertyType propertyType;
    private MandatoryTypeDemanding mandatoryDemand;

    // Used by: Multiply, Divide, Set(Not sure why), conditionComparedValue
    // May need an entityContext
    public ArgExpressionContextDemands(String entityName, PropertyType propertyType) {
        this(entityName, propertyType, MandatoryTypeDemanding.Mentioned);
    }

    // Used by: Increase, Decrease, Proximity, PercentMethod
    // Don't need to mention entityContext, just type!
    public ArgExpressionContextDemands(PropertyType propertyType) {
        this(null, propertyType, MandatoryTypeDemanding.Mentioned);
    }

    // Simple case while building condition expression - first arg (condition property)
    // Need an entityContext, but not a type demanding.
    public ArgExpressionContextDemands(String entityName) {

        this(entityName, null, MandatoryTypeDemanding.NotMentioned);
    }

    // gather all the inner c'tors to a single point.
    public ArgExpressionContextDemands(String entityName, PropertyType propertyType, MandatoryTypeDemanding mandatoryDemand) {
        this.entityName = entityName;
        this.propertyType = propertyType;
        this.mandatoryDemand = mandatoryDemand;
    }

    public PropertyType getPropertyType() {
        return this.propertyType;
    }

    public MandatoryTypeDemanding getMandatoryDemand() {
        return mandatoryDemand;
    }

    public Optional<String> getEntityName() {
        return Optional.ofNullable(entityName);
    }
}
