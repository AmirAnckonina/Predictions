package simulator.definition.termination;

import simulator.runner.utils.exceptions.TerminationReason;

import java.util.Optional;

public class Termination {
    private final Integer ticksTermination;
    private final Integer secondsTermination;
    private Boolean byUser;
    private TerminationReason reasonForTerminate;

    public Termination(Integer ticksTermination, Integer secondsTermination, Boolean byUser) {
        this.ticksTermination =  ticksTermination;
        this.secondsTermination = secondsTermination;
        this.byUser = byUser;
    }

    public Optional<Integer> getTicksTermination() {

        return Optional.ofNullable(ticksTermination);
    }

    public Optional<Integer> getSecondsTermination() {

        return Optional.ofNullable(secondsTermination);
    }

    public Optional<Boolean> byUser() {
        return  Optional.ofNullable(byUser);
    }

    public boolean shouldTerminate(int currTick, long currTimeInMilliSec) {

        boolean shouldTerminateBySeconds = false;
        boolean shouldTerminateByTicks = false;

        if (getSecondsTermination().isPresent()) {

            Float currTimeInSec = (currTimeInMilliSec / 1000f);
            shouldTerminateBySeconds = getSecondsTermination().get() <= currTimeInSec;
        }

        if (getTicksTermination().isPresent()) {
            shouldTerminateByTicks = getTicksTermination().get() <= currTick;
        }

        if (shouldTerminateBySeconds && !shouldTerminateByTicks){
            reasonForTerminate = TerminationReason.Time;
        } else if (shouldTerminateByTicks && !shouldTerminateBySeconds) {
            reasonForTerminate = TerminationReason.Ticks;
        } else {
            reasonForTerminate = TerminationReason.TicksAndTime;
        }

        return shouldTerminateBySeconds || shouldTerminateByTicks;
    }

    @Override
    public String toString() {
        return "Termination{" +
                "ticksTermination=" + ticksTermination +
                ", secondsTermination=" + secondsTermination +
                '}';
    }

    public TerminationReason reasonForTerminate(){
        return reasonForTerminate;
    }
}
