package simulator.definition.board.api;

import simulator.execution.instance.entity.api.EntityInstance;
import structure.api.Cell;
import structure.api.Coordinate;

import java.util.List;
import java.util.Optional;

public interface SpaceGridInstance {
    int getTotalNumberOfFreeCells();
    int getTotalNumberOfCells();
    int getHeight();
    int getWidth();
    Cell getCell(Coordinate coordinate);
    List<EntityInstance> getListOfInstancesInFirstCircle(Coordinate coordinate);
    List<EntityInstance> getListOfInstancesInFirstCircleByFamilyName(Coordinate coordinate, Optional<String> FamilyName);
    List<EntityInstance> getListOfInstancesInSecondCircle(Coordinate coordinate);
    List<EntityInstance> getListOfInstancesInSecondCircleByFamilyName(Coordinate coordinate, Optional<String> FamilyName);
    public List<Cell> getListOfCellsInFirstCircle(Coordinate coordinate);
    List<EntityInstance> getListOfAllInstances();
}
