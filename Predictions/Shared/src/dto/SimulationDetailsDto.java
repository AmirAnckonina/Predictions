package dto;

import java.util.List;
import java.util.Map;

public class SimulationDetailsDto {
    private String entitiesInfo;
    private String rulesInfo;
    private String terminationInfo;
    private Map<String, StringRule> ruleMap;

    public SimulationDetailsDto(String entitiesInfo, String rulesInfo, String terminationInfo) {
        this.entitiesInfo = entitiesInfo;
        this.rulesInfo = rulesInfo;
        this.terminationInfo = terminationInfo;

        createMapFromString(rulesInfo);
    }

    private void createMapFromString(String rulesInfo) {
        System.out.println("g");
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
