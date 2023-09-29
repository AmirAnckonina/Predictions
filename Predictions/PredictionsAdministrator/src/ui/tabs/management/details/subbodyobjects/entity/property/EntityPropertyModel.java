package ui.tabs.management.details.subbodyobjects.entity.property;

import javafx.beans.property.SimpleStringProperty;

public class EntityPropertyModel {
    protected SimpleStringProperty name;

    public EntityPropertyModel() {
        this.name = new SimpleStringProperty();
        this.propertyType = new SimpleStringProperty();
        this.initializationType = new SimpleStringProperty();
        this.from = new SimpleStringProperty();
        this.to = new SimpleStringProperty();
    }

    protected SimpleStringProperty propertyType;
    protected SimpleStringProperty initializationType;
    protected SimpleStringProperty from;
    protected SimpleStringProperty to;

}
