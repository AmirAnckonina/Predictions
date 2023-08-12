package simulator.builder.world.impl.xml;

import dto.builder.params.PropertyBuilderParamsDto;
import resources.jaxb.schema.generated.PRDEnvProperty;
import resources.jaxb.schema.generated.PRDProperty;
import simulator.builder.world.api.AbstractFileComponentBuilder;
import simulator.builder.world.api.PropertyBuilder;
import simulator.definition.property.api.BasePropertyDefinition;

public class XmlPropertyBuilder extends AbstractFileComponentBuilder<BasePropertyDefinition> implements PropertyBuilder {
    private PRDProperty entityProperty;
    private PRDEnvProperty environmentProperty;

    public XmlPropertyBuilder() {
    }

    public XmlPropertyBuilder(PRDProperty entityProperty) {
        this.entityProperty = entityProperty;
    }

    public XmlPropertyBuilder(PRDEnvProperty environmentProperty) {
        this.environmentProperty = environmentProperty;
    }

    @Override
    public <T> BasePropertyDefinition<T> buildEnvironmentProperty() {
        //environmentProperty
        return null;
    }

    @Override
    public <T> BasePropertyDefinition<T> buildEntityProperty() {
        //entityProperty.
        return null;
    }


}
