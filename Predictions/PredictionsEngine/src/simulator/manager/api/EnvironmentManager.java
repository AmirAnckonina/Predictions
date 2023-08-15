package simulator.manager.api;

import response.SimulatorResponse;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.world.World;

import java.util.List;

public interface EnvironmentManager {

    void addPropertyInstance(String propName, ePropertyType type, String value, World world);

    SimulatorResponse<String> setRandomValuesForUninitializedProperties(List<Integer> propertiesUserUpdatedList,
                                                                        World world);
}
