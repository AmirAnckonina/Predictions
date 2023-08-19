package dto;

import java.util.List;

public class SimulationDetailsDto {
    private List<String> entitiesInfo;
    private List<String> rulesInfo;
    private String terminationInfo;

    public SimulationDetailsDto(List<String> entitiesInfo, List<String> rulesInfo, String terminationInfo) {
        this.entitiesInfo = entitiesInfo;
        this.rulesInfo = rulesInfo;
        this.terminationInfo = terminationInfo;
    }
}
