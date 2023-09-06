package structure.impl;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import structure.api.Cell;
import structure.api.eCellContentStatus;

public class CellImpl<T> implements Cell<T>{
    private int x;
    private int y;
    private T data;
    private eCellContentStatus contentStatus;

    private CellImpl(int x, int y, T data, eCellContentStatus contentStatus) {
        this.x = x;
        this.y = y;
        this.data = data;
        this.contentStatus = contentStatus;
    }

    public CellImpl(int x, int y, T data) {
        this(x, y, data, eCellContentStatus.OCCUPIED);
    }

    public CellImpl(int x, int y) {
        this(x, y, null, eCellContentStatus.EMPTY);
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

        return copy;
    }
}
