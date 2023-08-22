package dto;

public class SimulationEndDto {
    private String guid;
    private String terminationReason;

    public SimulationEndDto(String guid, String terminationReason) {
        this.guid = guid;
        this.terminationReason = terminationReason;
    }

    public String getGuid() {
        return guid;
    }

    public String getTerminationReason() {
        return terminationReason;
    }
}
