package dto;

import java.util.Map;

public class EntityPopulationOvertimeDto {

    private Map<Integer, Map<String, Integer>> entityPopulationOvertimeMap;

    public EntityPopulationOvertimeDto(Map<Integer, Map<String, Integer>> entityPopulationOvertimeMap) {
        this.entityPopulationOvertimeMap = entityPopulationOvertimeMap;
    }

    public Map<Integer, Map<String, Integer>> getEntityPopulationOvertimeMap() {
        return entityPopulationOvertimeMap;
    }
}
