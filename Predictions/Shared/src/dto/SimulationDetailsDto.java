package dto;

import java.util.List;

public class SimulationDetailsDto {
    private String entitiesInfo;
    private String rulesInfo;
    private String terminationInfo;

    public SimulationDetailsDto(String entitiesInfo, String rulesInfo, String terminationInfo) {
        this.entitiesInfo = entitiesInfo;
        this.rulesInfo = rulesInfo;
        this.terminationInfo = terminationInfo;
    }

    public String getEntitiesInfo() {
        return entitiesInfo;
    }

    public String getRulesInfo() {
        return rulesInfo;
    }

    public String getTerminationInfo() {
        return terminationInfo;
    }

    public String getInfo() {
        return entitiesInfo + rulesInfo + terminationInfo;
    }
}
