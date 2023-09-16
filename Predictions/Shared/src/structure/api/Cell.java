package structure.api;

public interface Cell<T> {
    T getObjectInstance();
    void updateObjectInstance(T data);
    boolean insertObjectInstanceToCell(T data);
    CellOccupationStatus occupiedStatus();
    boolean isOccupied();
    T removeObjectInstanceFromCell();
    Coordinate getCoordinate();
}
