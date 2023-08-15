package simulator.manager.impl;

import dto.EnvironmentPropertiesDto;
import dto.SetPropertySimulatorResponseDto;
import dto.SimulationDetailsDto;
import dto.builder.params.BasePropertyDto;
import dto.builder.params.enums.eSetPropertyStatus;
import response.SimulatorResponse;
import simulator.builder.world.api.WorldBuilder;
import simulator.builder.world.utils.enums.eBuilderDataSrcType;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.builder.world.utils.factory.SimulationBuilderFactory;
import simulator.definition.property.api.AbstractNumericPropertyDefinition;
import simulator.definition.property.api.AbstractPropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.impl.Range;
import simulator.definition.world.World;
import dto.BuildSimulatorDto;
import simulator.manager.api.SimulatorManager;
import simulator.manager.utils.SimulatorUtils;

import java.io.File;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SimulatorManagerImpl implements SimulatorManager {
    private World world;
    private WorldBuilder worldBuilder;
    private List<String> propertiesUpdatedByUser;
    private EnvironmentManagerImpl environmentManager;

    public SimulatorManagerImpl() {
        this.propertiesUpdatedByUser = new LinkedList<>();
    }

    private void loadedSimulation(){
        // Load instances
    };

    @Override
    public SimulatorResponse buildSimulationWorld(String filePath) {

        BuildSimulatorDto buildSimulatorResult;

        try {
            File simulationConfigFile = SimulatorUtils.getFileByPath(filePath);
            eBuilderDataSrcType dataSrcType = SimulatorUtils.getDataSrcTypeByFileExtention(filePath);
            worldBuilder = SimulationBuilderFactory.createSimulationBuilder(dataSrcType, simulationConfigFile);
            world = worldBuilder.buildWorld();
            return new SimulatorResponse(true, "the following file has loaded successfully" + filePath);
        } catch(Exception ex) {
            return new SimulatorResponse(false, ex.getMessage());
        }
    }


    @Override
    public Object getSimulationWorldDetails() {
        return null;
    }

    @Override
    public SimulationDetailsDto runSimulator() {
        return null;
    }

    @Override
    public Object exitSimulator() {
        return null;
    }

    @Override
    public void endSetEnvironmentSession() {
        if(this.environmentManager != null) this.environmentManager.setRandomValuesForUninitializedProperties(
                this.propertiesUpdatedByUser, this.world.getEnvironment());
    }

    @Override
    public SimulatorResponse startEnvironmentSession() {
        // might be used in the future
        SimulatorResponse response = new SimulatorResponse<>(true, "session started successfully",
                null);
        return response;
    }

    @Override
    public SimulatorResponse endEnvironmentSession() {
        try {
            this.environmentManager.setRandomValuesForUninitializedProperties(this.propertiesUpdatedByUser,
                    this.world.getEnvironment());
            SimulatorResponse response = new SimulatorResponse<>(true, "session ended successfully",
                    null);
            return response;
        }catch (Exception e){
            SimulatorResponse response = new SimulatorResponse<>(false, "end session action failed",
                    null);
            return response;
        }
    }

    @Override
    public SimulatorResponse endLoadingSimulationSessionSignal() {
        SimulatorResponse response = new SimulatorResponse<>(true, "end loading simulation successfully",
                null);
        return response;
    }

    @Override
    public SimulatorResponse startLoadingSimulationSessionSignal() {
        SimulatorResponse response = new SimulatorResponse<>(true, "start loading simulation successfully",
                null);
        return response;
    }

    @Override
    public EnvironmentPropertiesDto getEnvironmentProperties() {
        List<BasePropertyDto> propertyDtoList = new LinkedList<>();
        for (String propertyName:this.world.getEnvironment().getPropertiesNames()
             ) {
            AbstractPropertyDefinition property = this.world.getEnvironment().getPropertyByName(propertyName);
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
        EnvironmentPropertiesDto response = new EnvironmentPropertiesDto(propertyDtoList);

        return response;
    }

    @Override
    public SimulatorResponse setEnvironmentVariableValue(String propName, String type, String value) {
        SetPropertySimulatorResponseDto responseDto;
        SimulatorResponse response;
        try {
            ePropertyType eType = ePropertyType.STRING;
            switch (type.toLowerCase())
            {
                case "string":
                case "str":
                    eType = ePropertyType.STRING;
                    break;
                case "float":
                case "decimal":
                case "double":
                    eType = ePropertyType.DECIMAL;
                    break;
                case "boolean":
                case "bool":
                    eType = ePropertyType.BOOLEAN;
                    break;
                case "integer":
                case "int":
                    eType = ePropertyType.INTEGER;
                    break;
            }
            environmentManager = new EnvironmentManagerImpl();
            environmentManager.addPropertyInstance(propName, eType, value, this.world.getEnvironment());

            responseDto = new SetPropertySimulatorResponseDto(eSetPropertyStatus.SUCCEEDED,
                    "Environment Variable Value has been set with " + value);
            response =  new SimulatorResponse(true,
                    "Environment Variable Value has been set with " + value,
                                responseDto);
            this.propertiesUpdatedByUser.add(propName);

        } catch (Exception e) {
            responseDto = new SetPropertySimulatorResponseDto(eSetPropertyStatus.FAILED, e.getMessage());
            response =  new SimulatorResponse(false, e.getMessage(), responseDto);
        }
        return response;
    }

    @Override
    public Object activateEnvironment() {
        // instances = manager.instance.createInstances();
        // env = manager.activateEnvironment(dto );
        // manager.initializeRunner(instances, env);
        // manager.run();
        return null;
    }


}
