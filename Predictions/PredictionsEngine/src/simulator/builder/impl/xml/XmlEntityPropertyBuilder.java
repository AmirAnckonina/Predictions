package simulator.builder.impl.xml;

import resources.jaxb.schema.generated.PRDProperty;
import simulator.builder.impl.baseImpl.BasePropertyBuilder;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import structure.range.Range;
import enums.PropertyType;

public class XmlEntityPropertyBuilder extends BasePropertyBuilder {
    private PRDProperty generatedEntityProperty;

    public XmlEntityPropertyBuilder(PRDProperty generatedEntityProperty) {
        this.generatedEntityProperty = generatedEntityProperty;
    }

    public AbstractPropertyDefinition buildEntityProperty() {
        String propName = generatedEntityProperty.getPRDName();
        PropertyType propType = PropertyType.valueOf(generatedEntityProperty.getType().toUpperCase());
        Range range = null;
        String rawInitValue = null;
        if (generatedEntityProperty.getPRDRange() != null) {
            double from = generatedEntityProperty.getPRDRange().getFrom();
            double to = generatedEntityProperty.getPRDRange().getTo();
            range = new Range(from, to);
        }

        if (!generatedEntityProperty.getPRDValue().isRandomInitialize()) {
            rawInitValue = generatedEntityProperty.getPRDValue().getInit();
        }

        return buildProperty(propName, propType, range, rawInitValue);
//        return buildPropertyDefinitionByXmlGeneratedData(
//                generatedEntityProperty.getPRDName(),
//                generatedEntityProperty.getType(),
//                generatedEntityProperty.getPRDRange());
    }
}
