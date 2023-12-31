package simulator.execution.instance.entity.manager.impl;

import enums.PropertyType;
import simulator.definition.entity.EntityDefinition;
import simulator.establishment.manager.api.EstablishmentManager;
import simulator.establishment.manager.impl.EstablishmentManagerImpl;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.manager.api.EntitiesInstancesManager;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.information.tickDocument.api.TickDocument;

import java.util.*;

public class EntitiesInstancesManagerImpl implements EntitiesInstancesManager {

    private Map<String, List<EntityInstance>> entitiesInstances;
    private List<EntityInstance> killWaitingList;
    private List<EntityInstance> createWaitingList;
    private Map<String, Integer> entityMaxIdMap;

    public EntitiesInstancesManagerImpl(Map<String, List<EntityInstance>> entitiesInstances) {
        this.entitiesInstances = entitiesInstances;
        this.killWaitingList = new ArrayList<>();
        this.createWaitingList = new ArrayList<>();
        this.entityMaxIdMap = new HashMap<>();
        completeCreationWaitingList();
    }

    @Override
    public List<EntityInstance> getKillWaitingList() {
        return this.killWaitingList;
    }

    @Override
    public List<EntityInstance> getCreateWaitingList() {
        return this.createWaitingList;
    }


    @Override
    public EntityInstance createEntityInstanceByDefinition(EntityDefinition entityDefinition, int tickNo) {

        EntityInstance createdEntityInstance;
        EstablishmentManager establishmentManager = new EstablishmentManagerImpl();

        int newId = this.entityMaxIdMap.get(entityDefinition.getName()) + 1;
        createdEntityInstance = establishmentManager.createSingleEntityInstance(entityDefinition, newId, tickNo);

        addInstanceToCreateWaitingList(createdEntityInstance);

        return createdEntityInstance;
    }

    @Override
    public List<EntityInstance> getEntityInstances(String entityName) {
        return entitiesInstances.get(entityName);
    }

    @Override
    public void killEntityInstance(String entityName, int id) {
        entitiesInstances.get(entityName)
                .removeIf(entIns -> entIns.getId() == id);
    }

    @Override
    public void addInstanceToKillWaitingList(EntityInstance entityInstanceToKill) {

        this.killWaitingList.add(entityInstanceToKill);
    }

    @Override
    public void completeKillEntitiesInstancesInWaitingList() {

        Iterator<EntityInstance> entityItr = this.killWaitingList.iterator();
        while (entityItr.hasNext()) {
            EntityInstance entityInstance = entityItr.next();
            killEntityInstance(entityInstance.getEntityNameFamily(), entityInstance.getId());
            entityItr.remove();
        }

        this.killWaitingList = new ArrayList<>();
    }

    @Override
    public void addInstanceToCreateWaitingList(EntityInstance entityInstanceToCreate) {
        this.createWaitingList.add(entityInstanceToCreate);
    }

    @Override
    public void completeCreationWaitingList() {
        this.createWaitingList
                .forEach((entityInstance ->
                        this.entitiesInstances.get(entityInstance.getEntityNameFamily()).add(entityInstance)));

        this.createWaitingList.clear();

        this.entitiesInstances
                .forEach((entityName, entityInstances) -> {
                    OptionalInt maxId =
                            entityInstances
                                    .stream()
                                    .mapToInt(EntityInstance::getId)
                                    .max();

                    if (!maxId.isPresent()) {
                        this.entityMaxIdMap.put(entityName, 0);
                    }
                    else  {
                        this.entityMaxIdMap.put(entityName, maxId.getAsInt());
                    }

                });
    }

    @Override
    public void derivePropertiesBetweenInstances(EntityInstance targetEntityInstance, EntityInstance sourceEntityInstance, TickDocument tickDocument) {

        Map<String, PropertyInstance> srcEntityProperties = sourceEntityInstance.getPropertiesMap();

        srcEntityProperties
                .forEach((srcEntPropName, srcEntPropInstance) -> {
                    PropertyType propType = srcEntPropInstance.getPropertyDefinition().getType();
                    if (targetEntityInstance.HasProperty(srcEntPropName, propType)) {
                        Object valToSet = srcEntPropInstance.getValue();
                        targetEntityInstance
                                .getPropertyInstanceByName(srcEntPropName)
                                .updateValue(valToSet, tickDocument.getTickNumber());
                    }
        });
    }


}
