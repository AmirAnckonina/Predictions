package simulator.result.newManager.impl;

import simulator.definition.world.WorldDefinition;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.mainManager.utils.SimulatorUtils;
import simulator.result.api.SimulationResult;
import simulator.result.impl.SimulationInitialInfo;
import simulator.result.impl.SimulationResultImpl;
import simulator.result.manager.api.ResultManager;
import simulator.result.manager.impl.ResultManagerImpl;
import simulator.result.newManager.api.InformationManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InformationManagerImpl implements InformationManager {
    private ResultManager simulatorResultManager;

    public InformationManagerImpl() {
        this.simulatorResultManager = new ResultManagerImpl();
    }

    @Override
    public SimulationResult setNewSimulationInfo(WorldDefinition worldDefinition, WorldInstance worldInstance) {
        String guid = SimulatorUtils.getGUID();
        // build dto - temporary:
        Map<String, Integer> entitiesPopulation = new HashMap<>();
        worldDefinition.getEntities()
                .forEach(
                        (entName, entDef) ->
                                entitiesPopulation.put(entName, entDef.getPopulation())
                );

        Map<String, Set<String>> entitiesPropertiesNames = new HashMap<>();
        worldDefinition.getEntities()
                .forEach(
                        (entName, entDef) ->
                                entitiesPropertiesNames.put(entName, entDef.getProperties().keySet())
                );

        SimulationInitialInfo simulationInitialInfo =
                new SimulationInitialInfo(
                        guid,
                        entitiesPopulation,
                        entitiesPropertiesNames,
                        worldInstance
                );

        SimulationResult simulationResult = new SimulationResultImpl(simulationInitialInfo);
        simulatorResultManager.addSimulationResult(
                simulationResult.getSimulationUuid(), simulationResult
        );

        return simulationResult;
    }
}
