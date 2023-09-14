package UI.impl.javaFX.tabBody.details.subbodyobjects.ruleComponent.actionsComponent.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProximityModel {

    protected SimpleStringProperty sourceEntityName;
    protected SimpleStringProperty destinationEntityName;
    protected SimpleIntegerProperty depth;
    protected SimpleIntegerProperty numberOfActions;

    public ProximityModel() {
        this.sourceEntityName = new SimpleStringProperty();
        this.destinationEntityName = new SimpleStringProperty();
        this.depth = new SimpleIntegerProperty();
        this.numberOfActions = new SimpleIntegerProperty();
    }
}
