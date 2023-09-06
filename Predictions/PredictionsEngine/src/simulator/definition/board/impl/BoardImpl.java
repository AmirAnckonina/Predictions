package simulator.definition.board.impl;

import simulator.definition.board.api.Board;
import simulator.definition.board.api.eCellOrInstance;
import simulator.execution.instance.entity.api.EntityInstance;
import structure.api.Cell;
import structure.api.Coordinate;
import structure.impl.CellImpl;
import structure.impl.CoordinateImpl;

import java.util.List;
import java.util.ArrayList;

public class BoardImpl implements Board {
    private Integer height = null;
    private Integer width = null;
    private Integer totalNumberOfCells = null;
    private Integer totalNumberOfFreeCells = null;

    private Cell<EntityInstance>[][] matrix = null;
    private List<EntityInstance> neighborsInstances = null;
    private List<Cell> neighborsCells = null;

    public BoardImpl(Integer height, Integer width) {
        this.height = height;
        this.width = width;
        this.totalNumberOfCells = this.totalNumberOfFreeCells = height * width;
        this.matrix = new CellImpl[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                matrix[x][y] = new CellImpl<>(new CoordinateImpl(x, y));
            }
        }
    }

    @Override
    public int getTotalNumberOfFreeCells() {
        return this.totalNumberOfFreeCells;
    }

    @Override
    public int getTotalNumberOfCells() {
        return this.totalNumberOfCells;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public Cell getCell(Coordinate coordinate) {
        return this.matrix[coordinate.getX()][coordinate.getY()];
    }

    @Override
    public List<EntityInstance> getListOfInstancesInFirstCircle(Coordinate coordinate) {
        getListOfInstancesOrCellsInGenericCircle(coordinate, 1, eCellOrInstance.INSTANCE);
        return getNeighborsInstancesReference();
    }

    @Override
    public List<Cell> getListOfCellsInFirstCircle(Coordinate coordinate) {
        getListOfInstancesOrCellsInGenericCircle(coordinate, 1, eCellOrInstance.CELL);
        return getNeighborsCellsReference();
    }

    @Override
    public List<EntityInstance> getListOfInstancesInSecondCircle(Coordinate coordinate) {
        getListOfInstancesOrCellsInGenericCircle(coordinate, 2, eCellOrInstance.INSTANCE);
        return getNeighborsInstancesReference();
    }

    private List<EntityInstance> getNeighborsInstancesReference(){
        List<EntityInstance> tempList = this.neighborsInstances;
        this.neighborsInstances = null;

        return tempList;
    }

    private List<Cell> getNeighborsCellsReference(){
        List<Cell> tempList = this.neighborsCells;
        this.neighborsCells = null;

        return tempList;
    }

    @Override
    public List<EntityInstance> getListOfAllInstances() {
        List<EntityInstance> entityInstanceList = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                entityInstanceList.add(matrix[x][y].getData());
            }
        }

        return entityInstanceList;
    }

    public void getListOfInstancesOrCellsInGenericCircle(Coordinate coordinate, int distance, eCellOrInstance typeOfReturnValue) {

        this.neighborsInstances = new ArrayList<>();
        this.neighborsCells = new ArrayList<>();

        for (int dx = -distance; dx <= distance; dx++) {
            for (int dy = -distance; dy <= distance; dy++) {
                if ((dx <= distance - 1 && dx >= 0 && dy <= distance - 1 && dy >= 0) ||
                        (dx >= -distance + 1 && dx <= 0 && dy >= -distance + 1 && dy <= 0)) {
                    // Skip the current cell itself and the all cell closer than the generic circle
                    continue;
                }

                int neighborX = coordinate.getX() + dx;
                int neighborY = coordinate.getY() + dy;

                neighborsCells.add(matrix[neighborX][neighborY]);

                // Check if the neighbor is within the matrix boundaries
                if (neighborX >= 0 && neighborX < height && neighborY >= 0 && neighborY < width) {
                    neighborsInstances.add(matrix[neighborX][neighborY].getData());
                } else {
                    // Handle boundary conditions
                    int wrappedX = (neighborX + height) % height;
                    int wrappedY = (neighborY + width) % width;
                    neighborsInstances.add(matrix[wrappedX][wrappedY].getData());
                }
            }
        }
    }
}
