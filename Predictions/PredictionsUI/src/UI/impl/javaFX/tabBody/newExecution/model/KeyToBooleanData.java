package UI.impl.javaFX.tabBody.newExecution.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class KeyToBooleanData {
    protected SimpleStringProperty keyNameProperty;
    protected SimpleBooleanProperty booleanValueProperty;

    public KeyToBooleanData() {
        this.keyNameProperty = new SimpleStringProperty();
        this.booleanValueProperty = new SimpleBooleanProperty();
    }
}
