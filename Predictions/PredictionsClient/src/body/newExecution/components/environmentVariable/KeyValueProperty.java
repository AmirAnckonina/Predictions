package body.newExecution.components.environmentVariable;

import enums.SetPropertyStatus;

import java.util.Optional;

public interface KeyValueProperty {
    void setStatus(SetPropertyStatus setPropertyStatus);
    void clearAndResetProperty();
    <T> void setPropertyValueByManualParamProcedure(T value);
    Optional<String> getValueAsString();
}
