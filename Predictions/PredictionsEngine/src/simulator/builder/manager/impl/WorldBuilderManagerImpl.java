package simulator.builder.manager.impl;

import dto.BasePropertyDto;
import dto.EnvironmentPropertiesDto;
import dto.EnvironmentPropertyDto;
import dto.SimulationWorldDetailsDto;
import simulator.builder.api.interfaces.WorldBuilder;
import simulator.builder.manager.api.WorldBuilderManager;
import simulator.builder.utils.exception.WorldBuilderManagerException;
import simulator.builder.utils.factory.WorldBuilderFactory;
import simulator.builder.utils.file.WorldBuilderFileUtils;
import simulator.builder.utils.file.enums.DataFileType;
import simulator.definition.entity.EntityDefinition;
import simulator.definition.property.api.abstracts.AbstractNumericPropertyDefinition;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import structure.range.Range;
import simulator.definition.rule.Rule;
import simulator.definition.world.WorldDefinition;

import java.util.*;
import java.util.stream.Collectors;

public class WorldBuilderManagerImpl implements WorldBuilderManager {
    private Map<String, WorldDefinition> worldDefinitionMap;
    private WorldBuilder worldBuilder;

    public WorldBuilderManagerImpl() {
        this.worldDefinitionMap = new HashMap<>();
    }

    @Override
    public WorldDefinition getWorldDefinition(String simulationWorldName) {
        return worldDefinitionMap.get(simulationWorldName);
    }

    @Override
    public List<EnvironmentPropertyDto> getAllEnvironmentProperties(String simulationWorldName) {

        List<EnvironmentPropertyDto> envPropDtoList = new ArrayList<>();

       this.worldDefinitionMap
               .get(simulationWorldName)
               .getEnvironment()
               .getEnvironmentProperties()
               .forEach((envPropName, envPropDef) ->
                       envPropDtoList.add(arrangeSingleEnvironmentPropertyDto(envPropName, envPropDef)));

       return envPropDtoList;

    }

    @Override
    public Integer getMaxPopulationSize(String simulationWorldName) {
        return this.worldDefinitionMap.get(simulationWorldName).getSpaceGridDefinition().getTotalSpace();
    }

    @Override
    public List<String> getAllLoadedSimulationWorldNames() {
        return new ArrayList<>(this.worldDefinitionMap.keySet());
    }

    private EnvironmentPropertyDto arrangeSingleEnvironmentPropertyDto(String envPropName, AbstractPropertyDefinition envPropDef) {

        EnvironmentPropertyDto envPropDto;
        if (envPropDef instanceof AbstractNumericPropertyDefinition
                &&  ((AbstractNumericPropertyDefinition) envPropDef).getRange().isPresent()) {

           Range range = (Range) (((AbstractNumericPropertyDefinition)envPropDef).getRange()).get();
           envPropDto = new EnvironmentPropertyDto(
                    envPropName, envPropDef.getType(), new Range<>(range.getFrom(), range.getTo()));

        } else {
            envPropDto = new EnvironmentPropertyDto(envPropName, envPropDef.getType());
        }

        return envPropDto;
    }

    @Override
    public void buildSimulationWorld(String filePath) {
            DataFileType dataSrcType = WorldBuilderFileUtils.getDataFileTypeByFileExtension(filePath);
            this.worldBuilder = WorldBuilderFactory.createSimulationBuilder(dataSrcType, filePath);
            String newSimulationWorldName = this.worldBuilder.getSimulationWorldName();
            if (this.worldDefinitionMap.containsKey(newSimulationWorldName)) {
                throw new WorldBuilderManagerException("The simulation world name already exists.");
            }

            WorldDefinition newWorldDefinition = worldBuilder.buildWorld();
    }

    @Override
    public SimulationWorldDetailsDto getSimulationWorldDetailsByName(String simulationWorldName) {

            EnvironmentPropertiesDto environmentPropertiesDto = getEnvironmentPropertiesDefinition(simulationWorldName);

            StringBuilder entitiesSb = new StringBuilder();
            for (Map.Entry<String, EntityDefinition> entityDef : this.worldDefinitionMap.get(simulationWorldName).getEntities().entrySet()) {
                entitiesSb.append(entityDef.getValue().toString()).append(System.lineSeparator());
            }
            String entitiesInfo = entitiesSb.toString();

            List<String> rulesInfo = new LinkedList<>();
            for (Rule rule : worldDefinitionMap.get(simulationWorldName).getRules()) {
                rulesInfo.add(rule.toString() + System.lineSeparator());
            }

            String terminationInfo = worldDefinitionMap.get(simulationWorldName).getTermination().toString();

            return new SimulationWorldDetailsDto(environmentPropertiesDto,entitiesInfo, rulesInfo, terminationInfo);
    }

    @Override
    public EnvironmentPropertiesDto getEnvironmentPropertiesDefinition(String simulationWorldName) {

            Map<String  ,BasePropertyDto> propertyDtoMap = new HashMap();
            List<BasePropertyDto> propertyDtoList = new ArrayList<>();
            for (String propertyName : this.worldDefinitionMap.get(simulationWorldName).getEnvironment().getPropertiesNames()) {

                AbstractPropertyDefinition property = this.worldDefinitionMap.get(simulationWorldName).getEnvironment().getPropertyByName(propertyName);
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
