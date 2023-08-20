package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDRange;
import resources.jaxb.schema.generated.PRDValue;
import simulator.builder.world.impl.baseImpl.BasePropertyBuilder;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.property.impl.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class XmlPropertyBuilder extends BasePropertyBuilder {

    public AbstractPropertyDefinition buildPropertyDefinitionByXmlGeneratedData(
            String prdName, String prdType, PRDRange prdRange, PRDValue prdValue) {
        String propName = prdName;
        ePropertyType propType = ePropertyType.valueOf(prdType.toUpperCase());
        Range range = null;
        if (prdRange != null) {
            double from = prdRange.getFrom();
            double to = prdRange.getTo();
            range = new Range(from, to);
        }
        throw new NotImplementedException();
        //return buildProperty(propName, propType, range, initValue);
    }
}
