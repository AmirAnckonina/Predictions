package simulator.information.simulationDocument.impl;

import simulator.execution.instance.world.api.WorldInstance;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.information.tickDocument.api.TickDocument;
import simulator.result.api.SimulationResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationDocumentImpl implements SimulationDocument {
    private String SimulationGuid;
    private WorldInstance worldInstance;
    private Map<Integer, TickDocument> tickDocumentMap;
    private SimulationResult simulationResult;

    public SimulationDocumentImpl(String simulationGuid, WorldInstance worldInstance) {
        this.SimulationGuid = simulationGuid;
        this.worldInstance = worldInstance;
        this.tickDocumentMap = new HashMap<>();
    }


    @Override
    public String getSimulationGuid() {
        return this.SimulationGuid;
    }

    @Override
    public WorldInstance getWorldInstance() {
        return this.worldInstance;
    }

    @Override
    public Map<Integer, TickDocument> getTickDocumentMap() {
        return this.tickDocumentMap;
    }

    @Override
    public TickDocument getTickDocumentByTickNumber(int tickNo) {
        return this.tickDocumentMap.get(tickNo);
    }

    @Override
    public SimulationResult getSimulationResult() {
        return this.simulationResult;
    }
}
