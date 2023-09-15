package UI.impl.javaFX.tabBody.newExecution.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class KeyToIntegerData extends BaseData {
    protected SimpleIntegerProperty integerValueProperty;
    public KeyToIntegerData() {
        super();
        integerValueProperty = new SimpleIntegerProperty();
    }
}
