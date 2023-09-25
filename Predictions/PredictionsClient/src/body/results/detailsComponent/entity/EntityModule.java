package body.results.detailsComponent.entity;

import javafx.beans.property.SimpleStringProperty;

public class EntityModule {
    SimpleStringProperty name;
    SimpleStringProperty numOfEntitiesInstance;

public EntityModule() {
        this.name = new SimpleStringProperty();
        this.numOfEntitiesInstance = new SimpleStringProperty();
    }
}
