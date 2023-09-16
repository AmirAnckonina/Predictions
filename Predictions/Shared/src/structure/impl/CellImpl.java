package structure.impl;

import structure.api.Cell;
import structure.api.Coordinate;
import structure.api.CellOccupationStatus;

public class CellImpl<T> implements Cell<T>{
    private Coordinate coordinate;
    private T data;
    private CellOccupationStatus contentStatus;

    private CellImpl(Coordinate coordinate, T data, CellOccupationStatus contentStatus) {
        this.coordinate = coordinate;
        this.data = data;
        this.contentStatus = contentStatus;
    }

    public CellImpl(Coordinate coordinate, T data) {
        this(coordinate, data, CellOccupationStatus.OCCUPIED);
    }

    public CellImpl(Coordinate coordinate) {
        this(coordinate, null, CellOccupationStatus.EMPTY);
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void updateData(T data) {
        this.data = data;
    }

    @Override
    public boolean insertObjectToCell(T data) {
        boolean returnVal = false;

        if (this.contentStatus == CellOccupationStatus.EMPTY){
            this.data = data;
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
    public T removeData(){
        T copy = data;
        this.data = null;
        this.contentStatus = CellOccupationStatus.EMPTY;

        return copy;
    }

    @Override
    public Coordinate getCoordinate() {
        return this.coordinate;
    }
}
