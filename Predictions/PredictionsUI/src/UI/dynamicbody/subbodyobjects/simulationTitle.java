package UI.dynamicbody.subbodyobjects;

import javafx.beans.property.SimpleStringProperty;

public class simulationTitle {

    private final SimpleStringProperty simulationID;

    public simulationTitle(String simulationID) {
        this.simulationID = new SimpleStringProperty(simulationID);
    }

    public String getName() {
        return this.simulationID.get();
    }

    public void setSimulationID(String simulationID) {
        this.simulationID.set(simulationID);
    }

    @Override
    public String toString() {
        return getName();
    }

}
