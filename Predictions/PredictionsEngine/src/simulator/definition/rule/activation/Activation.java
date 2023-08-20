package simulator.definition.rule.activation;

import java.util.Optional;
import java.util.Random;

public class Activation {
    private Integer ticksInterval;
    private Double probability;

    public Activation(Integer ticksInterval, Double probability) {

        this.ticksInterval = Optional.ofNullable(ticksInterval).orElse(1);
        this.probability = Optional.ofNullable(probability).orElse(1.0);
    }

    public Activation() {
        this(null, null);
    }

    public Optional<Integer> getTicksInterval() {
        return Optional.ofNullable(ticksInterval);
    }

    public Optional<Double> getProbability() {
        return Optional.ofNullable(probability);
    }

    public boolean isActive(int tickNumber) {

        Random random = new Random();
        double randomNumber = random.nextDouble();
        return tickNumber % this.ticksInterval == 0 && this.probability >= randomNumber;
    }

    @Override
    public String toString() {
        return "Activation{" +
                "ticksInterval=" + ticksInterval +
                ", probability=" + probability +
                '}';
    }
}
