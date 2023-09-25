package body.details.subbodyobjects.ruleComponent.actionsComponent.model;

import javafx.beans.property.SimpleStringProperty;

public class CalculationModel {
    protected SimpleStringProperty type;
    protected SimpleStringProperty firstArgument;
    protected SimpleStringProperty secondArgument;

    public CalculationModel() {
        this.type = new SimpleStringProperty();
        this.firstArgument = new SimpleStringProperty();
        this.secondArgument = new SimpleStringProperty();
    }
}
