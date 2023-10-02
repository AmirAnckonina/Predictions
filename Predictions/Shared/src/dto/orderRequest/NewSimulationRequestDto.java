package dto.orderRequest;

import enums.TerminationType;

public class NewSimulationRequestDto {

    private final String simulationWorldName;
    private final Integer numOfExecution;
    private final TerminationType terminationType;
    private final Integer ticks;
    private final Integer seconds;

    public NewSimulationRequestDto(String simulationWorldName, Integer numOfExecution, TerminationType terminationType, Integer ticks, Integer seconds) {
        this.simulationWorldName = simulationWorldName;
        this.numOfExecution = numOfExecution;
        this.terminationType = terminationType;
        this.ticks = ticks;
        this.seconds = seconds;
    }

    public String getSimulationWorldName() {
        return simulationWorldName;
    }

    public Integer getNumOfExecution() {
        return numOfExecution;
    }

    public TerminationType getTerminationType() {
        return terminationType;
    }

    public Integer getTicks() {
        return ticks;
    }

    public Integer getSeconds() {
        return seconds;
    }
}
