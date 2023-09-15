package UI.impl.javaFX.tabBody.newExecution.model;

import UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.KeyValueProperty;
import dto.enums.SetPropertyStatus;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class BaseData {
    protected SimpleBooleanProperty checkboxProperty;
    protected SimpleStringProperty keyNameProperty;
    protected SimpleStringProperty typeProperty;
    protected SimpleStringProperty statusProperty;

    public BaseData() {
        this.checkboxProperty = new SimpleBooleanProperty();
        this.keyNameProperty = new SimpleStringProperty();
        this.typeProperty = new SimpleStringProperty();
        this.statusProperty = new SimpleStringProperty();
    }
}
