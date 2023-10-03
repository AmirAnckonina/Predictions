package dto.simulationInfo;

import java.util.Map;

public class PropertiesAvgConsistencyDto {
    private Map<String, Map<String,Double>> propertiesConsistencyMap;
    public PropertiesAvgConsistencyDto(Map<String, Map<String, Double>> propertiesConsistencyMap) {
        this.propertiesConsistencyMap = propertiesConsistencyMap;
    }

    public Map<String, Map<String, Double>> getPropertiesConsistencyMap() {
        return propertiesConsistencyMap;
    }
}
