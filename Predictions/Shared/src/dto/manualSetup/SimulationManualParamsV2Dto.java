package dto.manualSetup;

import java.util.Map;

public class SimulationManualParamsV2Dto {
    Map<String, Integer> entityDefinitionPopulationMap;
    Map<String, String> environmentPropertyMap;

    public SimulationManualParamsV2Dto(Map<String, Integer> entityDefinitionPopulationMap, Map<String, String> environmentPropertyMap) {
        this.entityDefinitionPopulationMap = entityDefinitionPopulationMap;
        this.environmentPropertyMap = environmentPropertyMap;
    }

    public Map<String, Integer> getEntityDefinitionPopulationMap() {
        return entityDefinitionPopulationMap;
    }

    public Map<String, String> getEnvironmentPropertyMap() {
        return environmentPropertyMap;
    }
}
