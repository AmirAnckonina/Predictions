package dto;

import java.util.Map;

public class EntityPopulationOvertimeDto {

    private Map<String, Map<Integer, Integer>> entityPopulationOvertimeMap;

    public EntityPopulationOvertimeDto(Map<String, Map<Integer, Integer>> entityPopulationOvertimeMap) {
        this.entityPopulationOvertimeMap = entityPopulationOvertimeMap;
    }

    public Map<String, Map<Integer, Integer>> getEntityPopulationOvertimeMap() {
        return entityPopulationOvertimeMap;
    }
}
