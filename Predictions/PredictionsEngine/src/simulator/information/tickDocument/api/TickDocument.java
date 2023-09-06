package simulator.information.tickDocument.api;

import simulator.execution.instance.entity.api.EntityInstance;

import java.util.List;
import java.util.Map;

public interface TickDocument {
    int getTickNumber();
    int getTimePassedInSeconds();
    Map<String, List<EntityInstance>> getEntitiesInstancesMap();

}