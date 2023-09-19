package simulator.information.tickDocument.impl;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.information.tickDocument.api.TickDocument;

import java.util.List;
import java.util.Map;

public class TickDocumentImpl implements TickDocument {

    private int tickNumber;
    private long timePassedInSeconds;
    Map<String, List<EntityInstance>> entitiesInstancesMap;

    public TickDocumentImpl(int tickNumber, long timePassedInSeconds, Map<String, List<EntityInstance>> entitiesInstancesMap) {
            this.tickNumber = tickNumber;
            this.timePassedInSeconds = timePassedInSeconds;

        synchronized (this) {
            this.entitiesInstancesMap = entitiesInstancesMap;
        }
    }

    @Override
    public int getTickNumber() {
        return this.tickNumber;
    }

    @Override
    public long getTimePassedInSeconds() {
        return this.timePassedInSeconds;
    }

    @Override
    public Map<String, List<EntityInstance>> getEntitiesInstancesMap() {
        return this.entitiesInstancesMap;
    }
}
