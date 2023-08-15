package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDEnvProperty;
import resources.jaxb.schema.generated.PRDProperty;
import resources.jaxb.schema.generated.PRDRange;
import simulator.builder.world.impl.BasePropertyBuilder;
import simulator.definition.property.api.AbstractPropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.impl.*;

public class XmlPropertyBuilder extends BasePropertyBuilder {

    public AbstractPropertyDefinition buildPropertyDefinitionByXmlGeneratedData(String prdName, String prdType, PRDRange prdRange) {
        String propName = prdName;
        ePropertyType propType = ePropertyType.valueOf(prdType);
        Range range = null;
        if (prdRange != null) {
            double from = prdRange.getFrom();
            double to = prdRange.getTo();
            range = new Range(from, to);
        }

        return buildProperty(propName, propType, range);
    }
}
