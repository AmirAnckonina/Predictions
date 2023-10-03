package dto.simulationInfo;

import java.util.Map;

public class SimulationResultMappedProperties {

    private Map<String, Integer> mappedPropertiesToNumOfEntitiesByValues;
    private String guid;

    public SimulationResultMappedProperties(Map<String, Integer> mappedPropertiesToNumOfEntitiesByValues, String guid) {
        this.mappedPropertiesToNumOfEntitiesByValues = mappedPropertiesToNumOfEntitiesByValues;
        this.guid = guid;
    }

    public Map<String, Integer> getMappedPropertiesToNumOfEntitiesByValues() {
        return mappedPropertiesToNumOfEntitiesByValues;
    }

    public String getGuid() {
        return guid;
    }
}
