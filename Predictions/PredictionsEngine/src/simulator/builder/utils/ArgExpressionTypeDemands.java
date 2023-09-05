package simulator.builder.utils;

import simulator.definition.property.utils.enums.ePropertyType;

public class ArgExpressionTypeDemands {
    private ePropertyType propertyType;
    private eMandatoryTypeDemanding mandatoryDemand;

    public ArgExpressionTypeDemands(ePropertyType propertyType) {
        this(propertyType, eMandatoryTypeDemanding.Mentioned);
    }

    public ArgExpressionTypeDemands() {
        this(null, eMandatoryTypeDemanding.NotMentioned);
    }

    public ArgExpressionTypeDemands(ePropertyType propertyType, eMandatoryTypeDemanding mandatoryDemand) {
        this.propertyType = propertyType;
        this.mandatoryDemand = mandatoryDemand;
    }

    public ePropertyType getPropertyType() {
        return this.propertyType;
    }

    public eMandatoryTypeDemanding getMandatoryDemand() {
        return mandatoryDemand;
    }
}
