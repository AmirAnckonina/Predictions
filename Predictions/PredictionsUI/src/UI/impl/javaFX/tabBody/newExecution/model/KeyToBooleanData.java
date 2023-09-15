package UI.impl.javaFX.tabBody.newExecution.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class KeyToBooleanData extends BaseData {
    protected SimpleBooleanProperty booleanValueProperty;

    public KeyToBooleanData() {
        super();
        this.booleanValueProperty = new SimpleBooleanProperty();
    }
}
