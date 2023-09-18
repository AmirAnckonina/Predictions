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
    List<simulator.execution.instance.entity.api.EntityInstance> getAllInstancesOnSpaceGrid();
    List<Cell> getOneStepVacantCells(Coordinate coordinate);
    List<Coordinate> getOneStepStraightCoordinates(Coordinate coordinate);
    Coordinate getAdjacentStraightCoordinateByDirection(Coordinate coordinate, MoveDirection moveDirection);
    Optional<simulator.execution.instance.entity.api.EntityInstance> searchEntityInstance(Coordinate srcEntityCoordinate, String targetEntityName, Integer envCircleDepthValue);
    List<Coordinate> getAllCoordinatesInNCircleDepth(Coordinate srcCoordinate, int circleDepth);
    Coordinate getNorthCoordinate(Coordinate coordinate);
    Coordinate getEastCoordinate(Coordinate coordinate);
    Coordinate getWestCoordinate(Coordinate coordinate);
    Coordinate getSouthCoordinate(Coordinate coordinate);
    void setCellAsReserved(Coordinate coordinate, simulator.execution.instance.entity.api.EntityInstance entityInstance);
    void applyReservedCellsForCreatedInstances(List<simulator.execution.instance.entity.api.EntityInstance> createdInstances);
    List<simulator.execution.instance.entity.api.EntityInstance> getListOfInstancesInFirstCircle(Coordinate coordinate);
    List<simulator.execution.instance.entity.api.EntityInstance> getListOfInstancesInFirstCircleByFamilyName(Coordinate coordinate, Optional<String> FamilyName);
    List<simulator.execution.instance.entity.api.EntityInstance> getListOfInstancesInSecondCircle(Coordinate coordinate);
    List<simulator.execution.instance.entity.api.EntityInstance> getListOfInstancesInSecondCircleByFamilyName(Coordinate coordinate, Optional<String> FamilyName);
    List<Cell> getListOfCellsInFirstCircle(Coordinate coordinate);
}
