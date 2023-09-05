package simulator.builder.utils;

import simulator.definition.property.utils.enums.PropertyType;

public class ArgExpressionTypeDemands {
    private PropertyType propertyType;
    private MandatoryTypeDemanding mandatoryDemand;

    public ArgExpressionTypeDemands(PropertyType propertyType) {
        this(propertyType, MandatoryTypeDemanding.Mentioned);
    }

    public ArgExpressionTypeDemands() {
        this(null, MandatoryTypeDemanding.NotMentioned);
    }

    public ArgExpressionTypeDemands(PropertyType propertyType, MandatoryTypeDemanding mandatoryDemand) {
        this.propertyType = propertyType;
        this.mandatoryDemand = mandatoryDemand;
    }

    public PropertyType getPropertyType() {
        return this.propertyType;
    }

    public MandatoryTypeDemanding getMandatoryDemand() {
        return mandatoryDemand;
    }
}
