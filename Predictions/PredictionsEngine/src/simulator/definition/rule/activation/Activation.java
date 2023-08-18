package simulator.definition.rule.activation;

import java.util.Optional;

public class Activation {
    private Integer ticksInterval;
    private Double probability;

    public Activation(Integer ticksInterval, Double probability) {

        this.ticksInterval = Optional.ofNullable(ticksInterval).orElse(1);
        this.probability = Optional.ofNullable(probability).orElse(1.0);
    }

    public Activation(Integer ticksInterval) {
        this(ticksInterval, null);
    }

    public Activation(Double probability) {
        this(null, probability);
    }

    public Optional<Integer> getTicksInterval() {
        return Optional.ofNullable(ticksInterval);
    }

    public Optional<Double> getProbability() {
        return Optional.ofNullable(probability);
    }
}
