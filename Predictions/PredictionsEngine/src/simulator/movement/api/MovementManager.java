package simulator.movement.api;

import simulator.definition.board.api.Board;
import simulator.execution.instance.entity.api.EntityInstance;

import java.util.List;
import java.util.Map;

public interface MovementManager {
    void moveAllEntitiesOneStep(Board board);
    void setEntitiesOnSpaceGrid(Board board, List<EntityInstance> entityInstanceList);
    void placeEntitiesRandomizeOnSpaceGrid(Map<String, List<EntityInstance>> entitiesInstances, Board spaceGrid);
}
