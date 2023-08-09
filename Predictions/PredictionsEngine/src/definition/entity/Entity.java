package definition.entity;


import definition.property.api.BasePropertyDefinition;
import definition.property.api.PropertyDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entity {

    private String name = new String();
    private Integer numOfInstances = new Integer(0);
    private Map<String,PropertyDefinition> properties = new HashMap<>();

    public String getName() {
        return name;
    }

    public Integer getNumOfInstances() {
        return numOfInstances;
    }

    public PropertyDefinition getProperties(String propertieName) {
        return properties.get(propertieName);
    }

}
