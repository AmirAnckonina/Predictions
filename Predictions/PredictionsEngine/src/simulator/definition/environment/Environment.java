package simulator.definition.environment;


import simulator.definition.property.api.AbstractPropertyDefinition;
import javafx.beans.property.ListPropertyBase;
import simulator.definition.exception.AmbiguousException;
import simulator.definition.property.api.PropertyDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment {
    String name;

    private Map<String, AbstractPropertyDefinition> environmentProperties;

    public Environment(Map<String, AbstractPropertyDefinition> environmentProperties) {
        this.environmentProperties = environmentProperties;
    }

    public void addProp(String propName, AbstractPropertyDefinition propertyDefinition) {
        environmentProperties.put(propName, propertyDefinition);
    }

}
