package simulator.builder.manager.impl;

import dto.BasePropertyDto;
import dto.EnvironmentPropertiesDto;
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
    public SimulatorResponse buildSimulationWorld(String filePath) {
        try {
            DataFileType dataSrcType = WorldBuilderFileUtils.getDataFileTypeByFileExtension(filePath);
            worldBuilder = WorldBuilderFactory.createSimulationBuilder(dataSrcType, filePath);
            worldDefinition = worldBuilder.buildWorld();
            return new SimulatorResponse(true, "the following file has loaded successfully: " + filePath);

        } catch (Exception ex) {
            return new SimulatorResponse(false, ex.getMessage());
        }
    }

    @Override
    public SimulatorResponse<SimulationDetailsDto> getSimulationWorldDetails() {
        try {

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

            return new SimulatorResponse<>(
                    true,
                    new SimulationDetailsDto(entitiesInfo, rulesInfo, terminationInfo)
            );

        } catch (Exception e) {

            return new SimulatorResponse<>(
                    false,
                    "An error occured while trying to fetch the the loaded simulation world details."
            );
        }

    }

    @Override
    public SimulatorResponse<EnvironmentPropertiesDto> getEnvironmentPropertiesDefinition() {
        try {

            List<BasePropertyDto> propertyDtoList = new ArrayList<>();

            for (String propertyName : this.worldDefinition.getEnvironment().getPropertiesNames()) {

                AbstractPropertyDefinition property = this.worldDefinition.getEnvironment().getPropertyByName(propertyName);
                if (property instanceof AbstractNumericPropertyDefinition) {
                    Range range = (Range) ((AbstractNumericPropertyDefinition) property).getRange().orElse(null);
                    if (range != null) {
                        BasePropertyDto propertyDto = new BasePropertyDto(propertyName,
                                property.getType().toString(), range.getFrom().toString(),
                                range.getTo().toString(), null);
                        propertyDtoList.add(propertyDto);
                        continue;
                    }
                }
                BasePropertyDto propertyDto = new BasePropertyDto(propertyName,
                        property.getType().toString(), null, null, null);
                propertyDtoList.add(propertyDto);
            }
            EnvironmentPropertiesDto envPropsDto = new EnvironmentPropertiesDto(propertyDtoList);

            return new SimulatorResponse<>(true, envPropsDto);

        } catch (Exception e) {
            return new SimulatorResponse<>(
                    false, "an error detcetd while trying to set env properties.");
        }
    }
}
