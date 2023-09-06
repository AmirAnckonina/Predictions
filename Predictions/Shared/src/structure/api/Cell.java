package structure.api;

public interface Cell<T> {




    T getData();

    void updateData(T data);

    boolean insertObjectToCell(T data);

    eCellContentStatus occupiedStatus();

    boolean isOccupied();

    T removeData();
    Coordinate getCoordinate();
}
