package dto;

import java.util.List;

public class EnvironmentPropertiesDto {
    List<BasePropertyDto> properties;

    public EnvironmentPropertiesDto(List<BasePropertyDto> properties){ this.properties = properties;}

    public int getNumOfProperties(){
        return this.properties.size();
    }

    public List<BasePropertyDto> getPropertiesList(){
        return this.properties;
    }

}
