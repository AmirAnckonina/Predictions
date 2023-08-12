package dto;

import dto.builder.params.enums.eSetPropertyStatus;

public class SetPropertySimulatorResponseDto {
    eSetPropertyStatus processStatus;
    String message;

    public SetPropertySimulatorResponseDto(eSetPropertyStatus processStatus, String message) {
        this.processStatus = processStatus;
        this.message = message;
    }
}
