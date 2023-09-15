package UI.impl.javaFX.tabBody.newExecution.model;

import javafx.beans.property.SimpleStringProperty;


public class KeyToStringData extends BaseData {
    protected SimpleStringProperty stringValueProperty;

    public KeyToStringData() {
        super();
        this.stringValueProperty = new SimpleStringProperty();
    }
}
