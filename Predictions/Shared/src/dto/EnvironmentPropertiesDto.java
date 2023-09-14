package dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnvironmentPropertiesDto {
    List<BasePropertyDto> propertiesList;
    Map<String ,BasePropertyDto> propertiesMap;

    public EnvironmentPropertiesDto(List<BasePropertyDto> propertiesList, Map<String ,BasePropertyDto> propertiesMap){
        this.propertiesList = propertiesList;
        this.propertiesMap = propertiesMap;

        if(propertiesMap == null && propertiesList != null){
            this.propertiesMap = new HashMap<>();
            for (BasePropertyDto propertyDto:propertiesList){
                this.propertiesMap.put(propertyDto.getName(), propertyDto);
            }
        }
    }

    public int getNumOfProperties(){
        return this.propertiesList.size();
    }

    public List<BasePropertyDto> getPropertiesList(){
        return this.propertiesList;
    }
    public Map<String, BasePropertyDto> getPropertiesMap() {
        return propertiesMap;
    }

}
