package UI.impl.javaFX.tabBody.details.subbodyobjects.environment;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EnvironmentModel {
    protected SimpleStringProperty name;
    protected SimpleStringProperty type;
    protected SimpleStringProperty value;
    protected SimpleStringProperty from;
    protected SimpleStringProperty to;

    public EnvironmentModel() {
        this.name = new SimpleStringProperty();
        this.type = new SimpleStringProperty();
        this.value = new SimpleStringProperty();
        this.from = new SimpleStringProperty();
        this.to = new SimpleStringProperty();
    }
}
