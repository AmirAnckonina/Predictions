package dto.simulationInfo;

public class SimulationInfoRequestDto {
    private String simulationGuid;

    public SimulationInfoRequestDto(String simulationGuid) {
        this.simulationGuid = simulationGuid;
    }

    public String getSimulationGuid() {
        return simulationGuid;
    }
}
