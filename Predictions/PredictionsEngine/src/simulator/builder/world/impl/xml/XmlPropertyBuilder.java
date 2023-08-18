package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDRange;
import simulator.builder.world.impl.baseImpl.BasePropertyBuilder;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.property.impl.*;

public class XmlPropertyBuilder extends BasePropertyBuilder {

    public AbstractPropertyDefinition buildPropertyDefinitionByXmlGeneratedData(String prdName, String prdType, PRDRange prdRange) {
        String propName = prdName;
        ePropertyType propType = ePropertyType.valueOf(prdType.toUpperCase());
        Range range = null;
        if (prdRange != null) {
            double from = prdRange.getFrom();
            double to = prdRange.getTo();
            range = new Range(from, to);
        }

        return buildProperty(propName, propType, range);
    }
}
