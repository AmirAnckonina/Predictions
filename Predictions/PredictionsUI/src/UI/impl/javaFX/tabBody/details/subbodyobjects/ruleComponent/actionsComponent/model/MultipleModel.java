package UI.impl.javaFX.tabBody.details.subbodyobjects.ruleComponent.actionsComponent.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MultipleModel {
    protected SimpleStringProperty logicType;
    protected SimpleIntegerProperty numOfCondition;

    public MultipleModel() {
        this.logicType = new SimpleStringProperty();
        this.numOfCondition = new SimpleIntegerProperty();
    }
}
