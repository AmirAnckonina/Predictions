package simulator.builder.impl.xml;

import resources.jaxb.schema.generated.PRDRange;
import resources.jaxb.schema.generated.PRDValue;
import simulator.builder.impl.baseImpl.BasePropertyBuilder;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import enums.PropertyType;
import structure.range.Range;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class XmlPropertyBuilder extends BasePropertyBuilder {

    public AbstractPropertyDefinition buildPropertyDefinitionByXmlGeneratedData(
            String prdName, String prdType, PRDRange prdRange, PRDValue prdValue) {
        String propName = prdName;
        PropertyType propType = PropertyType.valueOf(prdType.toUpperCase());
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
