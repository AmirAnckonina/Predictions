package UI.impl.javaFX.tabBody.newExecution.components.environmentVariable;

import dto.enums.SetPropertyStatus;

public interface KeyValueProperty {
    void setStatus(SetPropertyStatus setPropertyStatus);
    void clearAndResetProperty();
}
