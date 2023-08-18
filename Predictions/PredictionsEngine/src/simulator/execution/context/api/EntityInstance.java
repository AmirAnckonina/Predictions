package simulator.execution.context.api;

import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;

public interface EntityInstance {
    PropertyInstance getPropertyByName(String propertyName);
    void addProperty(String propertyName, PropertyInstance propertyInstance);
}
