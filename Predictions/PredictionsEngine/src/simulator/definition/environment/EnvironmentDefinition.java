package simulator.definition.environment;


import simulator.definition.utils.exception.MapValueException;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EnvironmentDefinition {
    private final Map<String, AbstractPropertyDefinition> environmentProperties;

    public EnvironmentDefinition(Map<String, AbstractPropertyDefinition> environmentProperties) {
        this.environmentProperties = environmentProperties;
    }

    public Map<String, AbstractPropertyDefinition> getEnvironmentProperties() {
        return environmentProperties;
    }

    public void addProp(String propName, AbstractPropertyDefinition propertyDefinition) {
        environmentProperties.put(propName, propertyDefinition);
    }

//    public void addProp(AbstractPropertyDefinition property) throws AmbiguousException {
//        for (Map.Entry<String, AbstractPropertyDefinition> entry:
//                this.environmentProperties.entrySet()) {
//            if (entry.getValue().getName().equals(property.getName()))
//                throw new AmbiguousException("Datamember already exist");
//            this.environmentProperties.put(property.getName(), property);
//        }
//    }

    public AbstractPropertyDefinition getPropertyByName(String propertyName){
        if(this.environmentProperties.containsKey(propertyName))
            return this.environmentProperties.get(propertyName);
        else return null;
    }

    public void setValueGeneratorByPropertyName(String propertyName, ValueGenerator valueGenerator){
        if(this.environmentProperties.containsKey(propertyName)) {
            this.environmentProperties.get(propertyName).setFixedValueGenerator(valueGenerator);
        }
        else throw new MapValueException("Property does not exist");
    }

    public List<String> getPropertiesNames() {
        List<String> propertiesNames = new LinkedList<>();
        for (Map.Entry<String, AbstractPropertyDefinition> entry:
                this.environmentProperties.entrySet()) {
            propertiesNames.add(entry.getKey());
        }

        return propertiesNames;
    }
}
