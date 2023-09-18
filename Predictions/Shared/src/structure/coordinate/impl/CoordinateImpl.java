package structure.coordinate.impl;

import structure.coordinate.api.Coordinate;

import java.util.Objects;

public class CoordinateImpl implements Coordinate {

    private int rowIdx;
    private int colIdx;

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
    public boolean coordinateEquals(Coordinate otherCoordinate) {
        return this.rowIdx == otherCoordinate.getRowIdx() && this.colIdx == otherCoordinate.getColIdx();
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
