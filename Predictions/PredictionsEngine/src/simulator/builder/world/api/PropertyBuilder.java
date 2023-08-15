package simulator.builder.world.api;

import simulator.definition.property.api.AbstractPropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.impl.Range;

public interface PropertyBuilder {
    AbstractPropertyDefinition buildEntityProperty();
    AbstractPropertyDefinition buildEnvironmentProperty();
    AbstractPropertyDefinition buildProperty(String name, ePropertyType propertyType, Range range);
}
