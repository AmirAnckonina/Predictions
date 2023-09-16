package UI.impl.javaFX.tabBody.details.subbodyobjects.ruleComponent.actionsComponent.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProximityModel {

    protected SimpleStringProperty sourceEntityName;
    protected SimpleStringProperty destinationEntityName;
    protected SimpleStringProperty depth;
    protected SimpleStringProperty numberOfActions;

    public ProximityModel() {
        this.sourceEntityName = new SimpleStringProperty();
        this.destinationEntityName = new SimpleStringProperty();
        this.depth = new SimpleStringProperty();
        this.numberOfActions = new SimpleStringProperty();
    }
}
