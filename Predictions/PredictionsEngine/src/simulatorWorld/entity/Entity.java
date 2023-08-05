package simulatorWorld.entity;


import simulatorWorld.property.BaseProperty;
import simulatorWorld.property.BasePropertyList;

public class Entity {

    private BasePropertyList properties = new BasePropertyList();

    /**
     *
     * @param propertyName The attribute name
     * @param propertyValue The value of the attribute
     * @return true if inserting the new property was succeeded. False if a property named propertyName already exist.
     */
    public  Boolean setNewProperty(String propertyName, BaseProperty propertyValue ){
        return this.properties.setNewProperty(propertyName, propertyValue);
    }

    /**
     *
     * @param propertyName - the name of the attribute
     * @return if the entity contains a property object named propertyName, it will return it or null otherwise.
     */
    public Object getPropertyValue(String propertyName){
        return this.properties.getProperty(propertyName).getValue();
    }

}
