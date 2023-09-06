package simulator.manualSetup.manager.impl;

import dto.SetPropertySimulatorResponseDto;
import dto.enums.SetPropertyStatus;
import response.SimulatorResponse;
import simulator.definition.environment.EnvironmentDefinition;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.utils.enums.PropertyType;
import simulator.definition.property.impl.*;
import simulator.definition.property.valueGenerator.impl.fixed.FixedValueGenerator;
import simulator.definition.world.WorldDefinition;
import simulator.manualSetup.exception.SimulationManualSetupException;
import simulator.manualSetup.manager.api.ManualSimulationSetupManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManualSimulationSetupManagerImpl implements ManualSimulationSetupManager {
    private List<String> propertiesUpdatedByUser;

    public ManualSimulationSetupManagerImpl() {
        this.propertiesUpdatedByUser = new ArrayList<>();
    }

    @Override
    public void setFixedValueToEnvironmentPropertyDefinition(String propName, PropertyType type, String value, EnvironmentDefinition environmentDefinition) {
        AbstractPropertyDefinition propertyDefinition = environmentDefinition.getPropertyByName(propName);
        if(type == PropertyType.BOOLEAN){
            switch (value){
                case "True":
                case "true":
                case "false":
                case "False":
                    break;
                default:
                    throw new RuntimeException("Invalid input");
            }
            environmentDefinition.setValueGeneratorByPropertyName(propName, new FixedValueGenerator<>(Boolean.parseBoolean(value)));
        } else if (type == PropertyType.FLOAT) {
            FloatPropertyDefinition floatPropertyDefinition = (FloatPropertyDefinition)propertyDefinition;
            Optional<Range<Float>> optionalRange = floatPropertyDefinition.getRange();
            if(optionalRange.isPresent()){
                Range<Float> range = optionalRange.get();
                Float valueInFloat = Float.parseFloat(value);
                if(valueInFloat > range.getTo() || valueInFloat < range.getFrom()){
                    throw new RuntimeException("Invalid input");
                }
            }
            environmentDefinition.setValueGeneratorByPropertyName(propName, new FixedValueGenerator<>(Float.parseFloat(value)));
        } else if (type == PropertyType.DECIMAL) {
            DecimalPropertyDefinition decimalPropertyDefinition = (DecimalPropertyDefinition)propertyDefinition;
            Optional<Range<Integer>> optionalRange = decimalPropertyDefinition.getRange();

            if(optionalRange.isPresent()){
                Range<Integer> range = optionalRange.get();
                Integer valueInFloat = Integer.parseInt(value);
                if(valueInFloat > range.getTo() || valueInFloat < range.getFrom()){
                    throw new RuntimeException("Invalid input");
                }
            }
            environmentDefinition.setValueGeneratorByPropertyName(propName, new FixedValueGenerator<>(Integer.parseInt(value)));
        }
        else{
            environmentDefinition.setValueGeneratorByPropertyName(propName, new FixedValueGenerator<>(value));
        }
    }


    @Override
    public SimulatorResponse setSelectedEnvironmentPropertiesValue(WorldDefinition worldDefinition, String propName, String type, String value) {
        SetPropertySimulatorResponseDto responseDto;
        SimulatorResponse response;
        try {
            PropertyType eType = PropertyType.STRING;
            switch (type.toLowerCase())
            {
                case "string":
                case "str":
                    eType = PropertyType.STRING;
                    break;
                case "float":
                    eType = PropertyType.FLOAT;
                    break;
                case "decimal":
                case "int":
                case "integer":
                    eType = PropertyType.DECIMAL;
                    break;
                case "boolean":
                case "bool":
                    eType = PropertyType.BOOLEAN;
                    break;
            }

            setFixedValueToEnvironmentPropertyDefinition(propName, eType, value, worldDefinition.getEnvironment());

            responseDto = new SetPropertySimulatorResponseDto(
                    SetPropertyStatus.SUCCEEDED,
                    "Environment Variable Value has been set with " + value.toString());

            response =  new SimulatorResponse(
                    true,
                    "Environment Variable Value has been set with " + value.toString(),
                    responseDto);
            this.propertiesUpdatedByUser.add(propName);

        } catch (Exception e) {

            responseDto = new SetPropertySimulatorResponseDto(SetPropertyStatus.FAILED, e.getMessage());
            response =  new SimulatorResponse(false, e.getMessage(), responseDto);
        }
        return response;
    }

    @Override
    public SimulatorResponse setEntityDefinitionPopulation(WorldDefinition worldDefinition, String entityName, Integer population) {

        try
        {
            final int[] totalEntitiesPopulation = {0};
            worldDefinition.getEntities().forEach(
                    (entName, entDef) ->
                            totalEntitiesPopulation[0] += entDef.getPopulation()
            );

            int totalWorldSpace = worldDefinition.getSpaceGridDefinition().getTotalSpace();
            if (totalEntitiesPopulation[0] + population > totalWorldSpace) {
                throw new SimulationManualSetupException(
                        "The population size is invalid, " +
                                "there's no enough space in the world space grid to create this number of instances");
            }

            worldDefinition.getEntityDefinitionByName(entityName).setPopulation(population);

            return new SimulatorResponse<>(
                    true, "population set successfully to entity: " + entityName);

        } catch(Exception e) {
            return new SimulatorResponse<>(false, e.getMessage());
        }
    }
}
