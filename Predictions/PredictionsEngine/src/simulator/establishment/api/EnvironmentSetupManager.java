package simulator.establishment.api;

import simulator.definition.environment.Environment;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.world.World;

public interface EnvironmentSetupManager {

    void setFixedValuePropertyDefinition(String propName, ePropertyType type, String value, World world);
    void setFixedValuePropertyDefinition(String propName, ePropertyType type, String value, Environment environment);

//    SimulatorResponse<String> setRandomValuesForUninitializedProperties(List<String> propertiesUserUpdatedList,
//                                                                        Environment environment);
}
