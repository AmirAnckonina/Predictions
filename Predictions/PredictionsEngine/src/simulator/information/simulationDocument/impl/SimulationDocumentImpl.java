package simulator.information.simulationDocument.impl;

import dto.SimulationDocumentInfoDto;
import dto.SimulationManualParamsDto;
import enums.SimulationStatus;
import simulator.definition.entity.EntityDefinition;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.information.manager.exception.SimulationInformationException;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.information.tickDocument.api.TickDocument;
import simulator.result.api.SimulationResult;
import simulator.result.impl.SimulationResultImpl;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SimulationDocumentImpl implements SimulationDocument {
    private static final Integer INIT_TICK = -1;
    private String SimulationGuid;
    private WorldInstance worldInstance;
    private Map<Integer, TickDocument> tickDocumentMap;
    private SimulationResult simulationResult;
    private SimulationStatus simulationStatus;
    private SimulationDocumentInfoDto initialSimulationDocumentInfoDto;
    private SimulationManualParamsDto simulationManualParamsDto;
//private Map<SimulationStatus, SimulationDocumentInfoDto> statusInfoMap;

    public SimulationDocumentImpl(String simulationGuid, WorldInstance worldInstance) {
        synchronized (this) {
            this.SimulationGuid = simulationGuid;
            this.worldInstance = worldInstance;
            this.simulationStatus = SimulationStatus.READY;
            this.tickDocumentMap = new ConcurrentHashMap<>();
        }
        this.simulationManualParamsDto = simulationManualParamsDto;
        this.createInitialSimulationDocumentInfoDto();
        this.createSimulationManualParamsDto();
    }

    private void createSimulationManualParamsDto() {
        Map<String, Integer> entityPopulationParamsMap =
                this.worldInstance
                        .getEntityDefinitionMap()
                        .values()
                        .stream()
                        .collect(Collectors.toMap(EntityDefinition::getName, EntityDefinition::getPopulation));

        Map<String, ?> envPropertiesParamsMap =
                this.worldInstance
                        .getEnvironmentInstance()
                        .getAllEnvironmentPropertiesInstances()
                        .values()
                        .stream()
                        .collect(Collectors.toMap(
                                propertyInstance -> propertyInstance.getPropertyDefinition().getName(),
                                PropertyInstance::getValue)
                        );

        this.simulationManualParamsDto = new SimulationManualParamsDto(entityPopulationParamsMap, envPropertiesParamsMap);
    }

    @Override
    public void SetSimulationManualParams(SimulationManualParamsDto simulationManualParamsDto) {
        this.simulationManualParamsDto = simulationManualParamsDto;
    }

    @Override
    public SimulationManualParamsDto getSimulationManualParamsDto() {
        return this.simulationManualParamsDto;
    }

    private void createInitialSimulationDocumentInfoDto() {
        Map<String, Integer> initEntityPopulationMap = new HashMap<>();

        synchronized (this) {
            this.worldInstance
                    .getEntitiesInstances()
                    .forEach((entityName, entityInstances) -> {
                        initEntityPopulationMap.put(entityName, entityInstances.size());
                    });
        }

        this.initialSimulationDocumentInfoDto =
                new SimulationDocumentInfoDto(
                        this.SimulationGuid,
                        this.simulationStatus,
                        INIT_TICK,
                        new Long(0),
                        initEntityPopulationMap,
                        initEntityPopulationMap
                );
    }

    @Override
    public SimulationDocumentInfoDto getInitialSimulationDocumentInfoDto() {
        return initialSimulationDocumentInfoDto;
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
        synchronized (this){
            this.tickDocumentMap.put(tickDocument.getTickNumber(), tickDocument);
        }
    }

    @Override
    public void setSimulationStatus(SimulationStatus simulationStatus) {
        synchronized (this){
            this.simulationStatus = simulationStatus;
        }
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

    @Override
    public void finishSimulationSession(Long simulationStartingTime) {
        this.simulationResult = new SimulationResultImpl(this.SimulationGuid, this.worldInstance,
                this.getInitialSimulationDocumentInfoDto().getInitialEntityPopulationMap(),
                simulationStartingTime);
        simulationStatus = SimulationStatus.COMPLETED;
    }
}
