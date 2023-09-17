package UI.impl.javaFX.tabBody.results;

import javafx.beans.property.SimpleStringProperty;

public class DetailsResultModel {
    protected SimpleStringProperty simulationID;
    protected SimpleStringProperty numOfTicks;
    protected SimpleStringProperty timeCounter;

    public DetailsResultModel() {
        this.simulationID = new SimpleStringProperty();
        this.numOfTicks = new SimpleStringProperty();
        this.timeCounter = new SimpleStringProperty();
    }
}
