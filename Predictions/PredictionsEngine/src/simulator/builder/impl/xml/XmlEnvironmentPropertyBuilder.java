package simulator.builder.impl.xml;

import resources.jaxb.schema.generated.PRDEnvProperty;
import simulator.builder.impl.baseImpl.BasePropertyBuilder;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.impl.Range;
import simulator.definition.property.utils.enums.PropertyType;

public class XmlEnvironmentPropertyBuilder extends BasePropertyBuilder {
    private PRDEnvProperty generatedEnvironmentProperty;

    public XmlEnvironmentPropertyBuilder(PRDEnvProperty generatedEnvironmentProperty) {
        this.generatedEnvironmentProperty = generatedEnvironmentProperty;
    }

    public AbstractPropertyDefinition buildEnvironmentProperty() {
        String propName = generatedEnvironmentProperty.getPRDName();
        PropertyType propType = PropertyType.valueOf( generatedEnvironmentProperty.getType().toUpperCase());
        Range range = null;
        String rawInitValue = null;
        if ( generatedEnvironmentProperty.getPRDRange() != null) {
            double from =  generatedEnvironmentProperty.getPRDRange().getFrom();
            double to =  generatedEnvironmentProperty.getPRDRange().getTo();
            range = new Range(from, to);
        }

        return buildProperty(propName, propType, range, rawInitValue);
//        return buildPropertyDefinitionByXmlGeneratedData(
//                generatedEnvironmentProperty.getPRDName(),
//                generatedEnvironmentProperty.getType(),
//                generatedEnvironmentProperty.getPRDRange());
    }
}
