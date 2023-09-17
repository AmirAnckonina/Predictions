package structure.impl;

import structure.api.Coordinate;

public class CoordinateImpl implements Coordinate {

    private int colIdx;
    private int rowIdx;

    public CoordinateImpl(int rowIdx, int colIdx) {
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;
    }

    @Override
    public int getColIdx() {
        return colIdx;
    }

    @Override
    public int getRowIdx() {
        return rowIdx;
    }

    @Override
    public void setColIdx(int colIdx) {
        this.colIdx = colIdx;
    }

    @Override
    public void setRowIdx(int rowIdx) {
        this.rowIdx = rowIdx;
    }
}
