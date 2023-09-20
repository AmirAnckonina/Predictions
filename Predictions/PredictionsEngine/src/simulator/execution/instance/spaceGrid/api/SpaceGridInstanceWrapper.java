package simulator.execution.instance.spaceGrid.api;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.movement.enums.MoveDirection;
import simulator.execution.instance.spaceGrid.cell.api.Cell;
import structure.coordinate.api.Coordinate;

import java.util.List;
import java.util.Optional;

public interface SpaceGridInstanceWrapper {
    Cell[][] initSpaceGrid();
    int getTotalUnoccupiedCells();
    int getTotalCells();
    int getRows();
    int getColumns();
    Cell getCellByCoordinate(Coordinate coordinate);
    List<EntityInstance> getAllInstancesOnSpaceGrid();
    List<Cell> getOneStepVacantCells(Coordinate coordinate);
    List<Coordinate> getOneStepStraightCoordinates(Coordinate coordinate);
    Coordinate getAdjacentStraightCoordinateByDirection(Coordinate coordinate, MoveDirection moveDirection);
    Optional<EntityInstance> searchEntityInstance(Coordinate srcEntityCoordinate, String targetEntityName, Integer envCircleDepthValue);
    List<Coordinate> getAllCoordinatesInNCircleDepth(Coordinate srcCoordinate, int circleDepth);
    Coordinate getNorthCoordinate(Coordinate coordinate);
    Coordinate getEastCoordinate(Coordinate coordinate);
    Coordinate getWestCoordinate(Coordinate coordinate);
    Coordinate getSouthCoordinate(Coordinate coordinate);
    void setCellAsReserved(Coordinate coordinate);
    void applyReservedCellsForCreatedInstances(List<EntityInstance> createdInstances);
    void applyReservedCellsForKilledInstances(List<EntityInstance> killedInstances);
    void clearCellByCoordinate(Coordinate coordinate);
}
