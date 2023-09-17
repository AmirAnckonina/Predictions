package simulator.execution.context.api;

import simulator.definition.board.api.SpaceGridInstance;
import simulator.execution.instance.entity.api.EntityInstance;

import simulator.execution.instance.property.api.PropertyInstance;
import simulator.information.tickDocument.api.TickDocument;


public interface ExecutionContext {

    EntityInstance getEntityInstanceByName(String entityName);
    EntityInstance getPrimaryEntityInstance();
    EntityInstance getSecondaryEntityInstance();
    PropertyInstance getEnvironmentVariable(String name);
    void setSecondaryEntityInstance(EntityInstance additionalEntityInstance);
    TickDocument getTickDocument();
    SpaceGridInstance getSpaceGridInstance();
    void killEntityInstanceProcedure(String primaryEntityName);
}
