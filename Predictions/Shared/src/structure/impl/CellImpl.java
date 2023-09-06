package structure.impl;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import structure.api.Cell;
import structure.api.Coordinate;
import structure.api.eCellContentStatus;

public class CellImpl<T> implements Cell<T>{
    private Coordinate coordinate;
    private T data;
    private eCellContentStatus contentStatus;

    private CellImpl(Coordinate coordinate, T data, eCellContentStatus contentStatus) {
        this.coordinate = coordinate;
        this.data = data;
        this.contentStatus = contentStatus;
    }

    public CellImpl(Coordinate coordinate, T data) {
        this(coordinate, data, eCellContentStatus.OCCUPIED);
    }

    public CellImpl(Coordinate coordinate) {
        this(coordinate, null, eCellContentStatus.EMPTY);
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

        if(this.contentStatus == eCellContentStatus.EMPTY){
            this.data = data;
            returnVal = true;
            this.contentStatus = eCellContentStatus.OCCUPIED;
        }

        return returnVal;
    }

    @Override
    public eCellContentStatus occupiedStatus() {
        return this.contentStatus;
    }

    @Override
    public boolean isOccupied() {
        return this.contentStatus == eCellContentStatus.OCCUPIED;
    }

    @Override
    public T removeData(){
        T copy = data;
        this.data = null;
        this.contentStatus = eCellContentStatus.EMPTY;

        return copy;
    }

    @Override
    public Coordinate getCoordinate() {
        return this.coordinate;
    }
}
