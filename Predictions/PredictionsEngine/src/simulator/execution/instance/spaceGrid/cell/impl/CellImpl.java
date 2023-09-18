package simulator.execution.instance.spaceGrid.cell.impl;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.spaceGrid.cell.api.Cell;
import simulator.execution.instance.spaceGrid.cell.exception.UnauthorizedCellAccess;
import simulator.execution.instance.spaceGrid.cell.enums.CellOccupationStatus;
import structure.coordinate.api.Coordinate;

public class CellImpl implements Cell {
    private Coordinate coordinate;
    private EntityInstance objectInstance;
    private CellOccupationStatus occupationStatus;

    private CellImpl(Coordinate coordinate, EntityInstance objectInstance, CellOccupationStatus occupationStatus) {
        this.coordinate = coordinate;
        this.objectInstance = objectInstance;
        this.occupationStatus = occupationStatus;
    }

    public CellImpl(Coordinate coordinate, EntityInstance objectInstance) {
        this(coordinate, objectInstance, CellOccupationStatus.OCCUPIED);
    }

    public CellImpl(Coordinate coordinate) {
        this(coordinate, null, CellOccupationStatus.EMPTY);
    }

    @Override
    public EntityInstance getObjectInstance() {
        if (this.occupationStatus == CellOccupationStatus.RESERVED) {
            throw new UnauthorizedCellAccess("Object instance couldn't be returned, because cell is reserved");
        }
        return objectInstance;
    }

    @Override
    public void updateObjectInstance(EntityInstance objectInstance) {
        this.objectInstance = objectInstance;
    }

    @Override
    public boolean insertObjectInstanceToCell(EntityInstance objectInstance) {
        boolean returnVal = false;

        if (this.occupationStatus == CellOccupationStatus.EMPTY){
            this.objectInstance = objectInstance;
            returnVal = true;
            this.occupationStatus = CellOccupationStatus.OCCUPIED;
        }

        return returnVal;
    }

    @Override
    public EntityInstance removeObjectInstanceFromCell() {
        EntityInstance copy = objectInstance;
        this.objectInstance = null;
        this.occupationStatus = CellOccupationStatus.EMPTY;

        return copy;
    }

    @Override
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    @Override
    public CellOccupationStatus getCellOccupationStatus() {
        return this.occupationStatus;
    }

    @Override
    public void reserveCell(EntityInstance objectInstance) {
        this.occupationStatus = CellOccupationStatus.RESERVED;
        this.objectInstance = objectInstance;
    }

    @Override
    public void setOccupationStatus(CellOccupationStatus occupationStatus) {
        this.occupationStatus = occupationStatus;
    }
}
