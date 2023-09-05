package dto;

import dto.enums.SetPropertyStatus;

public class SetPropertySimulatorResponseDto {
    private SetPropertyStatus processStatus;
    private String message;

    public SetPropertySimulatorResponseDto(SetPropertyStatus processStatus, String message) {
        this.processStatus = processStatus;
        this.message = message;
    }

    public SetPropertyStatus getProcessStatus() {
        return processStatus;
    }

    public String getMessage() {
        return message;
    }
}
