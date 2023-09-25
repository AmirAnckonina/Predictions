package body.newExecution.components.environmentVariable;

import enums.SetPropertyStatus;

public interface KeyValueProperty {
    void setStatus(SetPropertyStatus setPropertyStatus);
    void clearAndResetProperty();
    <T> void setPropertyValueByManualParamProcedure(T value);
}
