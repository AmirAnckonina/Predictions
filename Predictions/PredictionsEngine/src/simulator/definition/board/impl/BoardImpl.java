package simulator.definition.board.impl;

import simulator.definition.board.api.Board;
import simulator.execution.instance.entity.api.EntityInstance;
import structure.api.Cell;
import structure.impl.CellImpl;

import java.util.List;
import java.util.ArrayList;

public class BoardImpl implements Board {
    private Integer height = null;
    private Integer width = null;
    private Integer totalNumberOfCells = null;
    private Integer totalNumberOfFreeCells = null;

    private CellImpl<EntityInstance>[][] matrix = null;

    public BoardImpl(Integer height, Integer width) {
        this.height = height;
        this.width = width;
        this.totalNumberOfCells = this.totalNumberOfFreeCells = height * width;
        this.matrix = new CellImpl[width][height];
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
    public CellImpl getCell(int x, int y) {
        return this.matrix[x][y];
    }

    @Override
    public List<EntityInstance> getListOfInstancesInFirstCircle(int x, int y) {

        return this.getListOfInstancesInGenericCircle(x,y, 1);
    }

    @Override
    public List<EntityInstance> getListOfInstancesInSecondCircle(int x, int y) {

        return this.getListOfInstancesInGenericCircle(x,y, 2);
    }

    public List<EntityInstance> getListOfInstancesInGenericCircle(int x, int y, int distance) {

        List<EntityInstance> neighbors = new ArrayList<>();

        for (int dx = -distance; dx <= distance; dx++) {
            for (int dy = -distance; dy <= distance; dy++) {
                if (dx == 0 && dy == 0) {
                    // Skip the current cell itself
                    continue;
                }

                int neighborX = x + dx;
                int neighborY = y + dy;

                // Check if the neighbor is within the matrix boundaries
                if (neighborX >= 0 && neighborX < height && neighborY >= 0 && neighborY < width) {
                    neighbors.add(matrix[neighborX][neighborY].getData());
                } else {
                    // Handle boundary conditions
                    int wrappedX = (neighborX + height) % height;
                    int wrappedY = (neighborY + width) % width;
                    neighbors.add(matrix[wrappedX][wrappedY].getData());
                }
            }
        }

        return neighbors;
    }
}
