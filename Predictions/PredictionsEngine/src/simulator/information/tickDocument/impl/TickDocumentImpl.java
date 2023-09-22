package simulator.information.tickDocument.impl;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.information.tickDocument.api.TickDocument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TickDocumentImpl implements TickDocument {

    private int tickNumber;
    private long timePassedInSeconds;
    Map<String, List<EntityInstance>> entitiesInstancesMap;
    Map<String, Integer> entitiesPopulationStatusMap;

    public TickDocumentImpl(int tickNumber, long timePassedInSeconds, Map<String, List<EntityInstance>> entitiesInstancesMap) {
            this.tickNumber = tickNumber;
            this.timePassedInSeconds = timePassedInSeconds;
            this.entitiesPopulationStatusMap = new HashMap<>();
        synchronized (this) {
            this.entitiesInstancesMap = entitiesInstancesMap;
        }
    }

    @Override
    public int getTickNumber() {
        return this.tickNumber;
    }

    @Override
    public Map<String, Integer> getEntitiesPopulationStatusMap() {
        return entitiesPopulationStatusMap;
    }

    @Override
    public long getTimePassedInSeconds() {
        return this.timePassedInSeconds;
    }

    @Override
    public Map<String, List<EntityInstance>> getEntitiesInstancesMap() {
        return this.entitiesInstancesMap;
    }

    @Override
    public void startingTickUpdate() {
        this.entitiesInstancesMap
                .forEach((entityName, entityInstancesList) ->
                        this.entitiesPopulationStatusMap.put(entityName, entityInstancesList.size()));

    }
}
