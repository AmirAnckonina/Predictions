package simulator.builder.world.api.interfaces;

import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.property.impl.Range;

public interface PropertyBuilder {
    AbstractPropertyDefinition buildProperty(String name, ePropertyType propertyType, Range range);
}
