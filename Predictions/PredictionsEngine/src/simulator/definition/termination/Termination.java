package simulator.definition.termination;

import enums.TerminationType;

import java.util.Optional;

public class Termination {
    private final Integer ticksTermination;
    private final Integer secondsTermination;
    private Boolean terminationWillBeDoneByUser;
    private TerminationType terminationReason;
    private Boolean terminateFlagUp;

    public Termination(Integer ticksTermination, Integer secondsTermination, Boolean terminationWillBeDoneByUser) {
        this.ticksTermination =  ticksTermination;
        this.secondsTermination = secondsTermination;
        this.terminationWillBeDoneByUser = terminationWillBeDoneByUser;
        this.terminateFlagUp = false;
    }

    public Optional<Integer> getTicksTermination() {
        return Optional.ofNullable(ticksTermination);
    }

    public Optional<Integer> getSecondsTermination() {
        return Optional.ofNullable(secondsTermination);
    }

    public Optional<Boolean> byUser() {
        return  Optional.ofNullable(terminationWillBeDoneByUser);
    }

    public void terminateInTheNextTick() {
        this.terminateFlagUp = true;
    }

    public boolean shouldTerminate(int currTick, long currTimeInMilliSec) {
        boolean shouldTerminateResult;

        if (terminateFlagUp) {
            shouldTerminateResult = true;
            terminateFlagUp = false;
            this.terminationReason = TerminationType.USER;
        } else if (terminationWillBeDoneByUser) {
            shouldTerminateResult = false;
            this.terminationReason = TerminationType.USER;
        } else {
            shouldTerminateResult = checkTerminationByTicksOrSeconds(currTick, currTimeInMilliSec);
        }

        return shouldTerminateResult;
    }

    private boolean checkTerminationByTicksOrSeconds(int currTick, long currTimeInMilliSec) {

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
            terminationReason = TerminationType.Seconds;
        } else if (shouldTerminateByTicks && !shouldTerminateBySeconds) {
            terminationReason = TerminationType.Ticks;
        } else {
            terminationReason = TerminationType.TicksAndSeconds;
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

    public TerminationType reasonForTerminate(){
        return terminationReason;
    }
}
