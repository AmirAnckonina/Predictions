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
import simulator.definition.rule.Rule;
import simulator.definition.world.World;
import dto.BuildSimulatorDto;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.runner.api.SimulatorRunner;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.execution.runner.impl.SimulatorRunnerImpl;
import simulator.execution.instance.entity.impl.EntityInstanceImpl;
import simulator.execution.instance.environment.impl.EnvironmentInstanceImpl;
import simulator.execution.instance.property.impl.PropertyInstanceImpl;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.execution.instance.world.impl.WorldInstanceImpl;
import simulator.manager.api.EnvironmentManager;
import simulator.manager.api.SimulatorManager;
import simulator.builder.world.utils.file.WorldBuilderFileUtils;

import java.util.*;

import static simulator.manager.utils.SimulatorUtils.getGUID;

public class SimulatorManagerImpl implements SimulatorManager {
    private World world;
    private WorldInstance worldInstance;
    private WorldBuilder worldBuilder;
    private List<String> propertiesUpdatedByUser;
    private EnvironmentManager environmentManager;
    private String simulationID;
    private SimulatorRunner simulatorRunner;

    //SimulatorResultManager simulatorResultManager;

    public SimulatorManagerImpl() {
        //this.simulatorResultManager = new  SimulatorResultManagerImpl();
        this.propertiesUpdatedByUser = new ArrayList<>();
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
    public SimulatorResponse<SimulationDetailsDto> getSimulationWorldDetails() {
        try {
            String entitiesInfo = world.getPrimaryEntity().toString();
            StringBuilder rulesSb = new StringBuilder();
            for (Rule rule : world.getRules()) {
                rulesSb.append(rule.toString()).append(System.lineSeparator());
            }

            String rulesInfo = rulesSb.toString();
            String terminationInfo = world.getTermination().toString();

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
    public SimulatorResponse runSimulator() {
        this.worldInstance = createSimulation();
        this.worldInstance.setRules(this.world.getRules());
        this.worldInstance.setTermination(this.world.getTermination());
        this.simulatorRunner = new SimulatorRunnerImpl(new WorldInstanceImpl());
        this.simulatorRunner.run();
        this.simulationID = getGUID();

        //simulatorResultManager.addNewExecutedSimulation(Guid, result...);
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
        return new SimulatorResponse<>(true, "session ended successfully", null);
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
                    eType = ePropertyType.FLOAT;
                    break;
                case "decimal":
                case "int":
                case "integer":
                    eType = ePropertyType.DECIMAL;
                    break;
                case "boolean":
                case "bool":
                    eType = ePropertyType.BOOLEAN;
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
        worldInstance.setPrimaryEntityInstances(createPrimaryEntityInstances());

        return worldInstance;
    }

    @Override
    public EnvironmentInstance activateEnvironment() {

        Map<String, PropertyInstance> environmentVariables =
                createPropertyInstances(world.getEnvironment().getEnvironmentProperties());

        return new EnvironmentInstanceImpl(environmentVariables);
    }

    public List<EntityInstance> createPrimaryEntityInstances() {

        // Result :
        List<EntityInstance> primaryEntityInstances = new ArrayList<>();

        for (int index = 0; index < world.getPrimaryEntity().getPopulation(); index++) {

            EntityInstance singlePrimaryInstance;

            Map<String, PropertyInstance> entityPropertyInstances =
                    createPropertyInstances(world.getPrimaryEntity().getProperties());

            singlePrimaryInstance = new EntityInstanceImpl(index + 1, entityPropertyInstances);

            // Finally after we build singleEntityInstance with it properties, we add it to the list result.
            primaryEntityInstances.add(singlePrimaryInstance);
        }

        return primaryEntityInstances;
    }

    public Map<String, PropertyInstance> createPropertyInstances(
            Map<String, AbstractPropertyDefinition> propertyDefinitions) {

        Map<String, PropertyInstance> propertyInstanceMap = new HashMap<>();

        for (Map.Entry<String, AbstractPropertyDefinition> entry :
                propertyDefinitions.entrySet()) {

            /// Note - we should validate key (bane) == value.getName(); in each propery Entry <K,V>

            String propName = entry.getKey();
            AbstractPropertyDefinition propDefinition = entry.getValue();

            PropertyInstance propertyInstance =
                    new PropertyInstanceImpl(propDefinition, propDefinition.generateValue());

            propertyInstanceMap.put(propName, propertyInstance);
        }

        return  propertyInstanceMap;
    }


}
