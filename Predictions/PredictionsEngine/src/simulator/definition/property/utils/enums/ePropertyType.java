package simulator.definition.property.utils.enums;

public enum ePropertyType {
    DECIMAL{
        public Double convert(Object value) {
            if (!(value instanceof Double)) {
                throw new IllegalArgumentException("value " + value + " is not of a DECIMAL type (expected Double class)");
            }
            return (Double) value;
        }
    }, BOOLEAN{
        public Boolean convert(Object value) {
            if (!(value instanceof Boolean)) {
                throw new IllegalArgumentException("value " + value + " is not of a DECIMAL type (expected Boolean class)");
            }
            return (Boolean) value;
        }
    }, STRING{
        public String convert(Object value) {
            if (!(value instanceof String)) {
                throw new IllegalArgumentException("value " + value + " is not of a DECIMAL type (expected String class)");
            }
            return (String) value;
        }
    }, FLOAT{
        public Float convert(Object value) {
            if (!(value instanceof Float)) {
                throw new IllegalArgumentException("value " + value + " is not of a DECIMAL type (expected Float class)");
            }
            return (Float) value;
        }
    }, INTEGER{
        public Integer convert(Object value) {
            if (!(value instanceof Integer)) {
                throw new IllegalArgumentException("value " + value + " is not of a DECIMAL type (expected Integer class)");
            }
            return (Integer) value;
        }
    };

    public abstract <T> T convert(Object value);
}
