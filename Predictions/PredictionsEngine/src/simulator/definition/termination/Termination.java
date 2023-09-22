package simulator.definition.termination;

import simulator.runner.utils.exceptions.TerminationReason;

import java.util.Optional;

public class Termination {
    private final Integer ticksTermination;
    private final Integer secondsTermination;
    private Boolean terminationWillBeDoneByUser;
    private TerminationReason terminationReason;
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
            this.terminationReason = TerminationReason.USER;
        } else if (terminationWillBeDoneByUser) {
            shouldTerminateResult = false;
            this.terminationReason = TerminationReason.USER;
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
            terminationReason = TerminationReason.Time;
        } else if (shouldTerminateByTicks && !shouldTerminateBySeconds) {
            terminationReason = TerminationReason.Ticks;
        } else {
            terminationReason = TerminationReason.TicksAndTime;
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
        return terminationReason;
    }
}
