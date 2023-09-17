package simulator.execution.instance.spaceGrid.api;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.movement.enums.MoveDirection;
import structure.cell.api.Cell;
import structure.coordinate.api.Coordinate;

import java.util.List;
import java.util.Optional;

public interface SpaceGridInstanceWrapper {
    Cell<EntityInstance>[][] initSpaceGrid();
    int getTotalUnoccuipiedCells();
    int getTotalCells();
    int getRows();
    int getColumns();
    Cell getCellByCoordinate(Coordinate coordinate);
    List<EntityInstance> getListOfInstancesInFirstCircle(Coordinate coordinate);
    List<EntityInstance> getListOfInstancesInFirstCircleByFamilyName(Coordinate coordinate, Optional<String> FamilyName);
    List<EntityInstance> getListOfInstancesInSecondCircle(Coordinate coordinate);
    List<EntityInstance> getListOfInstancesInSecondCircleByFamilyName(Coordinate coordinate, Optional<String> FamilyName);
    List<Cell> getListOfCellsInFirstCircle(Coordinate coordinate);
    List<EntityInstance> getAllInstancesOnSpaceGrid();
    List<Cell> getOneStepVacantCells(Coordinate coordinate);
    List<Coordinate> getOneStepCoordinates(Coordinate coordinate);
    Coordinate getAdjacentCoordinateByDirection(Coordinate coordinate, MoveDirection moveDirection);
    Coordinate getNorthCoordinate(Coordinate coordinate);
    Coordinate getEastCoordinate(Coordinate coordinate);
    Coordinate getWestCoordinate(Coordinate coordinate);
    Coordinate getSouthCoordinate(Coordinate coordinate);
    void setCellAsReserved(Coordinate coordinate, EntityInstance entityInstance);
    void applyReservedCellsForCreatedInstances(List<EntityInstance> createdInstances);
}
