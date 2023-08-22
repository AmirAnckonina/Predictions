package simulator.definition.termination;

import simulator.runner.utils.exceptions.eTerminationReason;

import java.util.Optional;

public class Termination {
    private final Integer ticksTermination;
    private final Integer secondsTermination;
    private eTerminationReason reasonForTerminate;

    public Termination(Integer ticksTermination, Integer secondsTermination) {
        this.ticksTermination =  ticksTermination;
        this.secondsTermination = secondsTermination;
    }

    public Optional<Integer> getTicksTermination() {

        return Optional.ofNullable(ticksTermination);
    }

    public Optional<Integer> getSecondsTermination() {
           return Optional.ofNullable(secondsTermination);
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
            reasonForTerminate = eTerminationReason.Time;
        } else if (shouldTerminateByTicks && !shouldTerminateBySeconds) {
            reasonForTerminate = eTerminationReason.Ticks;
        } else {
            reasonForTerminate = eTerminationReason.TicksAndTime;
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

    public eTerminationReason reasonForTerminate(){
        return reasonForTerminate;
    }
}
