package structure.impl;

public class Range<T extends Number> {
    private final T from;
    private final T to;

    public Range(T from, T to) {
        this.from = from;
        this.to = to;
    }

    public T getFrom() {
        return from;
    }

    public T getTo() {
        return to;
    }

    public boolean inRangeValidation(T value) {
        return value.doubleValue() >= this.from.doubleValue()  && value.doubleValue() <= this.to.doubleValue();
    }


    @Override
    public String toString() {
        return "Range{" +
                "from=" + from +
                ", to=" + to +
                '}' + new StringBuilder(System.lineSeparator());
    }
}
