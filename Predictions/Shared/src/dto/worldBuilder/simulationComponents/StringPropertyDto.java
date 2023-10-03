package dto.worldBuilder.simulationComponents;

public class StringPropertyDto {
    private String propertyName;
    private String propertyType;
    private String propertyInitializationType;
    private String propertyRangeFrom;
    private String propertyRangeTo;

    public StringPropertyDto(String propertyString) {
        setPropertiesDetailsFromString(propertyString);
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getPropertyInitializationType() {
        return propertyInitializationType;
    }

    public String getPropertyRangeFrom() {
        return propertyRangeFrom;
    }

    public String getPropertyRangeTo() {
        return propertyRangeTo;
    }

    private void setPropertiesDetailsFromString(String propertyString) {

        String[] parts = propertyString.split("\n");

        for (String part : parts) {
            String[] keyValue = part.split(": ");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                switch (key) {
                    case "property name":
                        propertyName = value;
                        break;
                    case "property type":
                        propertyType = value;
                        break;
                    case "value initialization type":
                        propertyInitializationType = value;
                        break;
                    case "Range{from":
                        propertyRangeFrom = value;
                        break;
                    case "to":
                        propertyRangeTo = value;
                        break;
                }
            }
        }

        if (propertyRangeFrom == null && propertyRangeTo == null) {
            propertyRangeFrom = "-";
            propertyRangeTo = "-";
        } else if (propertyRangeFrom == null) {
            propertyRangeFrom = "-";
        } else if (propertyRangeTo == null) {
            propertyRangeTo = "-";
        }
    }
}

        /*
property name: vacinated
property type: BOOLEAN
value initialization type: fixed

property name: age
property type: FLOAT
value initialization type: random
Range{from=20.0, to=70.0}
         */