package UI.impl.javaFX.tabBody.newExecution.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class KeyToFloatData {
    protected SimpleStringProperty keyNameProperty;
    protected SimpleFloatProperty floatValueProperty;

    public KeyToFloatData() {
        this.keyNameProperty = new SimpleStringProperty();
        this.floatValueProperty = new SimpleFloatProperty();
    }
}
