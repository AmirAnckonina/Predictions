package simulator.information.manager.impl;

import dto.SimulationDocumentInfoDto;
import dto.SimulationEndDto;
import simulator.definition.world.WorldDefinition;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
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
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InformationManagerImpl implements InformationManager {
    private ResultManager simulatorResultManager;
    private Map<String, SimulationDocument> simulationDocumentMap;

    public InformationManagerImpl() {
        this.simulationDocumentMap = new HashMap<>();
        this.simulatorResultManager = new ResultManagerImpl();
    }

    @Override
    public SimulationDocument createNewSimulationDocument(WorldDefinition worldDefinition, WorldInstance worldInstance) {
        SimulationDocument simulationDocument;
        String guid = SimulatorUtils.getGUID();
        simulationDocument = new SimulationDocumentImpl(guid, worldInstance);
        this.simulationDocumentMap.put(guid, simulationDocument);
        return simulationDocument;
    }

    @Override
    public SimulationDocumentFacade createSimulationDocumentFacade(SimulationDocument simulationDocument) {
        SimulationDocumentFacadeImpl simulationDocumentFacade = new SimulationDocumentFacadeImpl(simulationDocument);
        throw new SimulationInformationException("not impl creayeSimulationDocument");
    }

    @Override
    public SimulationDocumentInfoDto getSimulationDocumentInfo(String guid) {

        SimulationDocument simulationDoc = this.simulationDocumentMap.get(guid);
        TickDocument latestTickDoc = simulationDoc.getLatestTickDocument();
        Map<String, Integer> entityPopulationMap = new HashMap<>();
        latestTickDoc
                .getEntitiesInstancesMap()
                .forEach((entityName, entityInstances) -> entityPopulationMap.put(entityName, entityInstances.size()));

        return new SimulationDocumentInfoDto(
                simulationDoc.getSimulationGuid(),
                simulationDoc.getSimulationStatus(),
                simulationDoc.getLatestTickDocument().getTickNumber(),
                simulationDoc.getLatestTickDocument().getTimePassedInSeconds(),
                entityPopulationMap
        );
    }

}
