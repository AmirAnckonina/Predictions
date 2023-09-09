package UI.impl.javaFX.tabBody.newExecution.components.entityPopulation.model;

import UI.impl.javaFX.tabBody.newExecution.components.entityPopulation.EntityPopulationController;
import javafx.beans.property.SimpleStringProperty;

public class EntityPopulationLogic {

    private EntityPopulationController entityPopulationController;
    private SimpleStringProperty entityName;
    private int population;

    public EntityPopulationLogic(EntityPopulationController entityPopulationController) {
        this.entityPopulationController = entityPopulationController;
        this.entityName = new SimpleStringProperty();
        this.population = 0;
    }



}
