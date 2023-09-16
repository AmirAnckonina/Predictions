package simulator.execution.context.api;

import simulator.execution.instance.spaceGrid.api.SpaceGridInstanceWrapper;
import simulator.execution.instance.entity.api.EntityInstance;

import simulator.execution.instance.property.api.PropertyInstance;
import simulator.information.tickDocument.api.TickDocument;


public interface ExecutionContext {
    EntityInstance getEntityInstanceByName(String entityName);
    EntityInstance getPrimaryEntityInstance();
    EntityInstance getSecondaryEntityInstance();
    void removeEntity(String entityName, EntityInstance entityInstance);
    PropertyInstance getEnvironmentVariable(String name);
    void setSecondaryEntityInstance(EntityInstance additionalEntityInstance);
    TickDocument getTickDocument();
    SpaceGridInstanceWrapper getSpaceGridInstanceWrapper();
}
