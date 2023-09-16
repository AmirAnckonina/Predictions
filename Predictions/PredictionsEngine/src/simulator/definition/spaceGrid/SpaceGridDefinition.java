package simulator.definition.spaceGrid;

public class SpaceGridDefinition {
    private Integer rows;
    private Integer columns;

    public SpaceGridDefinition(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public Integer getRows() {
        return this.rows;
    }

    public Integer getColumns() {
        return this.columns;
    }

    public Integer getTotalSpace() {
        return (rows * columns);
    }
}
