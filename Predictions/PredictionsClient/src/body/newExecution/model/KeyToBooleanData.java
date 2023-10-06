package body.newExecution.model;

import javafx.beans.property.SimpleBooleanProperty;

public class KeyToBooleanData extends BaseData {
    protected SimpleBooleanProperty booleanValueProperty;

    public KeyToBooleanData() {
        super();
        this.booleanValueProperty = new SimpleBooleanProperty();
    }


}
