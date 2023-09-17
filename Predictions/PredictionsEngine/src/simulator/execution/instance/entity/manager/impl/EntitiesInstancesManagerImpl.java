package simulator.execution.instance.entity.manager.impl;

import simulator.definition.entity.EntityDefinition;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.manager.api.EntitiesInstancesManager;
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
    public EntityInstance createEntityInstance(EntityDefinition entityDefinition) {
        throw new SimulatorRunnerException("not implemented createEntityInstance method");
    }

    @Override
    public List<EntityInstance> getEntityInstances(String entityName) {
        return entitiesInstances.get(entityName);
    }

    @Override
    public void killEntity(String entityName, int id) {

        entitiesInstances
                .get(entityName)
                .removeIf(
                        entIns -> entIns.getId() == id
                );
    }

    @Override
    public void addInstanceToKillWaitingList(EntityInstance entityInstanceToKill) {
        throw new SimulatorRunnerException("Not impl addInstanceToKillWaitingList");
    }

}
