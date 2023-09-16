package simulator.execution.instance.movement.manager.impl;

import simulator.execution.instance.spaceGrid.api.SpaceGridInstanceWrapper;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.movement.manager.api.MovementManager;
import structure.api.Cell;
import structure.api.Coordinate;
import structure.impl.CoordinateImpl;

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
        Cell currEntityInstanceCell = spaceGridInstanceWrapper.getCellByCoordinate(entityCoordinate);
        List<Cell> vacantCells = spaceGridInstanceWrapper.getOneStepVacantCells(entityCoordinate);
        //List<Cell> cellList = spaceGridInstanceWrapper.getListOfCellsInFirstCircle(entityCoordinate);

//        if (!vacantCells.isEmpty()) {
//            int randomVacantCellIdx = new Random().nextInt(vacantCells.size());
//            Cell vacantCellToMove = vacantCells.get(randomVacantCellIdx);
//            vacantCellToMove.insertObjectInstanceToCell(entityInstance);
//        }

        for (Cell cell:vacantCells) {
            if(!cell.isOccupied()){
                Cell oldCell = spaceGridInstanceWrapper.getCellByCoordinate(entityCoordinate);
                oldCell.removeObjectInstanceFromCell();

                Coordinate newCellCoordinate = cell.getCoordinate();
                cell.insertObjectInstanceToCell(entityCoordinate);
                entityInstance.setCoordinate(newCellCoordinate);

                break;
            }
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
                        if (!spaceGrid.getCellByCoordinate(randomCoordinate).isOccupied()) {
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
