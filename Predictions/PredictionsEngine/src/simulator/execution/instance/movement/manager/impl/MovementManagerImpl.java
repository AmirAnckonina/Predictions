package simulator.execution.instance.movement.manager.impl;

import simulator.execution.instance.spaceGrid.api.SpaceGridInstanceWrapper;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.movement.manager.api.MovementManager;
import simulator.execution.instance.spaceGrid.cell.api.Cell;
import simulator.execution.instance.spaceGrid.cell.enums.CellOccupationStatus;
import structure.coordinate.api.Coordinate;
import structure.coordinate.impl.CoordinateImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MovementManagerImpl implements MovementManager {
    @Override
    public void moveAllEntitiesOneStep(SpaceGridInstanceWrapper spaceGridInstanceWrapper) {

        List<EntityInstance> entityInstanceList = spaceGridInstanceWrapper.getAllInstancesOnSpaceGrid();
        for (EntityInstance entityInstance: entityInstanceList) {
            moveSingleEntityOneStep(entityInstance, spaceGridInstanceWrapper);
        }
    }

    private void moveSingleEntityOneStep(EntityInstance entityInstance, SpaceGridInstanceWrapper spaceGridInstanceWrapper) {

        Coordinate entityCoordinate = entityInstance.getCoordinate();

        List<Cell> vacantCells = spaceGridInstanceWrapper.getOneStepVacantCells(entityCoordinate);
        if (!vacantCells.isEmpty()) {
            // Generate adj cell to move
            int randomVacantCellIdx = new Random().nextInt(vacantCells.size());
            Cell vacantCellToMove = vacantCells.get(randomVacantCellIdx);

            // First, remove the entityInstance from the currentCell
            Cell currEntityInstanceCell = spaceGridInstanceWrapper.getCellByCoordinate(entityCoordinate);
            currEntityInstanceCell.removeObjectInstanceFromCell();

            // Place the Entity Instance in the new generated adj cell
            vacantCellToMove.insertObjectInstanceToCell(entityInstance);
            entityInstance.setCoordinate(vacantCellToMove.getCoordinate());
        } else {
            // do not make any move.
        }

    }

    @Override
    public void placeEntitiesRandomizeOnSpaceGrid(Map<String, List<EntityInstance>> entitiesInstances, SpaceGridInstanceWrapper spaceGrid) {
        Random random = new Random();
        List<Coordinate> emptyCoordinates = generateCellCoordinates(spaceGrid.getRows(), spaceGrid.getColumns());
        try {
            for (Map.Entry<String, List<EntityInstance>> entry : entitiesInstances.entrySet()) {
                String key = entry.getKey();
                List<EntityInstance> entityInstanceS = entry.getValue();

                for (EntityInstance entityInstance : entityInstanceS) {
                    // Find a random empty cell in the matrix
                    boolean placed = false;
                    while (!placed) {
                        int randomCoordinateIndex = random.nextInt(emptyCoordinates.size());
                        Coordinate randomCoordinate = emptyCoordinates.get(randomCoordinateIndex);
                        if (spaceGrid.getCellByCoordinate(randomCoordinate).getCellOccupationStatus() == CellOccupationStatus.EMPTY) {
                            spaceGrid.getCellByCoordinate(randomCoordinate).insertObjectInstanceToCell(entityInstance);
                            entityInstance.setCoordinate(emptyCoordinates.get(randomCoordinateIndex));
                            placed = true;
                        }

                        emptyCoordinates.remove(randomCoordinateIndex);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private static List<Coordinate> generateCellCoordinates(int numRows, int numColumns) {
        List<Coordinate> coordinates = new ArrayList<>();

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                coordinates.add(new CoordinateImpl(row, col));
            }
        }

        return coordinates;
    }

    @Override
    public void setEntitiesOnSpaceGrid(SpaceGridInstanceWrapper board, List<EntityInstance> entityInstanceList) {

    }
}
