package simulator.definition.termination;

import java.util.Optional;

public class Termination {
    private final Integer ticksTermination;
    private final Integer secondsTermination;

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
}
