package simulator.execution.instance.movement.manager.api;

import simulator.execution.instance.spaceGrid.api.SpaceGridInstanceWrapper;
import simulator.execution.instance.entity.api.EntityInstance;

import java.util.List;
import java.util.Map;

public interface MovementManager {
    void moveAllEntitiesOneStep(SpaceGridInstanceWrapper spaceGridInstanceWrapper);
    void setEntitiesOnSpaceGrid(SpaceGridInstanceWrapper board, List<EntityInstance> entityInstanceList);
    void placeEntitiesRandomizeOnSpaceGrid(Map<String, List<EntityInstance>> entitiesInstances, SpaceGridInstanceWrapper spaceGrid);
}
