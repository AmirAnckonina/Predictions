package UI.impl.javaFX.tabBody.details.subbodyobjects.ruleComponent.actionsComponent.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SimpleConditionModel {
    protected SimpleStringProperty numOfThan;
    protected SimpleStringProperty numOfElse;
    protected SimpleStringProperty property;
    protected SimpleStringProperty operator;
    protected SimpleStringProperty value;

    public SimpleConditionModel() {
        this.numOfThan = new SimpleStringProperty();
        this.numOfElse = new SimpleStringProperty();
        this.property = new SimpleStringProperty();
        this.operator = new SimpleStringProperty();
        this.value = new SimpleStringProperty();
    }
}
