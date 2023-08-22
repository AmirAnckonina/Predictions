package dto;

import dto.enums.eSetPropertyStatus;

public class SetPropertySimulatorResponseDto {
    private eSetPropertyStatus processStatus;
    private String message;

    public SetPropertySimulatorResponseDto(eSetPropertyStatus processStatus, String message) {
        this.processStatus = processStatus;
        this.message = message;
    }

    public eSetPropertyStatus getProcessStatus() {
        return processStatus;
    }

    public String getMessage() {
        return message;
    }
}
