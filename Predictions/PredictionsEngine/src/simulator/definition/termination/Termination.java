package simulator.definition.termination;

public class Termination {
    private int ticksTermination;
    private int secondsTermination;

    public Termination() {
    }

    public void setTicksTermination(int ticksTermination) {

        this.ticksTermination = ticksTermination;
    }

    public void setSecondsTermination(int secondsTermination) {
        this.secondsTermination = secondsTermination;
    }

    public int getTicksTermination() {
        return ticksTermination;
    }

    public int getSecondsTermination() {
        return secondsTermination;
    }
}
