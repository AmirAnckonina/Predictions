package simulator.execution.instance.entity.manager.impl;

import enums.PropertyType;
import simulator.definition.entity.impl.EntityDefinition;
import simulator.establishment.manager.api.EstablishmentManager;
import simulator.establishment.manager.impl.EstablishmentManagerImpl;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.manager.api.EntitiesInstancesManager;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.information.tickDocument.api.TickDocument;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

import java.util.*;

public class EntitiesInstancesManagerImpl implements EntitiesInstancesManager {

    private Map<String, List<EntityInstance>> entitiesInstances;
    private List<EntityInstance> killWaitingList;
    private List<EntityInstance> createWaitingList;

    public EntitiesInstancesManagerImpl(Map<String, List<EntityInstance>> entitiesInstances) {
        this.entitiesInstances = entitiesInstances;
        this.killWaitingList = new ArrayList<>();
        this.createWaitingList = new ArrayList<>();
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

        OptionalInt maxId =
                this.getEntityInstances(entityDefinition.getName())
                        .stream()
                        .mapToInt(EntityInstance::getId)
                        .max();

        if (!maxId.isPresent()) {
            throw new SimulatorRunnerException(String.format("Couldn't find the maximum id of the entity %s", entityDefinition.getName()));
        }

        int newId = maxId.getAsInt() + 1;
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

        /**
         * The part below is to validate i already deleted the instances, so it shouldn't reach the if block never
         * This is the old loop but we changed the method
         */
        entitiesInstances.forEach((entityFamily, entityInstances) -> {
            // Run on all current entity instances with itr
            Iterator<EntityInstance> entityItr2 = entityInstances.iterator();
            while (entityItr2.hasNext()) {
                EntityInstance entityInstance = entityItr2.next();
                if (!entityInstance.isAlive()) {
                    entityItr2.remove();
                }
            }
        });
    }

    @Override
    public void addInstanceToCreateWaitingList(EntityInstance entityInstanceToCreate) {
        this.createWaitingList.add(entityInstanceToCreate);
    }

    @Override
    public void completeCreateEntitiesInstancesInWaitingList() {

        throw new SimulatorRunnerException("Not impl createEntitiesInstancesInWaitingList method");
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
