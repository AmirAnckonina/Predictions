package dto;

import dto.builder.params.BasePropertyDto;

import java.util.List;

public class EnvironmentPropertiesDto {
    List<BasePropertyDto> properties;

    public int getNumOfProperties(){
        return this.properties.size();
    }

    public List<BasePropertyDto> getPropertiesList(){
        return this.properties;
    }

}
