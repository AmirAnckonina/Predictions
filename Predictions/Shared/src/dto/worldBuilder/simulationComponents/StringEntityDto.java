package dto.worldBuilder.simulationComponents;

import java.util.HashMap;
import java.util.Map;

public class StringEntityDto {
    private String entityName;
    private Map<String, StringPropertyDto> propertyDtoMap;


    public StringEntityDto(String entity) {
        setEntityDetailsFromString(entity);
    }

    private void setEntityDetailsFromString(String entity) {
        propertyDtoMap = new HashMap<>();
        String[] entityDetailLines = entity.split("\r\n");
        String[] propertiesLines = entity.substring(entity.indexOf("\n") + 1, entity.length()).split("\r\n\r\n");

        this.entityName = entityDetailLines[0].replaceAll(" ", "");
        for(String property:propertiesLines){
            if(property.indexOf("property name") != -1){
                StringPropertyDto propertyDto = new StringPropertyDto(property);
                propertyDtoMap.put(propertyDto.getPropertyName(), propertyDto);
            }
        }
    }

    public String getEntityName() {
        return entityName;
    }

    public Map<String, StringPropertyDto> getPropertyDtoMap() {
        return propertyDtoMap;
    }
}