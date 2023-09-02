package simulator.builder.utils;

import simulator.definition.property.utils.enums.ePropertyType;

public class ArgExpressionTypeDemands {
    private ePropertyType propertyType;
    private eNumericDemanding numericDemanding;

    public ArgExpressionTypeDemands(ePropertyType propertyType) {
        this(propertyType, eNumericDemanding.NotMentioned);
    }

    public ArgExpressionTypeDemands(eNumericDemanding numericDemanding) {
        this(null, numericDemanding);
    }

    public ArgExpressionTypeDemands(ePropertyType propertyType, eNumericDemanding numericDemanding) {
        this.propertyType = propertyType;
        this.numericDemanding = numericDemanding;
        if (numericDemanding == eNumericDemanding.MANDATORY) { this.propertyType = ePropertyType.FLOAT; }
    }

    public ePropertyType getPropertyType() {
        return this.propertyType;
    }
}
