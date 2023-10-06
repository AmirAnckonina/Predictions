package body.newExecution.model;

import javafx.beans.property.*;

public class
KeyToIntegerData extends BaseData {
    protected SimpleStringProperty integerValuePropertyAsString;
    public KeyToIntegerData() {
        super();
        integerValuePropertyAsString = new SimpleStringProperty();

    }
}
