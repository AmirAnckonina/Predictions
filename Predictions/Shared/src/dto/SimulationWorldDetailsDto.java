package dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationWorldDetailsDto {
    private EnvironmentPropertiesDto environmentPropertiesDto;
    private String entitiesInfo;
    private List<String> rulesActions;
    private String terminationInfo;
    private Map<String, StringRuleDto> ruleMap;
    private Map<String, StringEntityDto> entityDtoMap;

    public SimulationWorldDetailsDto(EnvironmentPropertiesDto environmentPropertiesDto, String entitiesInfo, List<String> rulesActions, String terminationInfo) {
        this.environmentPropertiesDto = environmentPropertiesDto;
        this.entitiesInfo = entitiesInfo;
        this.rulesActions = rulesActions;
        this.terminationInfo = terminationInfo;

        setEntitiesMapFromString(entitiesInfo);
        setRuleMapFromRuleInfo(rulesActions);
    }

    public void setEnvironmentPropertiesDto(EnvironmentPropertiesDto environmentPropertiesDto) {
        this.environmentPropertiesDto = environmentPropertiesDto;
    }

    public EnvironmentPropertiesDto getEnvironmentPropertiesDto() {
        return environmentPropertiesDto;
    }

    private void setRuleMapFromRuleInfo(List<String> rulesActions) {
        this.ruleMap = new HashMap<>();
        for(String rule:rulesActions){
            StringRuleDto stringRuleDto = new StringRuleDto(rule);
            ruleMap.put(stringRuleDto.getName(), stringRuleDto);
        }
    }

    private void setEntitiesMapFromString(String entitiesInfo) {
        this.entityDtoMap = new HashMap<>();
        String[] entitiesArray = entitiesInfo.split("entity name:");
        for(String entity:entitiesArray){
            if(entity.length() < 2){
                continue;
            }
            StringEntityDto entityDto = new StringEntityDto(entity);
            this.entityDtoMap.put(entityDto.getEntityName(), entityDto);
        }
    }

    public String getEntitiesInfo() {
        return entitiesInfo;
    }

    public List<String> getRulesActions() {
        return rulesActions;
    }

    public String getTerminationInfo() {
        return terminationInfo;
    }

    public String getInfo() {
        return entitiesInfo + rulesActions + terminationInfo;
    }

    public Map<String, StringEntityDto> getEntityDtoMap() {
        return entityDtoMap;
    }

    public Map<String, StringRuleDto> getRuleMap() {
        return ruleMap;
    }
}
