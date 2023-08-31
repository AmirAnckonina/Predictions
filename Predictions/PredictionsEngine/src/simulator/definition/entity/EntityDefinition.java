package simulator.definition.entity;


import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;

import java.util.Map;

public class EntityDefinition {
    private final String name;
    private final Map<String, AbstractPropertyDefinition> properties;

    public EntityDefinition(String name, Map<String, AbstractPropertyDefinition> properties) {
        this.name = name;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public Map<String, AbstractPropertyDefinition> getProperties() {

        return properties;
    }

    /**
     *
     * @param propertyName The attribute name
     * @param propertyValue The value of the attribute
     * @return true if inserting the new property was succeeded. False if a property named propertyName already exist.
     */
    public  Boolean setNewProperty(String propertyName, AbstractPropertyDefinition propertyValue ){
        boolean res = false;
        if(!this.properties.containsKey(propertyName)) {
            this.properties.put(propertyName, propertyValue);
            res = true;
        }
        else res = false;

        return res;
    }

    /**
     *
     * @param propertyName - the name of the attribute
     * @return if the entity contains a property object named propertyName, it will return it or null otherwise.
     */
    public Object getPropertyValue(String propertyName){

        return this.properties.get(propertyName);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("entity name: ").append(name).append(System.lineSeparator());
        for (AbstractPropertyDefinition property : properties.values()) {
            sb.append(property.toString()).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
