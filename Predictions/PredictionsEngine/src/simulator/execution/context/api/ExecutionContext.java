package simulator.execution.context.api;

import simulator.execution.instance.spaceGrid.api.SpaceGridInstanceWrapper;
import simulator.execution.instance.entity.api.EntityInstance;

import simulator.execution.instance.property.api.PropertyInstance;
import simulator.information.tickDocument.api.TickDocument;


public interface ExecutionContext {
    EntityInstance getEntityInstanceByName(String entityName);
    PropertyInstance getEnvironmentVariable(String name);
    void setSecondaryEntityInstance(EntityInstance additionalEntityInstance);
    TickDocument getTickDocument();
    SpaceGridInstanceWrapper getSpaceGridInstanceWrapper();
    void killEntityInstanceProcedure(EntityInstance entityInstanceToKill);
    EntityInstance createEntityInstanceProcedure(String EntityFamilyName);
    CrossedExecutionContext getCrossedExecutionContext();
    void derivePropertiesBetweenInstancesProcedure(EntityInstance createdEntityInstance, EntityInstance instanceToKill);
}
