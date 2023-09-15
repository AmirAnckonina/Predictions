package UI.impl.javaFX.tabBody.newExecution.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class KeyToFloatData extends BaseData {
    protected SimpleFloatProperty floatValueProperty;

    public KeyToFloatData() {
        super();
        this.floatValueProperty = new SimpleFloatProperty();
    }
}
