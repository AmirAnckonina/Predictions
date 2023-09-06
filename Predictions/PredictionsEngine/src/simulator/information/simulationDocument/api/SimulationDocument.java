package simulator.information.simulationDocument.api;

import simulator.execution.instance.world.api.WorldInstance;
import simulator.information.tickDocument.api.TickDocument;
import simulator.result.api.SimulationResult;

import java.util.List;
import java.util.Map;

public interface SimulationDocument {
     String getSimulationGuid();
     WorldInstance getWorldInstance();
     Map<Integer, TickDocument> getTickDocumentMap();
     TickDocument getTickDocumentByTickNumber(int tickNo);
     SimulationResult getSimulationResult();
}
