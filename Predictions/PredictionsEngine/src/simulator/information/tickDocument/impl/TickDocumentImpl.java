package simulator.information.tickDocument.impl;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.information.tickDocument.api.TickDocument;

import java.util.List;
import java.util.Map;

public class TickDocumentImpl implements TickDocument {

    private int tickNumber;
    private int timePassedInSeconds;
    Map<String, List<EntityInstance>> entitiesInstancesMap;

    public TickDocumentImpl(int tickNumber, int timePassedInSeconds, Map<String, List<EntityInstance>> entitiesInstancesMap) {
        this.tickNumber = tickNumber;
        this.timePassedInSeconds = timePassedInSeconds;
        this.entitiesInstancesMap = entitiesInstancesMap;
    }

    @Override
    public int getTickNumber() {
        return this.tickNumber;
    }

    @Override
    public int getTimePassedInSeconds() {
        return this.timePassedInSeconds;
    }

    @Override
    public Map<String, List<EntityInstance>> getEntitiesInstancesMap() {
        return this.entitiesInstancesMap;
    }
}
