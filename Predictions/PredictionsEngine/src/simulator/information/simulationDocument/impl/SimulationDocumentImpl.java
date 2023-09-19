package simulator.information.simulationDocument.impl;

import enums.SimulationStatus;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.information.manager.exception.SimulationInformationException;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.information.tickDocument.api.TickDocument;
import simulator.result.api.SimulationResult;

import java.util.*;
import java.util.stream.Collectors;

public class SimulationDocumentImpl implements SimulationDocument {
    private String SimulationGuid;
    private WorldInstance worldInstance;
    private Map<Integer, TickDocument> tickDocumentMap;
    private SimulationResult simulationResult;
    private SimulationStatus simulationStatus;

    public SimulationDocumentImpl(String simulationGuid, WorldInstance worldInstance) {
        this.SimulationGuid = simulationGuid;
        this.worldInstance = worldInstance;
        this.simulationStatus = SimulationStatus.READY;
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
    public SimulationStatus getSimulationStatus() {
        return this.simulationStatus;
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

    @Override
    public void addTickDocument(TickDocument tickDocument) {
        this.tickDocumentMap.put(tickDocument.getTickNumber(), tickDocument);
    }

    @Override
    public void setSimulationStatus(SimulationStatus simulationStatus) {
        this.simulationStatus = simulationStatus;
    }

    @Override
    public TickDocument getLatestTickDocument() {
        Optional<TickDocument> latestTickDoc
                = this.tickDocumentMap
                .values()
                .stream()
                .max(Comparator.comparing(TickDocument::getTickNumber));

        if (!latestTickDoc.isPresent()) {
            throw new SimulationInformationException("Couldn't get the max latestTickDoc");
        }

        return latestTickDoc.get();
    }
}
