package simulator.information.manager.impl;

import dto.SimulationEndDto;
import simulator.definition.world.WorldDefinition;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.information.manager.exception.SimulationInformationException;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.information.simulationDocument.api.SimulationDocumentFacade;
import simulator.information.simulationDocument.impl.SimulationDocumentFacadeImpl;
import simulator.information.simulationDocument.impl.SimulationDocumentImpl;
import simulator.information.tickDocument.api.TickDocument;
import simulator.information.tickDocument.impl.TickDocumentImpl;
import simulator.mainManager.utils.SimulatorUtils;
import simulator.result.impl.SimulationInitialInfo;
import simulator.result.manager.api.ResultManager;
import simulator.result.manager.impl.ResultManagerImpl;
import simulator.information.manager.api.InformationManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InformationManagerImpl implements InformationManager {
    private ResultManager simulatorResultManager;

    public InformationManagerImpl() {
        this.simulatorResultManager = new ResultManagerImpl();
    }

    @Override
    public SimulationDocument createNewSimulationDocument(WorldDefinition worldDefinition, WorldInstance worldInstance) {
        String guid = SimulatorUtils.getGUID();
        return new SimulationDocumentImpl(guid, worldInstance);
    }

    @Override
    public SimulationDocumentFacade createSimulationDocumentFacade(SimulationDocument simulationDocument) {
        SimulationDocumentFacadeImpl simulationDocumentFacade = new SimulationDocumentFacadeImpl(simulationDocument);
        return null;
    }
}
