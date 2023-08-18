package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDEnvProperty;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;

public class XmlEnvironmentPropertyBuilder extends XmlPropertyBuilder {
    private PRDEnvProperty generatedEnvironmentProperty;

    public XmlEnvironmentPropertyBuilder(PRDEnvProperty generatedEnvironmentProperty) {
        this.generatedEnvironmentProperty = generatedEnvironmentProperty;
    }

    public AbstractPropertyDefinition buildEnvironmentProperty() {
        return buildPropertyDefinitionByXmlGeneratedData(
                generatedEnvironmentProperty.getPRDName(),
                generatedEnvironmentProperty.getType(),
                generatedEnvironmentProperty.getPRDRange());
    }
}
