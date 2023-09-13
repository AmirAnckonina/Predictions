package simulator.builder.manager.impl;

import dto.BasePropertyDto;
import dto.EnvironmentPropertiesDto;
import dto.EnvironmentPropertyDto;
import dto.SimulationDetailsDto;
import response.SimulatorResponse;
import simulator.builder.api.interfaces.WorldBuilder;
import simulator.builder.manager.api.WorldBuilderManager;
import simulator.builder.utils.factory.WorldBuilderFactory;
import simulator.builder.utils.file.WorldBuilderFileUtils;
import simulator.builder.utils.file.enums.DataFileType;
import simulator.definition.entity.EntityDefinition;
import simulator.definition.property.api.abstracts.AbstractNumericPropertyDefinition;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.impl.Range;
import simulator.definition.rule.Rule;
import simulator.definition.world.WorldDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldBuilderManagerImpl implements WorldBuilderManager {
    private WorldDefinition worldDefinition;
    private WorldBuilder worldBuilder;

    @Override
    public WorldDefinition getWorldDefinition() {
        return worldDefinition;
    }

    @Override
    public List<EnvironmentPropertyDto> getAllEnvironmentProperties() {

        List<EnvironmentPropertyDto> envPropDtoList = new ArrayList<>();

       this.worldDefinition
               .getEnvironment()
               .getEnvironmentProperties()
               .forEach( (envPropName, envPropDef) -> {
                   envPropDtoList.add(new EnvironmentPropertyDto(envPropName, envPropDef.getType()));
               });

       return envPropDtoList;
    }

    @Override
    public void buildSimulationWorld(String filePath) {
            DataFileType dataSrcType = WorldBuilderFileUtils.getDataFileTypeByFileExtension(filePath);
            worldBuilder = WorldBuilderFactory.createSimulationBuilder(dataSrcType, filePath);
            worldDefinition = worldBuilder.buildWorld();
    }

    @Override
    public SimulationDetailsDto getSimulationWorldDetails() {

            StringBuilder entitiesSb = new StringBuilder();
            for (Map.Entry<String, EntityDefinition> entityDef : this.worldDefinition.getEntities().entrySet()) {
                entitiesSb.append(entityDef.getValue().toString()).append(System.lineSeparator());
            }
            String entitiesInfo = entitiesSb.toString();

            StringBuilder rulesSb = new StringBuilder();
            for (Rule rule : worldDefinition.getRules()) {
                rulesSb.append(rule.toString()).append(System.lineSeparator());
            }
            String rulesInfo = rulesSb.toString();

            String terminationInfo = worldDefinition.getTermination().toString();

            return new SimulationDetailsDto(entitiesInfo, rulesInfo, terminationInfo);
    }

    @Override
    public EnvironmentPropertiesDto getEnvironmentPropertiesDefinition() {

            Map<String  ,BasePropertyDto> propertyDtoMap = new HashMap();
            List<BasePropertyDto> propertyDtoList = new ArrayList<>();
            for (String propertyName : this.worldDefinition.getEnvironment().getPropertiesNames()) {

                AbstractPropertyDefinition property = this.worldDefinition.getEnvironment().getPropertyByName(propertyName);
                if (property instanceof AbstractNumericPropertyDefinition) {
                    Range range = (Range) ((AbstractNumericPropertyDefinition) property).getRange().orElse(null);
                    if (range != null) {
                        BasePropertyDto propertyDto = new BasePropertyDto(propertyName,
                                property.getType().toString(), range.getFrom().toString(),
                                range.getTo().toString(), null);
                        propertyDtoMap.put(propertyName ,propertyDto);
                        propertyDtoList.add(propertyDto);
                        continue;
                    }
                }
                BasePropertyDto propertyDto = new BasePropertyDto(propertyName,
                        property.getType().toString(), null, null, null);
                propertyDtoMap.put(propertyName, propertyDto);
                propertyDtoList.add(propertyDto);
            }
            EnvironmentPropertiesDto envPropsDto = new EnvironmentPropertiesDto(propertyDtoList, propertyDtoMap);

            return envPropsDto;

    }
}
