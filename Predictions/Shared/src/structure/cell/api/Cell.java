package structure.cell.api;

import structure.cell.enums.CellOccupationStatus;
import structure.coordinate.api.Coordinate;

public interface Cell<T> {
    T getObjectInstance();
    void updateObjectInstance(T data);
    boolean insertObjectInstanceToCell(T data);
    T removeObjectInstanceFromCell();
    Coordinate getCoordinate();
    CellOccupationStatus getCellOccupationStatus();
    void reserveCell(T objectInstance);
    void setOccupationStatus(CellOccupationStatus occupationStatus);
}
