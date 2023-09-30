package dto;

public class TerminationInfoDto {

    private final Integer ticksTermination;
    private final Integer secondsTermination;

    public TerminationInfoDto(Integer ticksTermination, Integer secondsTermination) {
        this.ticksTermination = ticksTermination;
        this.secondsTermination = secondsTermination;
    }

    public Integer getTicksTermination() {
        return ticksTermination;
    }

    public Integer getSecondsTermination() {
        return secondsTermination;
    }

    @Override
    public String toString() {
        String ticksTerminationString;
        String secondsTerminationString;
        if(ticksTermination != null){
            ticksTerminationString = ticksTermination.toString();
        }else {
            ticksTerminationString = "-";
        }

        if(secondsTermination != null){
            secondsTerminationString = secondsTermination.toString();
        }else {
            secondsTerminationString = "-";
        }
        return  "ticksTermination = " + ticksTermination +
                ", secondsTermination = " + secondsTermination;
    }
}
