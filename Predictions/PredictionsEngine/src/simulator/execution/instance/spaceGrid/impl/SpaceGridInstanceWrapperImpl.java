package simulator.execution.instance.spaceGrid.impl;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.movement.enums.MoveDirection;
import simulator.execution.instance.spaceGrid.api.SpaceGridInstanceWrapper;
import simulator.execution.instance.spaceGrid.api.CellOrInstance;
import simulator.runner.utils.exceptions.SimulatorRunnerException;
import simulator.execution.instance.spaceGrid.cell.api.Cell;
import simulator.execution.instance.spaceGrid.cell.enums.CellOccupationStatus;
import structure.coordinate.api.Coordinate;
import simulator.execution.instance.spaceGrid.cell.impl.CellImpl;
import structure.coordinate.impl.CoordinateImpl;

import java.util.*;
import java.util.stream.Collectors;

public class SpaceGridInstanceWrapperImpl implements SpaceGridInstanceWrapper {
    private final Integer rows;
    private final Integer columns;
    private final Integer firstRowIdx;
    private final Integer firstColIdx;
    private final Integer lastRowIdx;
    private final Integer lastColIdx;
    private final Integer totalCells;
    private Integer totalUnoccupiedCells;
    private final Cell[][] spaceGrid;
    private List<simulator.execution.instance.entity.api.EntityInstance> neighborsInstances;
    private List<Cell> neighborsCells;

    public SpaceGridInstanceWrapperImpl(Integer rows, Integer columns) {
        this.rows = rows;
        this.firstRowIdx = 0;
        this.lastRowIdx = this.rows - 1;
        this.columns = columns;
        this.firstColIdx = 0;
        this.lastColIdx = this.columns - 1;
        this.totalCells = this.totalUnoccupiedCells = rows * columns;
        this.spaceGrid = initSpaceGrid();
    }


    @Override
    public Cell[][] initSpaceGrid() {
        Cell[][] spaceGrid = new CellImpl[this.rows][this.columns];

        for (int rowIdx = 0; rowIdx < rows; rowIdx++) {
            for (int colIdx = 0; colIdx < columns; colIdx++) {
                spaceGrid[rowIdx][colIdx] = new CellImpl(new CoordinateImpl(rowIdx, colIdx));
            }
        }

        return spaceGrid;
    }

    @Override
    public int getTotalUnoccupiedCells() {
        return this.totalUnoccupiedCells;
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
    public void clearCellByCoordinate(Coordinate coordinate) {
        this.getCellByCoordinate(coordinate).removeObjectInstanceFromCell();
    }

    @Override
    public List<EntityInstance> getAllInstancesOnSpaceGrid() {

        List<EntityInstance> entityInstanceList = new ArrayList<>();
        Arrays.stream(this.spaceGrid)
                .forEach((rowOfCells) -> {
                    Arrays.stream(rowOfCells)
                            .forEach((singleCell) -> {
                                if (singleCell.getCellOccupationStatus() == CellOccupationStatus.OCCUPIED) {
                                    entityInstanceList.add(singleCell.getObjectInstance());
                                }
                            });
                });

        return entityInstanceList;
    }

    @Override
    public List<Cell> getOneStepVacantCells(Coordinate coordinate) {

        List<Cell> vacantCells = new ArrayList<>();
        List<Coordinate> oneStepCoordinates = getOneStepStraightCoordinates(coordinate);

        for (Coordinate currAdjCoordinate : oneStepCoordinates) {

            Cell currAdjCell = getCellByCoordinate(currAdjCoordinate);
            if (currAdjCell.getCellOccupationStatus() == CellOccupationStatus.EMPTY) {
                vacantCells.add(currAdjCell);
            }
        }
        return vacantCells;
    }

    @Override
    public List<Coordinate> getOneStepStraightCoordinates(Coordinate coordinate) {

        List<Coordinate> coordList = new ArrayList<>();
        coordList.add(getAdjacentStraightCoordinateByDirection(coordinate, MoveDirection.NORTH));
        coordList.add(getAdjacentStraightCoordinateByDirection(coordinate, MoveDirection.EAST));
        coordList.add(getAdjacentStraightCoordinateByDirection(coordinate, MoveDirection.WEST));
        coordList.add(getAdjacentStraightCoordinateByDirection(coordinate, MoveDirection.SOUTH));

        return coordList;
    }

    @Override
    public Coordinate getAdjacentStraightCoordinateByDirection(Coordinate coordinate, MoveDirection moveDirection) {
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
    public Optional<EntityInstance> searchEntityInstance(Coordinate srcEntityCoordinate, String targetEntityName, Integer envCircleDepthValue) {
        
        EntityInstance singleTargetEntityInstance = null;
        
        // get all coordinates to the Nth circle
        List<Coordinate> allCoordinatesInDepth = getAllCoordinatesInNCircleDepth(srcEntityCoordinate, envCircleDepthValue);
        List<EntityInstance> targetEntityInstances =
                allCoordinatesInDepth
                        .stream()
                        .map(this::getCellByCoordinate)
                        .filter(cell -> cell.getCellOccupationStatus() == CellOccupationStatus.OCCUPIED)
                        .map(Cell::getObjectInstance)
                        .filter(objectInstance -> objectInstance.getEntityNameFamily().equals(targetEntityName))
                        .collect(Collectors.toList());

        if (!targetEntityInstances.isEmpty()) {
            int randIdx = new Random().nextInt(targetEntityInstances.size());
            singleTargetEntityInstance = targetEntityInstances.get(randIdx);
        }

        return Optional.ofNullable(singleTargetEntityInstance);
    }

    @Override
    public List<Coordinate> getAllCoordinatesInNCircleDepth(Coordinate srcCoordinate, int circleDepth) {

        List<Coordinate> coordinates = new ArrayList<>();
        int srcRowIdx = srcCoordinate.getRowIdx();
        int srcColIdx = srcCoordinate.getColIdx();

        for (int dRow = -circleDepth; dRow <= circleDepth; dRow++) {
            for (int dCol = -circleDepth; dCol <= circleDepth; dCol++) {
                int actualRowIdx = (dRow + srcRowIdx + rows) % rows; //= 10
                int actualColIdx = (dCol + srcColIdx + columns) % columns; // = 12 (lastIdx 11)
                coordinates.add(new CoordinateImpl(actualRowIdx, actualColIdx));
            }
        }

        coordinates.removeIf((coordinate) ->
                coordinate.getRowIdx() == srcRowIdx && coordinate.getColIdx() == srcColIdx);

        return coordinates;
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

        if (coordinate.getRowIdx() + 1 > lastRowIdx) {
            newRowIdx = firstRowIdx;
        } else {
            newRowIdx = coordinate.getRowIdx() + 1;
        }

        return new CoordinateImpl(newRowIdx, coordinate.getColIdx());
    }

    @Override
    public void setCellAsReserved(Coordinate coordinate) {
        this.getCellByCoordinate(coordinate).reserveCell();
    }

    @Override
    public void applyReservedCellsForCreatedInstances(List<EntityInstance> createdInstances) {

        List<Cell> reservedCells =
                createdInstances
                        .stream()
                        .map((singleInstance) -> this.getCellByCoordinate(singleInstance.getCoordinate()))
                        .collect(Collectors.toList());

        reservedCells.forEach((cell) -> cell.setOccupationStatus(CellOccupationStatus.OCCUPIED));
    }

    @Override
    public void applyReservedCellsForKilledInstances(List<EntityInstance> killedInstances) {

            List<Cell> reservedCells =
                    killedInstances
                            .stream()
                            .map((singleInstance) -> this.getCellByCoordinate(singleInstance.getCoordinate()))
                            .filter((cell) -> cell.getCellOccupationStatus() == CellOccupationStatus.RESERVED)
                            .collect(Collectors.toList());

            reservedCells.forEach((cell) -> cell.setOccupationStatus(CellOccupationStatus.EMPTY));
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
