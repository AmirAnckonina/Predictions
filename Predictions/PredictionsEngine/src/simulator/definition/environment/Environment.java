package simulator.definition.environment;


import simulator.definition.property.api.AbstractPropertyDefinition;
import javafx.beans.property.ListPropertyBase;
import simulator.definition.exception.AmbiguousException;
import simulator.definition.property.api.BasePropertyDefinition;

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

    public void addProp(BasePropertyDefinition prop) throws AmbiguousException {
        for(BasePropertyDefinition property : this.environmentProperties){
            if (property.getName() == prop.getName())
                throw new AmbiguousException("Datamember already exist");
        }
        this.environmentProperties.add(prop);

    }
}
