package body.results;

import javafx.beans.property.SimpleStringProperty;

public class DetailsResultModel {
    protected SimpleStringProperty simulationID;
    protected SimpleStringProperty numOfTicks;
    protected SimpleStringProperty timeCounter;
    protected SimpleStringProperty status;

    public DetailsResultModel() {
        this.simulationID = new SimpleStringProperty();
        this.numOfTicks = new SimpleStringProperty();
        this.timeCounter = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
    }
}
