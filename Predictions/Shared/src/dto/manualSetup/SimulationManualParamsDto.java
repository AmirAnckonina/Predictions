package dto.manualSetup;

import java.util.Map;

public class SimulationManualParamsDto {
    Map<String, Integer> entityDefinitionPopulationMap;
    Map<String, ?> environmentPropertyMap;

    public SimulationManualParamsDto(Map<String, Integer> entityDefinitionPopulationMap, Map<String, ?> environmentPropertyMap) {
        this.entityDefinitionPopulationMap = entityDefinitionPopulationMap;
        this.environmentPropertyMap = environmentPropertyMap;
    }

    public Map<String, Integer> getEntityDefinitionPopulationMap() {
        return entityDefinitionPopulationMap;
    }

    public Map<String, ?> getEnvironmentPropertyMap() {
        return environmentPropertyMap;
    }
}
