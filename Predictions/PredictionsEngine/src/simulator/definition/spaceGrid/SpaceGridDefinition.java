package simulator.definition.spaceGrid;

public class SpaceGridDefinition {
    private Integer rows;
    private Integer columns;

    public SpaceGridDefinition(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getColumns() {
        return columns;
    }
}
