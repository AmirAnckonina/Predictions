package simulator.movement.api;

import simulator.definition.board.api.SpaceGridInstance;
import simulator.execution.instance.entity.api.EntityInstance;

import java.util.List;
import java.util.Map;

public interface MovementManager {
    void moveAllEntitiesOneStep(SpaceGridInstance board);
    void setEntitiesOnSpaceGrid(SpaceGridInstance board, List<EntityInstance> entityInstanceList);
    void placeEntitiesRandomizeOnSpaceGrid(Map<String, List<EntityInstance>> entitiesInstances, SpaceGridInstance spaceGrid);
}
