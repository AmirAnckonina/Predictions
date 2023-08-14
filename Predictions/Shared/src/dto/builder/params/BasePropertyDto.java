package dto.builder.params;

public class BasePropertyDto {
    private String name;
    private String propertyType;
    private String from;
    private String to;
    private String value; // Anko - if you are prefer not to keep it here and create "ResponseBasePropertyDto" instead
                          // it fine either

    public BasePropertyDto(String name, String propertyType, String from, String to, String value, String environmentVariableName) {
        this.name = name;
        this.propertyType = propertyType;
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getValue() {
        return value;
    }

}
