package simulator.definition.board.api;

import simulator.execution.instance.entity.api.EntityInstance;
import structure.api.Cell;
import structure.impl.CellImpl;

import java.util.List;

public interface Board {

    int getTotalNumberOfFreeCells();
    int getTotalNumberOfCells();
    int getHeight();
    int getWidth();
    CellImpl getCell(int x, int y);
    List<EntityInstance> getListOfInstancesInFirstCircle(int x, int y);
    List<EntityInstance> getListOfInstancesInSecondCircle(int x, int y);
}
