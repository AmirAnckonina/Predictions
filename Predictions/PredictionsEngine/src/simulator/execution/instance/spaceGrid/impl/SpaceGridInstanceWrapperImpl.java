package simulator.execution.instance.spaceGrid.impl;

import simulator.execution.instance.movement.enums.MoveDirection;
import simulator.execution.instance.spaceGrid.api.SpaceGridInstanceWrapper;
import simulator.execution.instance.spaceGrid.api.CellOrInstance;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.runner.utils.exceptions.SimulatorRunnerException;
import structure.api.Cell;
import structure.api.Coordinate;
import structure.impl.CellImpl;
import structure.impl.CoordinateImpl;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class SpaceGridInstanceWrapperImpl implements SpaceGridInstanceWrapper {
    private Integer rows;
    private Integer columns;
    private Integer firstRowIdx;
    private Integer firstColIdx;
    private Integer lastRowIdx;
    private Integer lastColIdx;
    private Integer totalCells;
    private Integer totalUnoccuipiedCells;
    private final Cell<EntityInstance>[][] spaceGrid;
    private List<EntityInstance> neighborsInstances;
    private List<Cell> neighborsCells;

    public SpaceGridInstanceWrapperImpl(Integer rows, Integer columns) {
        this.rows = rows;
        this.firstRowIdx = 0;
        this.lastRowIdx = this.rows - 1;
        this.columns = columns;
        this.firstColIdx = 0;
        this.lastColIdx = this.columns - 1;
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
    public Cell getCellByCoordinate(Coordinate coordinate) {
        return this.spaceGrid[coordinate.getRowIdx()][coordinate.getColIdx()];
    }

    @Override
    public List<EntityInstance> getListOfInstancesInFirstCircle(Coordinate coordinate) {
        getListOfInstancesOrCellsInGenericCircle(coordinate, 1, CellOrInstance.INSTANCE, null);
        return getNeighborsInstancesReference();
    }

    @Override
    public List<EntityInstance> getListOfInstancesInFirstCircleByFamilyName(Coordinate coordinate, Optional<String> FamilyName) {
        getListOfInstancesOrCellsInGenericCircle(coordinate, 1, CellOrInstance.INSTANCE, FamilyName);
        return getNeighborsInstancesReference();
    }

    @Override
    public List<Cell> getListOfCellsInFirstCircle(Coordinate coordinate) {
        getListOfInstancesOrCellsInGenericCircle(coordinate, 1, CellOrInstance.CELL, null);
        return getNeighborsCellsReference();
    }

    @Override
    public List<EntityInstance> getListOfInstancesInSecondCircle(Coordinate coordinate) {
        getListOfInstancesOrCellsInGenericCircle(coordinate, 2, CellOrInstance.INSTANCE, null);
        return getNeighborsInstancesReference();
    }

    @Override
    public List<EntityInstance> getListOfInstancesInSecondCircleByFamilyName(Coordinate coordinate, Optional<String> FamilyName) {
        getListOfInstancesOrCellsInGenericCircle(coordinate, 2, CellOrInstance.INSTANCE, FamilyName);
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
    public List<EntityInstance> getAllInstancesOnSpaceGrid() {

        List<EntityInstance> entityInstanceList = new ArrayList<>();
        Arrays.stream(this.spaceGrid)
                .forEach((rowOfCells) -> {
                    Arrays.stream(rowOfCells)
                            .forEach((singleCell) -> {
                                if (singleCell.isOccupied()) {
                                    entityInstanceList.add(singleCell.getObjectInstance());
                                }
                            });
                });

        return entityInstanceList;
    }

    @Override
    public List<Cell> getOneStepVacantCells(Coordinate coordinate) {

        List<Cell> vacantCells = new ArrayList<>();
        List<Coordinate> oneStepCoordinates = getOneStepCoordinates(coordinate);

        for (Coordinate currAdjCoordinate : oneStepCoordinates) {

            Cell currAdjCell = getCellByCoordinate(currAdjCoordinate);
            if (!currAdjCell.isOccupied()) {
                vacantCells.add(currAdjCell);
            }
        }
        return vacantCells;
    }

    @Override
    public List<Coordinate> getOneStepCoordinates(Coordinate coordinate) {

        List<Coordinate> coordList = new ArrayList<>();
        coordList.add(getAdjacentCoordinateByDirection(coordinate, MoveDirection.NORTH));
        coordList.add(getAdjacentCoordinateByDirection(coordinate, MoveDirection.EAST));
        coordList.add(getAdjacentCoordinateByDirection(coordinate, MoveDirection.WEST));
        coordList.add(getAdjacentCoordinateByDirection(coordinate, MoveDirection.SOUTH));

        return coordList;
    }

    @Override
    public Coordinate getAdjacentCoordinateByDirection(Coordinate coordinate, MoveDirection moveDirection) {
        Coordinate adjCoordinate;

        switch (moveDirection) {
            case NORTH:
                adjCoordinate = getNorthCoordinate(coordinate);
                break;
            case EAST:
                adjCoordinate = getEastCoordinate(coordinate);
                break;
            case WEST:
                adjCoordinate = getWestCoordinate(coordinate);
                break;
            case SOUTH:
                adjCoordinate = getSouthCoordinate(coordinate);
                break;
            default:
                throw new SimulatorRunnerException("Move Direction in order to get adjacent coordinate is invalid");
        }

        return adjCoordinate;
    }

    @Override
    public Coordinate getNorthCoordinate(Coordinate coordinate) {

        int newRowIdx;

        if (coordinate.getRowIdx() - 1 < firstRowIdx) {
            // if we have 10 rows and we should set the new rowIdx to 9
            newRowIdx = lastRowIdx;
        } else {
            newRowIdx = coordinate.getRowIdx() - 1;
        }

        return new CoordinateImpl(newRowIdx, coordinate.getColIdx());
    }

    @Override
    public Coordinate getEastCoordinate(Coordinate coordinate) {
        int newColIdx;

        if (coordinate.getColIdx() + 1 > lastColIdx) {
            newColIdx = firstColIdx;
        } else {
            newColIdx = coordinate.getColIdx() + 1;
        }

        return new CoordinateImpl(coordinate.getRowIdx(), newColIdx);
    }

    @Override
    public Coordinate getWestCoordinate(Coordinate coordinate) {
        int newColIdx;

        if (coordinate.getColIdx() - 1 < firstColIdx) {
            newColIdx = lastColIdx;
        } else {
            newColIdx = coordinate.getColIdx() - 1;
        }

        return new CoordinateImpl(coordinate.getRowIdx(), newColIdx);
    }

    @Override
    public Coordinate getSouthCoordinate(Coordinate coordinate) {
        int newRowIdx;

        if (coordinate.getRowIdx() + 1 > lastColIdx) {
            newRowIdx = firstRowIdx;
        } else {
            newRowIdx = coordinate.getRowIdx() + 1;
        }

        return new CoordinateImpl(newRowIdx, coordinate.getColIdx());
    }


    public void getListOfInstancesOrCellsInGenericCircle(Coordinate coordinate, int distance, CellOrInstance typeOfReturnValue, Optional<String> familyName) {

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
                    if(familyName.isPresent() && familyName.get().equals(spaceGrid[neighborX][neighborY].getObjectInstance().getEntityNameFamily())){
                        neighborsInstances.add(spaceGrid[neighborX][neighborY].getObjectInstance());
                    }
                } else {
                    // Handle boundary conditions
                    int wrappedX = (neighborX + rows) % rows;
                    int wrappedY = (neighborY + columns) % columns;
                    if(familyName.isPresent() && familyName.get().equals(spaceGrid[neighborX][neighborY].getObjectInstance().getEntityNameFamily())){
                        neighborsInstances.add(spaceGrid[wrappedX][wrappedY].getObjectInstance());
                    }
                }
            }
        }
    }
}
