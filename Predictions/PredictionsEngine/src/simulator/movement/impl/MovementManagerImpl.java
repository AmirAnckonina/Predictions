package simulator.movement.impl;

import simulator.definition.board.api.SpaceGridInstance;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.movement.api.MovementManager;
import structure.api.Cell;
import structure.api.Coordinate;
import structure.impl.CoordinateImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MovementManagerImpl implements MovementManager {
    @Override
    public void moveAllEntitiesOneStep(SpaceGridInstance board) {
        List<EntityInstance> entityInstanceList = board.getListOfAllInstances();

        for (EntityInstance entityInstance:entityInstanceList) {
            moveSingleEntityOneStep(entityInstance, board);
        }
    }

    private void moveSingleEntityOneStep(EntityInstance entityInstance, SpaceGridInstance board) {
        Coordinate entityCoordinate = entityInstance.getCoordinate();
        List<Cell> cellList = board.getListOfCellsInFirstCircle(entityCoordinate);

        for (Cell cell:cellList){
            if(!cell.isOccupied()){
                Cell oldCell = board.getCell(entityCoordinate);
                oldCell.removeData();

                Coordinate newCellCoordinate = cell.getCoordinate();
                cell.insertObjectToCell(entityCoordinate);
                entityInstance.setCoordinate(newCellCoordinate);

                break;
            }
        }

    }

    @Override
    public void placeEntitiesRandomizeOnSpaceGrid(Map<String, List<EntityInstance>> entitiesInstances, SpaceGridInstance spaceGrid) {
        Random random = new Random();
        List<Coordinate> emptyCoordinates = generateCellCoordinates(spaceGrid.getHeight(), spaceGrid.getWidth());

        for (Map.Entry<String, List<EntityInstance>> entry : entitiesInstances.entrySet()) {
            String key = entry.getKey();
            List<EntityInstance> entityInstanceS = entry.getValue();

            for (EntityInstance entityInstance : entityInstanceS) {
                // Find a random empty cell in the matrix
                boolean placed = false;

                while (!placed) {
                    int randomCoordinateIndex = random.nextInt(emptyCoordinates.size());
                    Coordinate randomCoordinate = emptyCoordinates.get(randomCoordinateIndex);

                    if (!spaceGrid.getCell(randomCoordinate).isOccupied()) {
                        spaceGrid.getCell(randomCoordinate).insertObjectToCell(entityInstance);
                        entityInstance.setCoordinate(emptyCoordinates.get(randomCoordinateIndex));
                        placed = true;
                    }

                    emptyCoordinates.remove(randomCoordinateIndex);
                }
            }
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
    public void setEntitiesOnSpaceGrid(SpaceGridInstance board, List<EntityInstance> entityInstanceList) {

    }
}
