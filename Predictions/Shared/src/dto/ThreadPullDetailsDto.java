package dto;

public class ThreadPullDetailsDto {

    private Integer runningSimulations;
    private Integer waitingSimulations;
    private Integer terminatedSimulations;

    public ThreadPullDetailsDto(Integer runningSimulations, Integer waitingSimulations, Integer terminatedSimulations) {
        this.runningSimulations = runningSimulations;
        this.waitingSimulations = waitingSimulations;
        this.terminatedSimulations = terminatedSimulations;
    }

    public Integer getRunningSimulations() {
        return runningSimulations;
    }

    public Integer getWaitingSimulations() {
        return waitingSimulations;
    }

    public Integer getTerminatedSimulations() {
        return terminatedSimulations;
    }
}
