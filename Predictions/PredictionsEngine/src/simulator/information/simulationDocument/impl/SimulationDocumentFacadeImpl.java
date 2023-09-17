package simulator.information.simulationDocument.impl;

import dto.SimulationEndDto;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.information.simulationDocument.api.SimulationDocumentFacade;

public class SimulationDocumentFacadeImpl implements SimulationDocumentFacade {
    private SimulationDocument simulationDocument;
    private SimulationEndDto simulationEndDto;
    public SimulationDocumentFacadeImpl(SimulationDocument simulationDocument) {
        this.simulationDocument = simulationDocument;
    }
}
