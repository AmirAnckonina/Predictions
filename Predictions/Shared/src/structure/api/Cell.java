package structure.api;

public interface Cell<T> {




    public T getData();

    public void updateData(T data);

    public boolean insertObjectToCell(T data);

    public eCellContentStatus occupiedStatus();

    public boolean isOccupied();

    public T removeData();
}
