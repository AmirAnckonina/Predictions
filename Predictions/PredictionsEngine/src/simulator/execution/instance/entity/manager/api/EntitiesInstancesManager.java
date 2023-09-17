package simulator.execution.instance.entity.manager.api;

import simulator.definition.entity.impl.EntityDefinition;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.information.tickDocument.api.TickDocument;

import java.util.List;

public interface EntitiesInstancesManager {
    List<EntityInstance> getKillWaitingList();
    List<EntityInstance> getCreateWaitingList();
    EntityInstance createEntityInstanceByDefinition(EntityDefinition entityDefinition, int tickNo);
    List<EntityInstance> getEntityInstances(String entityName);
    void killEntityInstance(String entityName, int id);
    void addInstanceToKillWaitingList(EntityInstance entityInstanceToKill);
    void completeKillEntitiesInstancesInWaitingList();
    void addInstanceToCreateWaitingList(EntityInstance entityInstanceToCreate);
    void completeCreateEntitiesInstancesInWaitingList();
    void derivePropertiesBetweenInstances(EntityInstance targetEntityInstance, EntityInstance sourceEntityInstance, TickDocument tickDocument);
}
