package dto;

import java.util.Map;

public class EntityPopulationOvertimeDto {

    private Map<String, Map<Integer, Integer>> entityPopulationOvertimeMap;
    // Map<EntityName, Map<TickNo, PopulationInTick>>

    public EntityPopulationOvertimeDto(Map<String, Map<Integer, Integer>> entityPopulationOvertimeMap) {
        this.entityPopulationOvertimeMap = entityPopulationOvertimeMap;
    }

    public Map<String, Map<Integer, Integer>> getEntityPopulationOvertimeMap() {
        return entityPopulationOvertimeMap;
    }
}
