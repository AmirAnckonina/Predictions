package dto;

import java.util.Map;

public class EstablishedEnvironmentInfoDto {
    private Map<String, String> establishedEnvironmentProperties;

    public EstablishedEnvironmentInfoDto(Map<String, String> establishedEnvironmentProperties) {
        this.establishedEnvironmentProperties = establishedEnvironmentProperties;
    }
    public Map<String, String> getEstablishedEnvironmentProperties() {
        return establishedEnvironmentProperties;
    }
}
