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

    public void addProp(AbstractPropertyDefinition property) throws AmbiguousException {
        for (Map.Entry<String, AbstractPropertyDefinition> entry:
                this.environmentProperties.entrySet()) {
            if (entry.getValue().getName() == property.getName())
                throw new AmbiguousException("Datamember already exist");
            this.environmentProperties.put(property.getName(), property);
        }
    }
}
