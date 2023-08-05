package simulatorWorld.property;

import java.util.HashMap;

public class BasePropertyList {

    private HashMap<String,BaseProperty> properties = new HashMap<String, BaseProperty>();

    /**
     *
     * @param propertyName - the new property name
     * @param property - property instance
     * @return true if inserting the new property was succeeded. False if a property named propertyName already exist.
     */
    public boolean setNewProperty(String propertyName, BaseProperty property){
        if(this.properties.containsKey(propertyName))
        {
            return false;
        }
        else
        {
            properties.put(propertyName, property);
        }
        return true;
    }


    /**
     *
     * @param propertyName - the name of the attribute
     * @return if the property list contains a property object named propertyName, it will return it or null otherwise.
     */
    public BaseProperty getProperty(String propertyName){
        if(this.properties.containsKey(propertyName)){
            return properties.get(propertyName);
        }
        else return null;
    }
}
