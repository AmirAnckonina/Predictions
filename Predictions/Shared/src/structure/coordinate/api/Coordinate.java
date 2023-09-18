package structure.coordinate.api;

public interface Coordinate {

    int getRowIdx();
    int getColIdx();
    boolean coordinateEquals(Coordinate otherCoordinate);
    void setColIdx(int colIdx);
    void setRowIdx(int rowIdx);

}
