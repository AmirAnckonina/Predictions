package simulator.definition.environment;


import simulator.definition.exception.MapValueException;
import simulator.definition.property.api.AbstractPropertyDefinition;
import javafx.beans.property.ListPropertyBase;
import simulator.definition.exception.AmbiguousException;
import simulator.definition.property.api.PropertyDefinition;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

import java.util.HashMap;
import java.util.LinkedList;
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
            if (entry.getValue().getName().equals(property.getName()))
                throw new AmbiguousException("Datamember already exist");
            this.environmentProperties.put(property.getName(), property);
        }
    }

    public AbstractPropertyDefinition getPropertyByName(String propertyName){
        if(this.environmentProperties.containsKey(propertyName))
            return this.environmentProperties.get(propertyName);
        else return null;
    }

    public void setPropertyValueByName(String propertyName, ValueGenerator valueGenerator){
        if(this.environmentProperties.containsKey(propertyName)){
            this.environmentProperties.get(propertyName).setValueGenerator(valueGenerator);
        }
        else throw new MapValueException("Property does not exist");
    }

    public List<String> getPropertiesNames(){
        List<String> propertiesNames = new LinkedList<>();
        for (Map.Entry<String, AbstractPropertyDefinition> entry:
                this.environmentProperties.entrySet()) {
            propertiesNames.add(entry.getKey());
        }

        return propertiesNames;
    }
}
