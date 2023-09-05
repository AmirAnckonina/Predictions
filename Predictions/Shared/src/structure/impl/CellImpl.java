package structure.impl;

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
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public T removeData(){
        T copy = data;
        this.data = null;

        return copy;
    }
}