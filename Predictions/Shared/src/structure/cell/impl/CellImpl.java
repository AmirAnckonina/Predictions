package structure.cell.impl;

import structure.cell.api.Cell;
import structure.cell.enums.CellOccupationStatus;
import structure.cell.exception.UnauthorizedCellAccess;
import structure.coordinate.api.Coordinate;

public class CellImpl<T> implements Cell<T> {
    private Coordinate coordinate;
    private T objectInstance;
    private CellOccupationStatus occupationStatus;

    private CellImpl(Coordinate coordinate, T objectInstance, CellOccupationStatus occupationStatus) {
        this.coordinate = coordinate;
        this.objectInstance = objectInstance;
        this.occupationStatus = occupationStatus;
    }

    public CellImpl(Coordinate coordinate, T objectInstance) {
        this(coordinate, objectInstance, CellOccupationStatus.OCCUPIED);
    }

    public CellImpl(Coordinate coordinate) {
        this(coordinate, null, CellOccupationStatus.EMPTY);
    }

    @Override
    public T getObjectInstance() {
        if (this.occupationStatus == CellOccupationStatus.RESERVED) {
            throw new UnauthorizedCellAccess("Object instance couldn't be returned, because cell is reserved");
        }
        return objectInstance;
    }

    @Override
    public void updateObjectInstance(T objectInstance) {
        this.objectInstance = objectInstance;
    }

    @Override
    public boolean insertObjectInstanceToCell(T objectInstance) {
        boolean returnVal = false;

        if (this.occupationStatus == CellOccupationStatus.EMPTY){
            this.objectInstance = objectInstance;
            returnVal = true;
            this.occupationStatus = CellOccupationStatus.OCCUPIED;
        }

        return returnVal;
    }

    @Override
    public T removeObjectInstanceFromCell() {
        T copy = objectInstance;
        this.objectInstance = null;
        this.occupationStatus = CellOccupationStatus.EMPTY;

        return copy;
    }

    @Override
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    @Override
    public CellOccupationStatus getCellOccupationStatus() {
        return this.occupationStatus;
    }

    @Override
    public void reserveCell(T objectInstance) {
        this.occupationStatus = CellOccupationStatus.RESERVED;
        this.objectInstance = objectInstance;
    }

    @Override
    public void setOccupationStatus(CellOccupationStatus occupationStatus) {
        this.occupationStatus = occupationStatus;
    }
}
