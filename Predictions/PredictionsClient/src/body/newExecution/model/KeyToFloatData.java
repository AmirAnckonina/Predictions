package body.newExecution.model;

import javafx.beans.property.SimpleStringProperty;

public class KeyToFloatData extends BaseData {
    protected SimpleStringProperty floatValuePropertyAsString;

    public KeyToFloatData() {
        super();
        this.floatValuePropertyAsString = new SimpleStringProperty();
    }
}
