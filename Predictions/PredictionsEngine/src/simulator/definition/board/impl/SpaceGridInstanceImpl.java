package simulator.definition.board.impl;

import simulator.definition.board.api.SpaceGridInstance;
import simulator.definition.board.api.eCellOrInstance;
import simulator.execution.instance.entity.api.EntityInstance;
import structure.api.Cell;
import structure.api.Coordinate;
import structure.impl.CellImpl;
import structure.impl.CoordinateImpl;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class SpaceGridInstanceImpl implements SpaceGridInstance {
    private Integer rows;
    private Integer columns;
    private Integer totalCells;
    private Integer totalUnoccuipiedCells;
    private final Cell<EntityInstance>[][] spaceGrid;
    private List<EntityInstance> neighborsInstances;
    private List<Cell> neighborsCells;

    public SpaceGridInstanceImpl(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
        this.totalCells = this.totalUnoccuipiedCells = rows * columns;
        this.spaceGrid = initSpaceGrid();
    }


    @Override
    public Cell<EntityInstance>[][] initSpaceGrid() {
        Cell<EntityInstance>[][] spaceGrid = new CellImpl[this.rows][this.columns];

        for (int rowIdx = 0; rowIdx < rows; rowIdx++) {
            for (int colIdx = 0; colIdx < columns; colIdx++) {
                spaceGrid[rowIdx][colIdx] = new CellImpl<>(new CoordinateImpl(rowIdx, colIdx));
            }
        }

        return spaceGrid;
    }

    @Override
    public int getTotalUnoccuipiedCells() {
        return this.totalUnoccuipiedCells;
    }

    @Override
    public int getTotalCells() {
        return this.totalCells;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public Cell getCell(Coordinate coordinate) {
        return this.spaceGrid[coordinate.getColIdx()][coordinate.getRowIdx()];
    }

    @Override
    public List<EntityInstance> getListOfInstancesInFirstCircle(Coordinate coordinate) {
        getListOfInstancesOrCellsInGenericCircle(coordinate, 1, eCellOrInstance.INSTANCE, null);
        return getNeighborsInstancesReference();
    }

    @Override
    public List<EntityInstance> getListOfInstancesInFirstCircleByFamilyName(Coordinate coordinate, Optional<String> FamilyName) {
        getListOfInstancesOrCellsInGenericCircle(coordinate, 1, eCellOrInstance.INSTANCE, FamilyName);
        return getNeighborsInstancesReference();
    }

    @Override
    public List<Cell> getListOfCellsInFirstCircle(Coordinate coordinate) {
        getListOfInstancesOrCellsInGenericCircle(coordinate, 1, eCellOrInstance.CELL, null);
        return getNeighborsCellsReference();
    }

    @Override
    public List<EntityInstance> getListOfInstancesInSecondCircle(Coordinate coordinate) {
        getListOfInstancesOrCellsInGenericCircle(coordinate, 2, eCellOrInstance.INSTANCE, null);
        return getNeighborsInstancesReference();
    }

    @Override
    public List<EntityInstance> getListOfInstancesInSecondCircleByFamilyName(Coordinate coordinate, Optional<String> FamilyName) {
        getListOfInstancesOrCellsInGenericCircle(coordinate, 2, eCellOrInstance.INSTANCE, FamilyName);
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
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                entityInstanceList.add(spaceGrid[x][y].getData());
            }
        }

        return entityInstanceList;
    }

    public void getListOfInstancesOrCellsInGenericCircle(Coordinate coordinate, int distance, eCellOrInstance typeOfReturnValue, Optional<String> familyName) {

        this.neighborsInstances = new ArrayList<>();
        this.neighborsCells = new ArrayList<>();

        for (int dx = -distance; dx <= distance; dx++) {
            for (int dy = -distance; dy <= distance; dy++) {
                if ((dx <= distance - 1 && dx >= 0 && dy <= distance - 1 && dy >= 0) ||
                        (dx >= -distance + 1 && dx <= 0 && dy >= -distance + 1 && dy <= 0)) {
                    // Skip the current cell itself and the all cell closer than the generic circle
                    continue;
                }

                int neighborX = coordinate.getColIdx() + dx;
                int neighborY = coordinate.getRowIdx() + dy;

                neighborsCells.add(spaceGrid[neighborX][neighborY]);

                // Check if the neighbor is within the matrix boundaries
                if (neighborX >= 0 && neighborX < rows && neighborY >= 0 && neighborY < columns) {
                    if(familyName.isPresent() && familyName.get().equals(spaceGrid[neighborX][neighborY].getData().getEntityNameFamily())){
                        neighborsInstances.add(spaceGrid[neighborX][neighborY].getData());
                    }
                } else {
                    // Handle boundary conditions
                    int wrappedX = (neighborX + rows) % rows;
                    int wrappedY = (neighborY + columns) % columns;
                    if(familyName.isPresent() && familyName.get().equals(spaceGrid[neighborX][neighborY].getData().getEntityNameFamily())){
                        neighborsInstances.add(spaceGrid[wrappedX][wrappedY].getData());
                    }
                }
            }
        }
    }
}
