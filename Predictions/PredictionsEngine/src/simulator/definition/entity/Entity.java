package simulator.definition.entity;


import simulator.definition.property.api.AbstractPropertyDefinition;

import java.util.Map;

public class Entity {
    private String name;
    private Map<String, AbstractPropertyDefinition> properties;

    public String getName() {
        return name;
    }

    public Entity(String name, Map<String, AbstractPropertyDefinition> properties) {
        this.name = name;
        this.properties = properties;
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

}
