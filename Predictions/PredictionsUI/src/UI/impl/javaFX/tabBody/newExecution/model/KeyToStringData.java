package UI.impl.javaFX.tabBody.newExecution.model;

import javafx.beans.property.SimpleStringProperty;


public class KeyToStringData {
    protected SimpleStringProperty keyNameProperty;
    protected SimpleStringProperty stringValueProperty;

    public KeyToStringData() {
        this.keyNameProperty = new SimpleStringProperty();
        this.stringValueProperty = new SimpleStringProperty();
    }
}
