package dto.simulationInfo;

import java.util.Map;

public class PropertiesConsistencyDto {
    private Map<String, Map<String,Double>> propertiesConsistencyMap;
    public PropertiesConsistencyDto(Map<String, Map<String, Double>> propertiesConsistencyMap) {
        this.propertiesConsistencyMap = propertiesConsistencyMap;
    }

    public Map<String, Map<String, Double>> getPropertiesConsistencyMap() {
        return propertiesConsistencyMap;
    }
}
