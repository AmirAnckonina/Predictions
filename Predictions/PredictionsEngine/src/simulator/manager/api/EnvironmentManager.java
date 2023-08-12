package simulator.manager.api;

import simulator.definition.property.api.BasePropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.world.World;

public interface EnvironmentManager {

    void addPropertyInstance(String propName, ePropertyType type, String value, World world);
}
