package structure.impl;

import structure.api.Cell;
import structure.api.Coordinate;
import structure.api.CellOccupationStatus;

public class CellImpl<T> implements Cell<T>{
    private Coordinate coordinate;
    private T objectInstance;
    private CellOccupationStatus contentStatus;

    private CellImpl(Coordinate coordinate, T objectInstance, CellOccupationStatus contentStatus) {
        this.coordinate = coordinate;
        this.objectInstance = objectInstance;
        this.contentStatus = contentStatus;
    }

    public CellImpl(Coordinate coordinate, T objectInstance) {
        this(coordinate, objectInstance, CellOccupationStatus.OCCUPIED);
    }

    public CellImpl(Coordinate coordinate) {
        this(coordinate, null, CellOccupationStatus.EMPTY);
    }

    @Override
    public T getObjectInstance() {
        return objectInstance;
    }

    @Override
    public void updateObjectInstance(T objectInstance) {
        this.objectInstance = objectInstance;
    }

    @Override
    public boolean insertObjectInstanceToCell(T objectInstance) {
        boolean returnVal = false;

        if (this.contentStatus == CellOccupationStatus.EMPTY){
            this.objectInstance = objectInstance;
            returnVal = true;
            this.contentStatus = CellOccupationStatus.OCCUPIED;
        }

        return returnVal;
    }

    @Override
    public CellOccupationStatus occupiedStatus() {
        return this.contentStatus;
    }

    @Override
    public boolean isOccupied() {
        return this.contentStatus == CellOccupationStatus.OCCUPIED;
    }

    @Override
    public T removeObjectInstanceFromCell() {
        T copy = objectInstance;
        this.objectInstance = null;
        this.contentStatus = CellOccupationStatus.EMPTY;

        return copy;
    }

    @Override
    public Coordinate getCoordinate() {
        return this.coordinate;
    }
}
