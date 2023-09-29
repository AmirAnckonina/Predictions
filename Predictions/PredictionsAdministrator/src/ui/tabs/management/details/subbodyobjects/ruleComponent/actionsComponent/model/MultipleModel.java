package ui.tabs.management.details.subbodyobjects.ruleComponent.actionsComponent.model;

import javafx.beans.property.SimpleStringProperty;

public class MultipleModel {
    protected SimpleStringProperty logicType;
    protected SimpleStringProperty numOfCondition;
    protected SimpleStringProperty nThen;
    protected SimpleStringProperty nElse;

    public MultipleModel() {
        this.logicType = new SimpleStringProperty();
        this.numOfCondition = new SimpleStringProperty();
        this.nThen = new SimpleStringProperty();
        this.nElse = new SimpleStringProperty();
    }
}
