package simulator.information.simulationDocument.api;

import dto.SimulationDocumentInfoDto;
import enums.SimulationStatus;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.information.tickDocument.api.TickDocument;
import simulator.result.api.SimulationResult;

import java.util.List;
import java.util.Map;

public interface SimulationDocument {
     void createInitialSimulationDocumentInfoDto();
     SimulationDocumentInfoDto getInitialSimulationDocumentInfoDto();
     String getSimulationGuid();
     WorldInstance getWorldInstance();
     SimulationStatus getSimulationStatus();
     Map<Integer, TickDocument> getTickDocumentMap();
     TickDocument getTickDocumentByTickNumber(int tickNo);
     SimulationResult getSimulationResult();
     void addTickDocument(TickDocument tickDocument);
     void setSimulationStatus(SimulationStatus simulationStatus);
     TickDocument getLatestTickDocument();

    void finishSimulationSession(Long simulationStartingTime);

}
