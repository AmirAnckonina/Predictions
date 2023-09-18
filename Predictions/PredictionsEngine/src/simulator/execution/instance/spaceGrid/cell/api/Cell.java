package simulator.execution.instance.spaceGrid.cell.api;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.spaceGrid.cell.enums.CellOccupationStatus;
import structure.coordinate.api.Coordinate;

public interface Cell {
    EntityInstance getObjectInstance();
    void updateObjectInstance(EntityInstance data);
    boolean insertObjectInstanceToCell(EntityInstance data);
    EntityInstance removeObjectInstanceFromCell();
    Coordinate getCoordinate();
    CellOccupationStatus getCellOccupationStatus();
    void reserveCell(EntityInstance objectInstance);
    void setOccupationStatus(CellOccupationStatus occupationStatus);
}
