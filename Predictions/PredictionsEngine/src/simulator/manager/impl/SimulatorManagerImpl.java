package simulator.manager.impl;

import dto.EnvironmentPropertiesDto;
import dto.SetPropertySimulatorResponseDto;
import dto.SimulationDetailsDto;
import dto.builder.params.BasePropertyDto;
import dto.builder.params.enums.eSetPropertyStatus;
import response.SimulatorResponse;
import simulator.builder.world.api.interfaces.WorldBuilder;
import simulator.builder.world.utils.file.enums.eDataFileType;
import simulator.builder.world.utils.factory.WorldBuilderFactory;
import simulator.definition.property.api.abstracts.AbstractNumericPropertyDefinition;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.property.impl.Range;
import simulator.definition.world.World;
import dto.BuildSimulatorDto;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.execution.impl.SimulatorRunnerImpl;
import simulator.execution.instance.entity.impl.EntityInstanceImpl;
import simulator.execution.instance.environment.impl.EnvironmentInstanceImpl;
import simulator.execution.instance.property.impl.PropertyInstanceImpl;
import simulator.execution.instance.world.impl.WorldInstanceImpl;
import simulator.manager.api.SimulatorManager;
import simulator.builder.world.utils.file.WorldBuilderFileUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static simulator.manager.utils.SimulatorUtils.getGUID;

public class SimulatorManagerImpl implements SimulatorManager {
    private World world;
    private WorldInstanceImpl worldInstance;
    private WorldBuilder worldBuilder;
    private List<String> propertiesUpdatedByUser;
    private EnvironmentManagerImpl environmentManager;
    private String simulationID;
    private SimulatorRunnerImpl simulatorRunner;

    public SimulatorManagerImpl() {
        this.propertiesUpdatedByUser = new LinkedList<>();
    }

    private void loadedSimulation() {
        // Load instances
    };

    @Override
    public SimulatorResponse buildSimulationWorld(String filePath) {

        BuildSimulatorDto buildSimulatorResult;

        try {
            eDataFileType dataSrcType = WorldBuilderFileUtils.getDataFileTypeByFileExtension(filePath);
            worldBuilder = WorldBuilderFactory.createSimulationBuilder(dataSrcType, filePath);
            world = worldBuilder.buildWorld();
            return new SimulatorResponse(true, "the following file has loaded successfully" + filePath);

        } catch (Exception ex) {

            return new SimulatorResponse(false, ex.getMessage());
        }
    }


    @Override
    public SimulationDetailsDto getSimulationWorldDetails() {
        //entitiesInfo = world.getPrimaryEntity().toString();
        String terminationInfo;
        List<String> rulesInfo = null;
        List<String> entitiesInfo;
        //return new SimulationDetailsDto(entitiesInfo, rulesInfo, terminationInfo);
        return null;
    }

    @Override
    public SimulatorResponse runSimulator() {
        this.worldInstance = createSimulation();
        this.worldInstance.setRules(this.world.getRules());
        this.worldInstance.setTermination(this.world.getTermination());
        this.simulatorRunner = new SimulatorRunnerImpl(this.worldInstance);
        this.simulatorRunner.run();
        this.simulationID = getGUID();

        SimulatorResponse<String> response = new SimulatorResponse<>(true, "", simulationID);
        return null;
    }

    @Override
    public Object exitSimulator() {
        return null;
    }

    @Override
    public void endSetEnvironmentSession() {
//        if(this.environmentManager != null) this.environmentManager.setRandomValuesForUninitializedProperties(
//                this.propertiesUpdatedByUser, this.world.getEnvironment());
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
//        try {
//            this.environmentManager.setRandomValuesForUninitializedProperties(this.propertiesUpdatedByUser,
//                    this.world.getEnvironment());
//            SimulatorResponse response = new SimulatorResponse<>(true, "session ended successfully",
//                    null);
//            return response;
//        }catch (Exception e){
//            SimulatorResponse response = new SimulatorResponse<>(false, "end session action failed",
//                    null);
//            return response;
//        }
        return null;
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
            environmentManager.setFixedValuePropertyDefinition(propName, eType, value, this.world.getEnvironment());

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

    public WorldInstanceImpl createSimulation(){
        WorldInstanceImpl worldInstance = new WorldInstanceImpl();

        worldInstance.setEnvironmentInstance(activateEnvironment());
        worldInstance.setPrimaryEntities(creatPrimaryInstance());

        return worldInstance;
    }

    @Override
    public EnvironmentInstanceImpl activateEnvironment() {
        EnvironmentInstanceImpl environmentInstance = new EnvironmentInstanceImpl();
        for (String propertyName:world.getEnvironment().getPropertiesNames() ){
            PropertyInstance propertyInstance = new PropertyInstanceImpl(propertyName,
                    world.getEnvironment().getPropertyByName(propertyName).generateValue(),
                    world.getEnvironment().getPropertyByName(propertyName).getType());
            environmentInstance.addPropertyInstance(propertyInstance);
        }
        // instances = manager.instance.createInstances();
        // env = manager.activateEnvironment(dto );
        // manager.initializeRunner(instances, env);
        // manager.run();
        return environmentInstance;
    }

    public List<EntityInstance> creatPrimaryInstance(){
        List<EntityInstance> singlePrimaryInstances = new LinkedList<>();
        //EntityInstances primaryInstance;
        for(int index = 0; index<this.world.getPrimaryEntity().getPopulation(); index++){

            EntityInstanceImpl singlePrimaryInstance = new EntityInstanceImpl();
            for (Map.Entry<String, AbstractPropertyDefinition> entry : this.world.getPrimaryEntity().getProperties().entrySet()){
                String propertyName = entry.getKey();
                AbstractPropertyDefinition propertyDefinition = entry.getValue();
                PropertyInstance propertyInstance = new PropertyInstanceImpl(propertyDefinition.getName(),
                        propertyDefinition.generateValue(),
                        propertyDefinition.getType());
                singlePrimaryInstance.addProperty(propertyName, propertyInstance);
            }

            singlePrimaryInstances.add(singlePrimaryInstance);
        }

        return singlePrimaryInstances;
    }


}
