package simulator.builder.api.interfaces;

import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.utils.enums.PropertyType;
import simulator.definition.property.impl.Range;

public interface PropertyBuilder {
    AbstractPropertyDefinition buildProperty(String name, PropertyType propertyType, Range doubledRange, String rawInitValue);
    AbstractPropertyDefinition buildBooleanProperty(String name, PropertyType propertyType, Range doubledRange, String rawInitValue);
    AbstractPropertyDefinition buildStringProperty(String name, PropertyType propertyType, Range doubledRange, String rawInitValue);
    AbstractPropertyDefinition buildDecimalProperty(String name, PropertyType propertyType, Range doubledRange, String rawInitValue);
    AbstractPropertyDefinition buildFloatProperty(String name, PropertyType propertyType, Range doubledRange, String rawInitValue);

}
