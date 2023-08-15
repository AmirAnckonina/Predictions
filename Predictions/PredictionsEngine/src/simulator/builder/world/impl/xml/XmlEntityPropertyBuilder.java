package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDProperty;
import simulator.definition.property.api.AbstractPropertyDefinition;

public class XmlEntityPropertyBuilder extends XmlPropertyBuilder {
    private PRDProperty generatedEntityProperty;

    public XmlEntityPropertyBuilder(PRDProperty generatedEntityProperty) {
        this.generatedEntityProperty = generatedEntityProperty;
    }

    public AbstractPropertyDefinition buildEntityProperty() {
        return buildPropertyDefinitionByXmlGeneratedData(
                generatedEntityProperty.getPRDName(),
                generatedEntityProperty.getType(),
                generatedEntityProperty.getPRDRange());
    }
}
