package simulator.builder.utils;

import simulator.definition.property.utils.enums.ePropertyType;

public class ArgExpressionTypeDemands {
    private ePropertyType propertyType;
    private eMandatoryTypeDemanding mandatoryDemand;

    public ArgExpressionTypeDemands(ePropertyType propertyType) {
        this(propertyType, eMandatoryTypeDemanding.NotMentioned);
    }

    public ArgExpressionTypeDemands(eMandatoryTypeDemanding mandatoryDemand) {
        this(null, mandatoryDemand);
    }

    public ArgExpressionTypeDemands(ePropertyType propertyType, eMandatoryTypeDemanding mandatoryDemand) {
        this.propertyType = propertyType;
        this.mandatoryDemand = mandatoryDemand;
        if (mandatoryDemand == eMandatoryTypeDemanding.NUMERIC) {
            this.propertyType = ePropertyType.FLOAT;
        }
    }

    public ePropertyType getPropertyType() {
        return this.propertyType;
    }
}
