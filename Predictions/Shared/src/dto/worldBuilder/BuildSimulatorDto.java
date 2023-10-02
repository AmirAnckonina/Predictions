package dto.worldBuilder;

public class BuildSimulatorDto {

    private boolean success;
    private String simulationGuid;
    public BuildSimulatorDto(boolean success, String simulationGuid) {
        this.success = success;
        this.simulationGuid = simulationGuid;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getSimulationGuid() {
        return simulationGuid;
    }
}
